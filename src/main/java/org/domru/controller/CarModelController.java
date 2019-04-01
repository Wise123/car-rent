package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.domru.dto.CarModelDto;
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

  /**
   * TODO.
   * @return TODO
   */
  @GetMapping("")
  @ApiOperation(value = "получить все модели")
  public List<CarModelDto> findAll() {
    return carModelRepository.findAll()
        .stream()
        .map(CarModelDto::toDto)
        .collect(Collectors.toList());
  }

  @PostMapping("")
  @ApiOperation(value = "создать модель")
  public CarModelDto create(
      @RequestBody
      CarModelDto carModel
  ) {
    carModel.setId(null);
    return CarModelDto.toDto(carModelRepository.save(carModel.fromDto()));
  }

  /**
   * TODO.
   * @param carModel TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить модель")
  public CarModelDto update(
      @RequestBody
          CarModelDto carModel,
      HttpServletResponse response
  ) {
    if (carModelRepository.findById(carModel.getId()).isPresent()) {
      return CarModelDto.toDto(carModelRepository.save(carModel.fromDto()));
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
  public CarModelDto delete(
      @RequestBody
          CarModelDto carModel,
      HttpServletResponse response
  ) {
    if (carModelRepository.findById(carModel.getId()).isPresent()) {
      carModelRepository.delete(carModel.fromDto());
      return carModel;
    }
    response.setStatus(404);
    return null;
  }
}
