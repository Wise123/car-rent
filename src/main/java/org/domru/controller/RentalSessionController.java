package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.domru.model.RentalSession;
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
  public List<RentalSession> findAll() {
    return rentalSessionRepository.findAll();
  }

  @GetMapping("/findByFilters")
  @ApiOperation(value = "получить все сессии аренды по фильтрам")
  public List<RentalSession> findByFilters(
      @RequestParam
      @ApiParam(name = "carId", required = true, value = "идентфикатор автомобиля", example = "1")
        Long carId) {
    return rentalSessionRepository.findByFilters(carId);
  }

  @PostMapping("")
  @ApiOperation(value = "создать сессию аренды")
  public RentalSession create(
      @RequestBody
          RentalSession rentalSession
  ) {
    rentalSession.setId(null);
    return rentalSessionRepository.save(rentalSession);
  }

  /**
   * TODO.
   * @param rentalSession TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить арендатора")
  public RentalSession update(
      @RequestBody
          RentalSession rentalSession,
      HttpServletResponse response
  ) {
    if (rentalSessionRepository.findById(rentalSession.getId()).isPresent()) {
      return rentalSessionRepository.save(rentalSession);
    }
    response.setStatus(404);
    return null;
  }

  /**
   * TODO.
   * @param rentalSession TODO
   * @param response TODO
   * @return TODO
   */
  @DeleteMapping("")
  @ApiOperation(value = "удалить арендатора")
  public RentalSession delete(
      @RequestBody
          RentalSession rentalSession,
      HttpServletResponse response
  ) {
    if (rentalSessionRepository.findById(rentalSession.getId()).isPresent()) {
      rentalSessionRepository.delete(rentalSession);
      return rentalSession;
    }
    response.setStatus(404);
    return null;
  }
}
