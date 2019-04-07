package org.domru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.domru.dto.RentalSessionDto;
import org.domru.model.RentalSession;
import org.domru.repository.RentalSessionRepository;
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

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RentalSessionController.class)
public class RentalSessionControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private RentalSessionRepository rentalSessionRepository;

  @Test
  public void testFindAll() throws Exception {
    List<RentalSession> dummyRentalSessionList = DummyObjects.getDummyRentalSessionList();
    List<RentalSessionDto> dummyRentalSessionDtoList = DummyObjects.getDummyRentalSessionDtoList();
    BDDMockito.given(rentalSessionRepository.findAll()).willReturn(dummyRentalSessionList);
    mvc.perform(MockMvcRequestBuilders.get("/rental-session"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(dummyRentalSessionDtoList.get(0).getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].sessionStart", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].sessionEnd", nullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].startRentPoint", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].endRentPoint", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].renter", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].car", notNullValue()))
    ;
  }

  @Test
  public void testFindByFilters() throws Exception {
    List<RentalSession> dummyRentalSessionList = DummyObjects.getDummyRentalSessionList();
    List<RentalSessionDto> dummyRentalSessionDtoList = DummyObjects.getDummyRentalSessionDtoList();
    BDDMockito.given(rentalSessionRepository.findByFilters(ArgumentMatchers.any(Long.class))).willReturn(dummyRentalSessionList);
    mvc.perform(MockMvcRequestBuilders.get("/rental-session/findByFilters?carId=1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(dummyRentalSessionDtoList.get(0).getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].sessionStart", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].sessionEnd", nullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].startRentPoint", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].endRentPoint", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].renter", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].car", notNullValue()))
    ;
  }

  @Test
  public void testGetAverageSessionLength() throws Exception {
    List<RentalSession> dummyRentalSessionList = DummyObjects.getDummyRentalSessionList();
    List<RentalSessionDto> dummyRentalSessionDtoList = DummyObjects.getDummyRentalSessionDtoList();
    BDDMockito.given(rentalSessionRepository.getAverageSessionLength(
        ArgumentMatchers.any(Long.class),
        ArgumentMatchers.any(Long.class)
    )).willReturn(1L);
    mvc.perform(MockMvcRequestBuilders.get("/rental-session/findByFilters?carId=1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testCreateValid() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    BDDMockito.given(rentalSessionRepository.save(ArgumentMatchers.any(RentalSession.class))).willReturn(dummyRentalSession);
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.post("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyRentalSessionDto.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.sessionStart", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.sessionEnd", nullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.startRentPoint", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.endRentPoint", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.renter", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.car", notNullValue()))
    ;
  }

  @Test
  public void testCreateNullSessionStart() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    dummyRentalSessionDto.setSessionStart(null);
    BDDMockito.given(rentalSessionRepository.save(ArgumentMatchers.any(RentalSession.class))).willReturn(dummyRentalSession);
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.post("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateNullStartRentPoint() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    dummyRentalSessionDto.setStartRentPoint(null);
    BDDMockito.given(rentalSessionRepository.save(ArgumentMatchers.any(RentalSession.class))).willReturn(dummyRentalSession);
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.post("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateNullRenter() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    dummyRentalSessionDto.setRenter(null);
    BDDMockito.given(rentalSessionRepository.save(ArgumentMatchers.any(RentalSession.class))).willReturn(dummyRentalSession);
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.post("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateNullCar() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    dummyRentalSessionDto.setCar(null);
    BDDMockito.given(rentalSessionRepository.save(ArgumentMatchers.any(RentalSession.class))).willReturn(dummyRentalSession);
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.post("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateValid() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    BDDMockito.given(rentalSessionRepository.save(ArgumentMatchers.any(RentalSession.class))).willReturn(dummyRentalSession);
    BDDMockito.given(rentalSessionRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentalSession));
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.put("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyRentalSessionDto.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.sessionStart", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.sessionEnd", nullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.startRentPoint", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.endRentPoint", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.renter", notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.car", notNullValue()))
    ;
  }

  @Test
  public void testUpdateNullSessionStart() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    dummyRentalSessionDto.setSessionStart(null);
    BDDMockito.given(rentalSessionRepository.save(ArgumentMatchers.any(RentalSession.class))).willReturn(dummyRentalSession);
    BDDMockito.given(rentalSessionRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentalSession));
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.put("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateNullStartRentPoint() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    dummyRentalSessionDto.setStartRentPoint(null);
    BDDMockito.given(rentalSessionRepository.save(ArgumentMatchers.any(RentalSession.class))).willReturn(dummyRentalSession);
    BDDMockito.given(rentalSessionRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentalSession));
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.put("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateNullRenter() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    dummyRentalSessionDto.setRenter(null);
    BDDMockito.given(rentalSessionRepository.save(ArgumentMatchers.any(RentalSession.class))).willReturn(dummyRentalSession);
    BDDMockito.given(rentalSessionRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentalSession));
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.put("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateNullCar() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    dummyRentalSessionDto.setCar(null);
    BDDMockito.given(rentalSessionRepository.save(ArgumentMatchers.any(RentalSession.class))).willReturn(dummyRentalSession);
    BDDMockito.given(rentalSessionRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentalSession));
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.put("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testDeleteValid() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    BDDMockito.given(rentalSessionRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentalSession));
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.delete("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testDeleteInvalid() throws Exception {
    RentalSession dummyRentalSession = DummyObjects.getDummyRentalSession();
    RentalSessionDto dummyRentalSessionDto = DummyObjects.getDummyRentalSessionDto();
    dummyRentalSessionDto.setId(null);
    BDDMockito.given(rentalSessionRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRentalSession));
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mvc.perform(
        MockMvcRequestBuilders.delete("/rental-session")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(mapper.writeValueAsString(dummyRentalSessionDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }
}
