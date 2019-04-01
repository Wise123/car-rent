package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.domru.dto.CarManufacturerDto;
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
  List<CarManufacturerDto> findAll() {
    return carManufacturerRepository.findAll()
        .stream()
        .map(CarManufacturerDto::toDto)
        .collect(Collectors.toList());
  }

  @PostMapping("")
  @ApiOperation(value = "создать производителя")
  CarManufacturerDto create(
      @RequestBody
      @Valid
          CarManufacturerDto carManufacturer
  ) {
    carManufacturer.setId(null);
    return CarManufacturerDto.toDto(carManufacturerRepository.save(carManufacturer.fromDto()));
  }

  @PutMapping("")
  @ApiOperation(value = "обновить производителя")
  CarManufacturerDto update(
      @RequestBody
      @Valid
          CarManufacturerDto carManufacturer,
      HttpServletResponse response
  ) {
    if (carManufacturerRepository.findById(carManufacturer.getId()).isPresent()) {
      return CarManufacturerDto.toDto(carManufacturerRepository.save(carManufacturer.fromDto()));
    }
    response.setStatus(404);
    return null;
  }

  @DeleteMapping("")
  @ApiOperation(value = "удалить производителя")
  CarManufacturerDto delete(
      @RequestBody
          CarManufacturerDto carManufacturer,
      HttpServletResponse response
  ) {
    if (carManufacturerRepository.findById(carManufacturer.getId()).isPresent()) {
      carManufacturerRepository.delete(carManufacturer.fromDto());
      return carManufacturer;
    }
    response.setStatus(404);
    return null;
  }
}
