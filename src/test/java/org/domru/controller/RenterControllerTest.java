package org.domru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domru.dto.RenterDto;
import org.domru.model.Renter;
import org.domru.repository.RenterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;

import org.mockito.BDDMockito;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.DummyObjects;

@RunWith(SpringRunner.class)
@WebMvcTest(RenterController.class)
public class RenterControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private RenterRepository renterRepository;

  @Test
  public void testFindAll() throws Exception {
    List<Renter> dummyRenterList = DummyObjects.getDummyRenterList();
    BDDMockito.given(renterRepository.findAll()).willReturn(dummyRenterList);
    mvc.perform(MockMvcRequestBuilders.get("/renter"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(dummyRenterList.get(0).getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", is(dummyRenterList.get(0).getFirstName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname", is(dummyRenterList.get(0).getSurname())))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].patronymic", is(dummyRenterList.get(0).getPatronymic())));
  }

  @Test
  public void testCreateValid() throws Exception {
    Renter dummyRenter = DummyObjects.getDummyRenter();
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(dummyRenter);
      mvc.perform(
          MockMvcRequestBuilders.post("/renter")
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .content(new ObjectMapper().writeValueAsString(DummyObjects.getDummyRenterDto()))
      )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyRenter.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is(dummyRenter.getFirstName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.surname", is(dummyRenter.getSurname())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patronymic", is(dummyRenter.getPatronymic())));
  }

  @Test
  public void testCreateNullFirstName() throws Exception {
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(DummyObjects.getDummyRenter());
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setId(null);
    dummyRenterDto.setFirstName(null);
    mvc.perform(
        MockMvcRequestBuilders.post("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateLongFirstName() throws Exception {
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(DummyObjects.getDummyRenter());
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setId(null);
    dummyRenterDto.setFirstName(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.post("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateNullSurame() throws Exception {
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(DummyObjects.getDummyRenter());
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setId(null);
    dummyRenterDto.setSurname(null);
    mvc.perform(
        MockMvcRequestBuilders.post("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateLongSurname() throws Exception {
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(DummyObjects.getDummyRenter());
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setId(null);
    dummyRenterDto.setSurname(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.post("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateNullPatronymic() throws Exception {
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(DummyObjects.getDummyRenter());
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setId(null);
    dummyRenterDto.setPatronymic(null);
    mvc.perform(
        MockMvcRequestBuilders.post("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testCreateLongPatronymic() throws Exception {
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(DummyObjects.getDummyRenter());
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setId(null);
    dummyRenterDto.setPatronymic(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.post("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateValid() throws Exception {
    Renter dummyRenter = DummyObjects.getDummyRenter();
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(dummyRenter);
    BDDMockito.given(renterRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRenter));
    mvc.perform(
        MockMvcRequestBuilders.put("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(DummyObjects.getDummyRenterDto()))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyRenter.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is(dummyRenter.getFirstName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.surname", is(dummyRenter.getSurname())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patronymic", is(dummyRenter.getPatronymic())));
  }

  @Test
  public void testUpdateNullFirstName() throws Exception {
    Renter dummyRenter = DummyObjects.getDummyRenter();
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(dummyRenter);
    BDDMockito.given(renterRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRenter));
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setFirstName(null);
    mvc.perform(
        MockMvcRequestBuilders.put("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateLongFirstName() throws Exception {
    Renter dummyRenter = DummyObjects.getDummyRenter();
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(dummyRenter);
    BDDMockito.given(renterRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRenter));
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setFirstName(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.put("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateNullSurame() throws Exception {
    Renter dummyRenter = DummyObjects.getDummyRenter();
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(dummyRenter);
    BDDMockito.given(renterRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRenter));
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setSurname(null);
    mvc.perform(
        MockMvcRequestBuilders.put("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateLongSurname() throws Exception {
    Renter dummyRenter = DummyObjects.getDummyRenter();
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(dummyRenter);
    BDDMockito.given(renterRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRenter));
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setSurname(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.put("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateNullPatronymic() throws Exception {
    Renter dummyRenter = DummyObjects.getDummyRenter();
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(dummyRenter);
    BDDMockito.given(renterRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRenter));
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setPatronymic(null);
    mvc.perform(
        MockMvcRequestBuilders.put("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testUpdateLongPatronymic() throws Exception {
    Renter dummyRenter = DummyObjects.getDummyRenter();
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(dummyRenter);
    BDDMockito.given(renterRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRenter));
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setPatronymic(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.put("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testDeleteValid() throws Exception {
    Renter dummyRenter = DummyObjects.getDummyRenter();
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(dummyRenter);
    BDDMockito.given(renterRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRenter));
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    mvc.perform(
        MockMvcRequestBuilders.delete("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(dummyRenter.getId().intValue())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is(dummyRenter.getFirstName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.surname", is(dummyRenter.getSurname())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patronymic", is(dummyRenter.getPatronymic())));;
  }

  @Test
  public void testDeleteInvalidId() throws Exception {
    Renter dummyRenter = DummyObjects.getDummyRenter();
    BDDMockito.given(renterRepository.save(ArgumentMatchers.any(Renter.class))).willReturn(dummyRenter);
    BDDMockito.given(renterRepository.findById(ArgumentMatchers.any(Long.class))).willReturn(Optional.of(dummyRenter));
    RenterDto dummyRenterDto = DummyObjects.getDummyRenterDto();
    dummyRenterDto.setId(null);
    dummyRenterDto.setPatronymic(DummyObjects.getLongString());
    mvc.perform(
        MockMvcRequestBuilders.delete("/renter")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(dummyRenterDto))
    )
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }
}
