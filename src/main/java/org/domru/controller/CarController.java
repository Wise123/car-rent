package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.domru.model.Car;
import org.domru.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
@Api(value = "car", description = "Контроллер для работы с автомобилями")
public class CarController {

  @Autowired
  CarRepository carRepository;

  @GetMapping("")
  @ApiOperation(value = "получить все модели")
  public List<Car> findAll() {
    return carRepository.findAll();
  }

  @PostMapping("")
  @ApiOperation(value = "создать модель")
  public Car create(
      @RequestBody
          Car car
  ) {
    car.setId(null);
    return carRepository.save(car);
  }

  /**
   * TODO.
   * @param car TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить модель")
  public Car update(
      @RequestBody
          Car car,
      HttpServletResponse response
  ) {
    if (carRepository.findById(car.getId()).isPresent()) {
      return carRepository.save(car);
    }
    response.setStatus(404);
    return null;
  }

  /**
   * TODO.
   * @param car TODO
   * @param response TODO
   * @return TODO
   */
  @DeleteMapping("")
  @ApiOperation(value = "удалить модель")
  public Car delete(
      @RequestBody
          Car car,
      HttpServletResponse response
  ) {
    if (carRepository.findById(car.getId()).isPresent()) {
      carRepository.delete(car);
      return car;
    }
    response.setStatus(404);
    return null;
  }
}
