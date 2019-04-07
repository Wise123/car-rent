package org.domru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.domru.dto.CarManufacturerDto;
import org.domru.model.CarManufacturer;
import org.domru.repository.CarManufacturerRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(CarManufacturerController.class)
public class CarManufactorerControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private CarManufacturerRepository carManufacturerRepository;

  @Test
  public void testFindAll() throws Exception {
    List<CarManufacturer> dummyCarManufactorerList = DummyObjects.getDummyCarmanufacturerList();
    BDDMockito.given(carManufacturerRepository.findAll()).willReturn(dummyCarManufactorerList);
    mvc.perform(MockMvcRequestBuilders.get("/car-manufacturer"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(dummyCarManufactorerList.get(0).getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is(dummyCarManufactorerList.get(0).getName())));
  }

  @Test
  public void testCreateValid() throws Exception {
    CarManufacturer dummyCarManufacturer = DummyObjects.getDummyCarManufacturer();
    BDDMockito.given(carManufacturerRepository.save(ArgumentMatchers.any(CarManufacturer.class))).willReturn(dummyCarManufacturer);
      mvc.perform(
          MockMvcRequestBuilders.post("/car-manufacturer")
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .content(new ObjectMapper().writeValueAsString(DummyObjects.getDummyCarManufacturerDto()))
      )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyCarManufacturer.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(dummyCarManufacturer.getName())));
  }

  @Test
  public void testCreateNullName() throws Exception {
    CarManufacturer dummyCarManufacturer = DummyObjects.getDummyCarManufacturer();
    BDDMockito.given(carManufacturerRepository.save(ArgumentMatchers.any(CarManufacturer.class))).willReturn(dummyCarManufacturer);
    CarManufacturerDto dummyCarManufacturerDto = DummyObjects.getDummyCarManufacturerDto();
    dummyCarManufacturer.setName(null);
    mvc.perform(
        MockMvcRequestBuilders.post("/car-manufacturer")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarManufacturer))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateLongName() throws Exception {
    CarManufacturer dummyCarManufacturer = DummyObjects.getDummyCarManufacturer();
    BDDMockito.given(carManufacturerRepository.save(ArgumentMatchers.any(CarManufacturer.class))).willReturn(dummyCarManufacturer);
    CarManufacturerDto dummyCarManufacturerDto = DummyObjects.getDummyCarManufacturerDto();
    dummyCarManufacturer.setName(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.post("/car-manufacturer")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarManufacturer))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateValid() throws Exception {
    CarManufacturer dummyCarManufacturer = DummyObjects.getDummyCarManufacturer();
    BDDMockito.given(carManufacturerRepository.save(ArgumentMatchers.any(CarManufacturer.class))).willReturn(dummyCarManufacturer);
    BDDMockito.given(carManufacturerRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarManufacturer));
    mvc.perform(
        MockMvcRequestBuilders.put("/car-manufacturer")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(DummyObjects.getDummyCarManufacturerDto()))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyCarManufacturer.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(dummyCarManufacturer.getName())));
  }

  @Test
  public void testUpdateNullName() throws Exception {
    CarManufacturer dummyCarManufacturer = DummyObjects.getDummyCarManufacturer();
    BDDMockito.given(carManufacturerRepository.save(ArgumentMatchers.any(CarManufacturer.class))).willReturn(dummyCarManufacturer);
    BDDMockito.given(carManufacturerRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarManufacturer));

    CarManufacturerDto dummyCarManufacturerDto = DummyObjects.getDummyCarManufacturerDto();
    dummyCarManufacturerDto.setName(null);

    mvc.perform(
        MockMvcRequestBuilders.put("/car-manufacturer")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarManufacturerDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateLongName() throws Exception {
    CarManufacturer dummyCarManufacturer = DummyObjects.getDummyCarManufacturer();
    BDDMockito.given(carManufacturerRepository.save(ArgumentMatchers.any(CarManufacturer.class))).willReturn(dummyCarManufacturer);
    BDDMockito.given(carManufacturerRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarManufacturer));

    CarManufacturerDto dummyCarManufacturerDto = DummyObjects.getDummyCarManufacturerDto();
    dummyCarManufacturerDto.setName(DummyObjects.getLongString());

    mvc.perform(
        MockMvcRequestBuilders.put("/car-manufacturer")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarManufacturerDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testDeleteValid() throws Exception {
    CarManufacturer dummyCarManufacturer = DummyObjects.getDummyCarManufacturer();
    BDDMockito.given(carManufacturerRepository.save(ArgumentMatchers.any(CarManufacturer.class))).willReturn(dummyCarManufacturer);
    BDDMockito.given(carManufacturerRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarManufacturer));
    CarManufacturerDto dummyCarManufacturerDto = DummyObjects.getDummyCarManufacturerDto();
    mvc.perform(
        MockMvcRequestBuilders.delete("/car-manufacturer")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarManufacturerDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyCarManufacturer.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(dummyCarManufacturer.getName())));
  }

  @Test
  public void testDeleteInvalidId() throws Exception {
    CarManufacturer dummyCarManufacturer = DummyObjects.getDummyCarManufacturer();
    BDDMockito.given(carManufacturerRepository.save(ArgumentMatchers.any(CarManufacturer.class))).willReturn(dummyCarManufacturer);
    BDDMockito.given(carManufacturerRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyCarManufacturer));
    CarManufacturerDto dummyCarManufacturerDto = DummyObjects.getDummyCarManufacturerDto();
    dummyCarManufacturerDto.setId(null);
    mvc.perform(
        MockMvcRequestBuilders.delete("/car-manufacturer")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyCarManufacturerDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }
}
