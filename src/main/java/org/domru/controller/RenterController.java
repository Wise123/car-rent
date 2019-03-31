package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.domru.model.Renter;
import org.domru.repository.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/renter")
@Api(value = "renter", description = "Контроллер для работы с арендаторами")
public class RenterController {

  @Autowired
  RenterRepository renterRepository;

  @GetMapping("")
  @ApiOperation(value = "получить всех арендаторов")
  public List<Renter> findAll() {
    return renterRepository.findAll();
  }

  @PostMapping("")
  @ApiOperation(value = "создать арендатора")
  public Renter create(
      @RequestBody
          Renter renter
  ) {
    renter.setId(null);
    return renterRepository.save(renter);
  }

  /**
   * TODO.
   * @param renter TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить арендатора")
  public Renter update(
      @RequestBody
          Renter renter,
      HttpServletResponse response
  ) {
    if (renterRepository.findById(renter.getId()).isPresent()) {
      return renterRepository.save(renter);
    }
    response.setStatus(404);
    return null;
  }

  /**
   * TODO.
   * @param renter TODO
   * @param response TODO
   * @return TODO
   */
  @DeleteMapping("")
  @ApiOperation(value = "удалить арендатора")
  public Renter delete(
      @RequestBody
          Renter renter,
      HttpServletResponse response
  ) {
    if (renterRepository.findById(renter.getId()).isPresent()) {
      renterRepository.delete(renter);
      return renter;
    }
    response.setStatus(404);
    return null;
  }
}
