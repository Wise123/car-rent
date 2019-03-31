package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.domru.model.CarManufactorer;
import org.domru.repository.CarManufactorerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-manufactorer")
@Api(value = "car-manufactorer", description = "Контроллер для работы с производителями")
public class CarManufactorerController {

  @Autowired
  CarManufactorerRepository carManufactorerRepository;

  @GetMapping("")
  @ApiOperation(value = "получить всех производителей")
  public List<CarManufactorer> findAll() {
    return carManufactorerRepository.findAll();
  }

  @PostMapping("")
  @ApiOperation(value = "создать производителя")
  public CarManufactorer create(
      @RequestBody
      CarManufactorer carManufactorer
  ) {
    carManufactorer.setId(null);
    return carManufactorerRepository.save(carManufactorer);
  }

  /**
   * TODO.
   * @param carManufactorer TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить производителя")
  public CarManufactorer update(
      @RequestBody
          CarManufactorer carManufactorer,
      HttpServletResponse response
  ) {
    if (carManufactorerRepository.findById(carManufactorer.getId()).isPresent()) {
      return carManufactorerRepository.save(carManufactorer);
    }
    response.setStatus(404);
    return null;
  }

  /**
   * TODO.
   * @param carManufactorer TODO
   * @param response TODO
   * @return TODO
   */
  @DeleteMapping("")
  @ApiOperation(value = "удалить производителя")
  public CarManufactorer delete(
      @RequestBody
          CarManufactorer carManufactorer,
      HttpServletResponse response
  ) {
    if (carManufactorerRepository.findById(carManufactorer.getId()).isPresent()) {
      carManufactorerRepository.delete(carManufactorer);
      return carManufactorer;
    }
    response.setStatus(404);
    return null;
  }
}
