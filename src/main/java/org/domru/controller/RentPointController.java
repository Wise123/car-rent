package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.domru.dto.RentPointDto;
import org.domru.repository.RentPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rent-point")
@Api(value = "rent-point", description = "Контроллер для работы с точками арендодателя")
public class RentPointController {

  @Autowired
  RentPointRepository rentPointRepository;

  /**
   * TODO.
   * @return TODO
   */
  @GetMapping("")
  @ApiOperation(value = "получить все точки")
  public List<RentPointDto> findAll() {
    return rentPointRepository.findAll()
        .stream()
        .map(RentPointDto::toDto)
        .collect(Collectors.toList());
  }

  @PostMapping("")
  @ApiOperation(value = "создать точку")
  public RentPointDto create(
      @RequestBody
      @Valid
          RentPointDto rentPoint
  ) {
    rentPoint.setId(null);
    return RentPointDto.toDto(rentPointRepository.save(rentPoint.fromDto()));
  }

  /**
   * TODO.
   * @param rentPoint TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить модель")
  public RentPointDto update(
      @RequestBody
      @Valid
          RentPointDto rentPoint,
      HttpServletResponse response
  ) {
    if (rentPointRepository.findById(rentPoint.getId()).isPresent()) {
      return RentPointDto.toDto(rentPointRepository.save(rentPoint.fromDto()));
    }
    response.setStatus(404);
    return null;
  }

  /**
   * TODO.
   * @param rentPoint TODO
   * @param response TODO
   * @return TODO
   */
  @DeleteMapping("")
  @ApiOperation(value = "удалить модель")
  public RentPointDto delete(
      @RequestBody
          RentPointDto rentPoint,
      HttpServletResponse response
  ) {
    if (rentPointRepository.findById(rentPoint.getId()).isPresent()) {
      rentPointRepository.delete(rentPoint.fromDto());
      return rentPoint;
    }
    response.setStatus(404);
    return null;
  }
}
