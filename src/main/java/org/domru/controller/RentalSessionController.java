package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.domru.dto.RentalSessionDto;

import org.domru.repository.RentalSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rental-session")
@Api(value = "rental-session", description = "Контроллер для работы с сессиями сдачи в аренду")
public class RentalSessionController {

  @Autowired
  RentalSessionRepository rentalSessionRepository;

  @GetMapping("")
  @ApiOperation(value = "получить все сессии аренды")
  List<RentalSessionDto> findAll() {
    return rentalSessionRepository.findAll()
        .stream()
        .map(RentalSessionDto::toDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/findByFilters")
  @ApiOperation(value = "получить все сессии аренды по фильтрам")
  List<RentalSessionDto> findByFilters(
      @RequestParam
      @ApiParam(name = "carId", required = true, value = "идентфикатор автомобиля", example = "1")
        Long carId) {
    return rentalSessionRepository.findByFilters(carId)
        .stream()
        .map(RentalSessionDto::toDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/getAverageSessionLength")
  @ApiOperation(value = "получить среднее время аренды")
  Long getAverageSessionLength(
      @RequestParam
      @ApiParam(
          name = "carManufacturerId",
          required = true,
          value = "идентфикатор производителя автомобиля",
          example = "1"
      )
          Long carManufacturerId,
      @RequestParam
      @ApiParam(
          name = "rentPointId",
          required = true,
          value = "идентфикатор точки аренды автомобиля",
          example = "1"
      )
          Long rentPointId) {
    return rentalSessionRepository.getAverageSessionLength(carManufacturerId, rentPointId);
  }

  @PostMapping("")
  @ApiOperation(value = "создать сессию аренды")
  public RentalSessionDto create(
      @RequestBody
      @Valid
          RentalSessionDto rentalSession
  ) {
    rentalSession.setId(null);
    return RentalSessionDto.toDto(rentalSessionRepository.save(rentalSession.fromDto()));
  }

  @PutMapping("")
  @ApiOperation(value = "обновить сессию аренды")
  RentalSessionDto update(
      @RequestBody
      @Valid
          RentalSessionDto rentalSession,
      HttpServletResponse response
  ) {
    if (rentalSessionRepository.findById(rentalSession.getId()).isPresent()) {
      return RentalSessionDto.toDto(rentalSessionRepository.save(rentalSession.fromDto()));
    }
    response.setStatus(404);
    return null;
  }

  @DeleteMapping("")
  @ApiOperation(value = "удалить сессию аренды")
  RentalSessionDto delete(
      @RequestBody
          RentalSessionDto rentalSession,
      HttpServletResponse response
  ) {
    if (rentalSessionRepository.findById(rentalSession.getId()).isPresent()) {
      rentalSessionRepository.delete(rentalSession.fromDto());
      return rentalSession;
    }
    response.setStatus(404);
    return null;
  }
}
