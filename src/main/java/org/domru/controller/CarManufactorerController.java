package org.domru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-manufactorer")
@Api(value = "car-manufactorer", description = "Контроллер для работы с производителями")
public class CarManufactorerController {

  @GetMapping("/test")
  @ApiOperation(value = "тестовый метод")
  public String helloWorld() {
    return "hello";
  }
}
