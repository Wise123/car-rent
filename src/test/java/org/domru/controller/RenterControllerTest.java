package org.domru.controller;

import org.domru.model.Renter;
import org.domru.repository.RenterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RenterController.class)
public class RenterControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private RenterRepository renterRepository;

  @Test
  public void testFindAll() throws Exception {
    Renter renter = new Renter(
        1L,
        "test",
        "test",
        "test"
    );

    List<Renter> allRenters = Collections.singletonList(renter);
    given(renterRepository.findAll()).willReturn(allRenters);

    mvc.perform(get("/renter"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].firstName", is("test")))
        .andExpect(jsonPath("$[0].surname", is("test")))
        .andExpect(jsonPath("$[0].patronymic", is("test")));
  }
}
