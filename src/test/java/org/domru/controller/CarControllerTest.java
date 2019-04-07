package org.domru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domru.dto.CarDto;
import org.domru.model.Car;
import org.domru.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.DummyObjects;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private CarRepository carRepository;

  @Test
  public void testFindAll() throws Exception {
    List<Car> dummyRenterList = DummyObjects.getDummyCarList();
    BDDMockito.given(carRepository.findAll()).willReturn(dummyRenterList);
    mvc.perform(MockMvcRequestBuilders.get("/car"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(dummyRenterList.get(0).getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].regNum", is(dummyRenterList.get(0).getRegNum())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].carModel", notNullValue()));
  }

  @Test
  public void testCreateValid() throws Exception {
    Car dummyCar = DummyObjects.getDummyCar();
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class))).willReturn(dummyCar);
    CarDto dummyCarDto = DummyObjects.getDummyCarDto();
      mvc.perform(
          MockMvcRequestBuilders.post("/car")
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .content(new ObjectMapper().writeValueAsString(dummyCarDto))
      )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyCar.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.regNum", is(dummyCar.getRegNum())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.carModel", notNullValue()));
  }

  @Test
  public void testCreateNullRegnum() throws Exception {
    Car dummyCar = DummyObjects.getDummyCar();
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class))).willReturn(dummyCar);
    CarDto dummyCarDto = DummyObjects.getDummyCarDto();
    dummyCarDto.setRegNum(null);
    mvc.perform(
        MockMvcRequestBuilders.post("/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateInvalidRegnum() throws Exception {
    Car dummyCar = DummyObjects.getDummyCar();
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class))).willReturn(dummyCar);
    CarDto dummyCarDto = DummyObjects.getDummyCarDto();
    dummyCarDto.setRegNum("");
    mvc.perform(
        MockMvcRequestBuilders.post("/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateNullCarModel() throws Exception {
    Car dummyCar = DummyObjects.getDummyCar();
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class))).willReturn(dummyCar);
    CarDto dummyCarDto = DummyObjects.getDummyCarDto();
    dummyCarDto.setCarModel(null);
    mvc.perform(
        MockMvcRequestBuilders.post("/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateValid() throws Exception {
    Car dummyCar = DummyObjects.getDummyCar();
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class))).willReturn(dummyCar);
    BDDMockito.given(carRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCar));
    CarDto dummyCarDto = DummyObjects.getDummyCarDto();
    mvc.perform(
        MockMvcRequestBuilders.put("/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyCar.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.regNum", is(dummyCar.getRegNum())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.carModel", notNullValue()));
  }

  @Test
  public void testUpdateNullRegnum() throws Exception {
    Car dummyCar = DummyObjects.getDummyCar();
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class))).willReturn(dummyCar);
    BDDMockito.given(carRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCar));
    CarDto dummyCarDto = DummyObjects.getDummyCarDto();
    dummyCarDto.setRegNum(null);
    mvc.perform(
        MockMvcRequestBuilders.put("/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateInvalidRegnum() throws Exception {
    Car dummyCar = DummyObjects.getDummyCar();
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class))).willReturn(dummyCar);
    BDDMockito.given(carRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCar));
    CarDto dummyCarDto = DummyObjects.getDummyCarDto();
    dummyCarDto.setRegNum("");
    mvc.perform(
        MockMvcRequestBuilders.put("/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateNullCarModel() throws Exception {
    Car dummyCar = DummyObjects.getDummyCar();
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class))).willReturn(dummyCar);
    BDDMockito.given(carRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCar));
    CarDto dummyCarDto = DummyObjects.getDummyCarDto();
    dummyCarDto.setCarModel(null);
    mvc.perform(
        MockMvcRequestBuilders.put("/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testDeleteValid() throws Exception {
    Car dummyCar = DummyObjects.getDummyCar();
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class))).willReturn(dummyCar);
    BDDMockito.given(carRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCar));
    CarDto dummyCarDto = DummyObjects.getDummyCarDto();
    mvc.perform(
        MockMvcRequestBuilders.delete("/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyCar.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.regNum", is(dummyCar.getRegNum())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.carModel", notNullValue()));
  }

  @Test
  public void testDeleteInvalidId() throws Exception {
    Car dummyCar = DummyObjects.getDummyCar();
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class))).willReturn(dummyCar);
    BDDMockito.given(carRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCar));
    CarDto dummyCarDto = DummyObjects.getDummyCarDto();
    dummyCarDto.setId(null);
    mvc.perform(
        MockMvcRequestBuilders.delete("/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }
}
