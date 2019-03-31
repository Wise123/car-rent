package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.domru.model.RentPoint;
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

  @GetMapping("")
  @ApiOperation(value = "получить все точки")
  public List<RentPoint> findAll() {
    return rentPointRepository.findAll();
  }

  @PostMapping("")
  @ApiOperation(value = "создать точку")
  public RentPoint create(
      @RequestBody
          RentPoint rentPoint
  ) {
    rentPoint.setId(null);
    return rentPointRepository.save(rentPoint);
  }

  /**
   * TODO.
   * @param rentPoint TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить модель")
  public RentPoint update(
      @RequestBody
          RentPoint rentPoint,
      HttpServletResponse response
  ) {
    if (rentPointRepository.findById(rentPoint.getId()).isPresent()) {
      return rentPointRepository.save(rentPoint);
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
  public RentPoint delete(
      @RequestBody
          RentPoint rentPoint,
      HttpServletResponse response
  ) {
    if (rentPointRepository.findById(rentPoint.getId()).isPresent()) {
      rentPointRepository.delete(rentPoint);
      return rentPoint;
    }
    response.setStatus(404);
    return null;
  }
}
