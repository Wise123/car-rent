package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.domru.dto.RenterDto;
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

  /**
   * TODO.
   * @return TODO
   */
  @GetMapping("")
  @ApiOperation(value = "получить всех арендаторов")
  public List<RenterDto> findAll() {
    return renterRepository.findAll()
        .stream()
        .map(RenterDto::toDto)
        .collect(Collectors.toList());
  }

  @PostMapping("")
  @ApiOperation(value = "создать арендатора")
  public RenterDto create(
      @RequestBody
      @Valid
          RenterDto renter
  ) {
    renter.setId(null);
    return RenterDto.toDto(renterRepository.save(renter.fromDto()));
  }

  /**
   * TODO.
   * @param renter TODO
   * @param response TODO
   * @return TODO
   */
  @PutMapping("")
  @ApiOperation(value = "обновить арендатора")
  public RenterDto update(
      @RequestBody
      @Valid
          RenterDto renter,
      HttpServletResponse response
  ) {
    if (renterRepository.findById(renter.getId()).isPresent()) {
      return RenterDto.toDto(renterRepository.save(renter.fromDto()));
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
  public RenterDto delete(
      @RequestBody
          RenterDto renter,
      HttpServletResponse response
  ) {
    if (renterRepository.findById(renter.getId()).isPresent()) {
      renterRepository.delete(renter.fromDto());
      return renter;
    }
    response.setStatus(404);
    return null;
  }
}
