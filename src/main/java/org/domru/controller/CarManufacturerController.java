package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.domru.model.CarManufacturer;
import org.domru.repository.CarManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-manufacturer")
@Api(value = "car-manufacturer", description = "Контроллер для работы с производителями")
public class CarManufacturerController {

  @Autowired
  CarManufacturerRepository carManufacturerRepository;

  @GetMapping("")
  @ApiOperation(value = "получить всех производителей")
  public List<CarManufacturer> findAll() {
    return carManufacturerRepository.findAll();
  }

  @PostMapping("")
  @ApiOperation(value = "создать производителя")
  public CarManufacturer create(
      @RequestBody
      CarManufacturer carManufacturer
  ) {
    carManufacturer.setId(null);
    return carManufacturerRepository.save(carManufacturer);
  }

  /**
   * TODO.
   * @param carManufacturer TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить производителя")
  public CarManufacturer update(
      @RequestBody
          CarManufacturer carManufacturer,
      HttpServletResponse response
  ) {
    if (carManufacturerRepository.findById(carManufacturer.getId()).isPresent()) {
      return carManufacturerRepository.save(carManufacturer);
    }
    response.setStatus(404);
    return null;
  }

  /**
   * TODO.
   * @param carManufacturer TODO
   * @param response TODO
   * @return TODO
   */
  @DeleteMapping("")
  @ApiOperation(value = "удалить производителя")
  public CarManufacturer delete(
      @RequestBody
          CarManufacturer carManufacturer,
      HttpServletResponse response
  ) {
    if (carManufacturerRepository.findById(carManufacturer.getId()).isPresent()) {
      carManufacturerRepository.delete(carManufacturer);
      return carManufacturer;
    }
    response.setStatus(404);
    return null;
  }
}
