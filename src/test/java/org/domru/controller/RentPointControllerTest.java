package org.domru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domru.dto.RentPointDto;
import org.domru.model.RentPoint;
import org.domru.repository.RentPointRepository;
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
@WebMvcTest(RentPointController.class)
public class RentPointControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private RentPointRepository rentPointRepository;

  @Test
  public void testFindAll() throws Exception {
    List<RentPoint> dummyRentPointList = DummyObjects.getDummyRentPointList();
    BDDMockito.given(rentPointRepository.findAll()).willReturn(dummyRentPointList);
    mvc.perform(MockMvcRequestBuilders.get("/rent-point"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(dummyRentPointList.get(0).getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].address", is(dummyRentPointList.get(0).getAddress())));
  }

  @Test
  public void testCreateValid() throws Exception {
    RentPoint dummyRentPoint = DummyObjects.getDummyRentPoint();
    BDDMockito.given(rentPointRepository.save(ArgumentMatchers.any(RentPoint.class))).willReturn(dummyRentPoint);
    RentPointDto dummyRentPointDto = DummyObjects.getDummyRentPointDto();
      mvc.perform(
          MockMvcRequestBuilders.post("/rent-point")
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .content(new ObjectMapper().writeValueAsString(dummyRentPointDto))
      )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyRentPoint.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address", is(dummyRentPoint.getAddress())));
  }

  @Test
  public void testCreateNullAddress() throws Exception {
    RentPoint dummyRentPoint = DummyObjects.getDummyRentPoint();
    BDDMockito.given(rentPointRepository.save(ArgumentMatchers.any(RentPoint.class))).willReturn(dummyRentPoint);
    RentPointDto dummyRentPointDto = DummyObjects.getDummyRentPointDto();
    dummyRentPointDto.setId(null);
    dummyRentPointDto.setAddress(null);
    mvc.perform(
        MockMvcRequestBuilders.post("/rent-point")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRentPointDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateLongAddress() throws Exception {
    RentPoint dummyRentPoint = DummyObjects.getDummyRentPoint();
    BDDMockito.given(rentPointRepository.save(ArgumentMatchers.any(RentPoint.class))).willReturn(dummyRentPoint);
    RentPointDto dummyRentPointDto = DummyObjects.getDummyRentPointDto();
    dummyRentPointDto.setId(null);
    dummyRentPointDto.setAddress(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.post("/rent-point")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRentPointDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateValid() throws Exception {
    RentPoint dummyRentPoint = DummyObjects.getDummyRentPoint();
    BDDMockito.given(rentPointRepository.save(ArgumentMatchers.any(RentPoint.class))).willReturn(dummyRentPoint);
    BDDMockito.given(rentPointRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentPoint));
    RentPointDto dummyRentPointDto = DummyObjects.getDummyRentPointDto();

    mvc.perform(
        MockMvcRequestBuilders.put("/rent-point")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRentPointDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyRentPoint.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address", is(dummyRentPoint.getAddress())));
  }

  @Test
  public void testUpdateNullAddress() throws Exception {
    RentPoint dummyRentPoint = DummyObjects.getDummyRentPoint();
    BDDMockito.given(rentPointRepository.save(ArgumentMatchers.any(RentPoint.class))).willReturn(dummyRentPoint);
    BDDMockito.given(rentPointRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentPoint));
    RentPointDto dummyRentPointDto = DummyObjects.getDummyRentPointDto();
    dummyRentPointDto.setAddress(null);
    mvc.perform(
        MockMvcRequestBuilders.put("/rent-point")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRentPointDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateLongAddress() throws Exception {
    RentPoint dummyRentPoint = DummyObjects.getDummyRentPoint();
    BDDMockito.given(rentPointRepository.save(ArgumentMatchers.any(RentPoint.class))).willReturn(dummyRentPoint);
    BDDMockito.given(rentPointRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentPoint));
    RentPointDto dummyRentPointDto = DummyObjects.getDummyRentPointDto();
    dummyRentPointDto.setAddress(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.put("/rent-point")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRentPointDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testDeleteValid() throws Exception {
    RentPoint dummyRentPoint = DummyObjects.getDummyRentPoint();
    BDDMockito.given(rentPointRepository.save(ArgumentMatchers.any(RentPoint.class))).willReturn(dummyRentPoint);
    BDDMockito.given(rentPointRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentPoint));
    RentPointDto dummyRentPointDto = DummyObjects.getDummyRentPointDto();
    mvc.perform(
        MockMvcRequestBuilders.delete("/rent-point")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRentPointDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyRentPoint.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address", is(dummyRentPoint.getAddress())));
  }

  @Test
  public void testDeleteInvalidId() throws Exception {
    RentPoint dummyRentPoint = DummyObjects.getDummyRentPoint();
    BDDMockito.given(rentPointRepository.save(ArgumentMatchers.any(RentPoint.class))).willReturn(dummyRentPoint);
    BDDMockito.given(rentPointRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentPoint));
    RentPointDto dummyRentPointDto = DummyObjects.getDummyRentPointDto();
    dummyRentPointDto.setId(null);
    mvc.perform(
        MockMvcRequestBuilders.delete("/rent-point")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRentPointDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }
}
