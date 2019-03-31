package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.domru.model.CarModel;
import org.domru.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-model")
@Api(value = "car-model", description = "Контроллер для работы с моделями автомобилей")
public class CarModelController {

  @Autowired
  CarModelRepository carModelRepository;

  @GetMapping("")
  @ApiOperation(value = "получить все модели")
  public List<CarModel> findAll() {
    return carModelRepository.findAll();
  }

  @PostMapping("")
  @ApiOperation(value = "создать модель")
  public CarModel create(
      @RequestBody
      CarModel carModel
  ) {
    carModel.setId(null);
    return carModelRepository.save(carModel);
  }

  /**
   * TODO.
   * @param carModel TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить модель")
  public CarModel update(
      @RequestBody
          CarModel carModel,
      HttpServletResponse response
  ) {
    if (carModelRepository.findById(carModel.getId()).isPresent()) {
      return carModelRepository.save(carModel);
    }
    response.setStatus(404);
    return null;
  }

  /**
   * TODO.
   * @param carModel TODO
   * @param response TODO
   * @return TODO
   */
  @DeleteMapping("")
  @ApiOperation(value = "удалить модель")
  public CarModel delete(
      @RequestBody
          CarModel carModel,
      HttpServletResponse response
  ) {
    if (carModelRepository.findById(carModel.getId()).isPresent()) {
      carModelRepository.delete(carModel);
      return carModel;
    }
    response.setStatus(404);
    return null;
  }
}
