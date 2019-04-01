package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.domru.dto.CarDto;
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

  /**
   * TODO.
   * @return TODO
   */
  @GetMapping("")
  @ApiOperation(value = "получить все автомобили")
  public List<CarDto> findAll() {
    return carRepository.findAll()
        .stream()
        .map(CarDto::toDto)
        .collect(Collectors.toList());
  }

  @PostMapping("")
  @ApiOperation(value = "создать автомобиль")
  public CarDto create(
      @RequestBody
      @Valid
          CarDto car
  ) {
    car.setId(null);
    return CarDto.toDto(carRepository.save(car.fromDto()));
  }

  /**
   * TODO.
   * @param car TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить автомобиль")
  public CarDto update(
      @RequestBody
      @Valid
          CarDto car,
      HttpServletResponse response
  ) {
    if (carRepository.findById(car.getId()).isPresent()) {
      return CarDto.toDto(carRepository.save(car.fromDto()));
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
  @ApiOperation(value = "удалить автомобиль")
  public CarDto delete(
      @RequestBody
          CarDto car,
      HttpServletResponse response
  ) {
    if (carRepository.findById(car.getId()).isPresent()) {
      carRepository.delete(car.fromDto());
      return car;
    }
    response.setStatus(404);
    return null;
  }
}
