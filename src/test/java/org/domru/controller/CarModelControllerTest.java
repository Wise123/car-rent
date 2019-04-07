package org.domru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domru.dto.CarModelDto;
import org.domru.model.CarModel;
import org.domru.repository.CarModelRepository;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(CarModelController.class)
public class CarModelControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private CarModelRepository carModelRepository;

  @Test
  public void testFindAll() throws Exception {
    List<CarModel> dummyCarModelList = DummyObjects.getDummyCarModelList();
    BDDMockito.given(carModelRepository.findAll()).willReturn(dummyCarModelList);
    mvc.perform(MockMvcRequestBuilders.get("/car-model"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(dummyCarModelList.get(0).getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is(dummyCarModelList.get(0).getName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].carManufacturers", hasSize(1)));
  }

  @Test
  public void testCreateValid() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    mvc.perform(
        MockMvcRequestBuilders.post("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyCarModel.getId().intValue())))
      .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(dummyCarModel.getName())))
      .andExpect(MockMvcResultMatchers.jsonPath("$.carManufacturers", hasSize(1)));
  }

  @Test
  public void testCreateNullName() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    dummyCarModelDto.setName(null);
    mvc.perform(
        MockMvcRequestBuilders.post("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateLongName() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    dummyCarModelDto.setName(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.post("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateNullCarManufactorers() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    dummyCarModelDto.setCarManufacturers(null);
    mvc.perform(
        MockMvcRequestBuilders.post("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateEmptyCarManufactorers() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    dummyCarModelDto.setCarManufacturers(new ArrayList<>());
    mvc.perform(
        MockMvcRequestBuilders.post("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateValid() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    BDDMockito.given(carModelRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarModel));
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    mvc.perform(
        MockMvcRequestBuilders.put("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyCarModel.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(dummyCarModel.getName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.carManufacturers", hasSize(1)));
  }

  @Test
  public void testUpdateNullName() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    BDDMockito.given(carModelRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarModel));
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    dummyCarModelDto.setName(null);
    mvc.perform(
        MockMvcRequestBuilders.put("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateLongName() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    BDDMockito.given(carModelRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarModel));
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    dummyCarModelDto.setName(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.put("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateNullCarManufaturers() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    BDDMockito.given(carModelRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarModel));
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    dummyCarModelDto.setCarManufacturers(null);
    mvc.perform(
        MockMvcRequestBuilders.put("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateEmptyCarManufaturers() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    BDDMockito.given(carModelRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarModel));
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    dummyCarModelDto.setCarManufacturers(new ArrayList<>());
    mvc.perform(
        MockMvcRequestBuilders.put("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testDeleteValid() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    BDDMockito.given(carModelRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarModel));
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    mvc.perform(
        MockMvcRequestBuilders.delete("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyCarModel.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(dummyCarModel.getName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.carManufacturers", hasSize(1)));
  }
  @Test
  public void testDeleteInvalidId() throws Exception {
    CarModel dummyCarModel = DummyObjects.getDummyCarModel();
    BDDMockito.given(carModelRepository.save(ArgumentMatchers.any(CarModel.class))).willReturn(dummyCarModel);
    BDDMockito.given(carModelRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarModel));
    CarModelDto dummyCarModelDto = DummyObjects.getDummyCarModelDto();
    dummyCarModelDto.setId(null);
    mvc.perform(
        MockMvcRequestBuilders.delete("/car-model")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarModelDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }
}
