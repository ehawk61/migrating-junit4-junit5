package com.jlmeek.migratingjunit4junit5.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CoffeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("GET / - 200 ok, returns all the coffees")
  public void shouldReturnAllCoffeesWhenHittingDefaultResourceWithGET() throws Exception{
      this.mockMvc
              .perform(get("/"))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$", hasSize(3)))
              .andExpect(jsonPath("$.[0].coffeeName", containsString("Charbucks Burnt Roast")))
              .andExpect(jsonPath("$.[0].coffeeType", containsString("Dark Roast")))
              .andExpect(jsonPath("$.[0].coffeeRating", comparesEqualTo(2.0)));
  }

  @Test
  @DisplayName("GET /2 - 200 OK, returns coffee listed by id 2")
  public void shouldReturnASingleCoffeeWhenHittingIdResourceWithGET() throws Exception {
      this.mockMvc
              .perform(get("/2"))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.coffeeId", comparesEqualTo(2)))
              .andExpect(jsonPath("$.coffeeName", containsString("Blumpkin Dounts Dark Roast")))
              .andExpect(jsonPath("$.coffeeType", containsString("Dark Roast")))
              .andExpect(jsonPath("$.coffeeRating", comparesEqualTo(4.0)));
  }

  @Test
  @DisplayName("GET /bestCoffee - 200 OK, returns highest rated coffee")
  public void shouldReturnASingleCoffeeWhenHittingWithBestCoffeeResourceWithGET() throws Exception {
      this.mockMvc
              .perform(get("/bestCoffee"))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.coffeeName", containsString("Cafe Ladero")))
              .andExpect(jsonPath("$.coffeeType", containsString("Medium Roast")))
              .andExpect(jsonPath("$.coffeeRating", comparesEqualTo(5.0)));
  }

  @Test
  @DisplayName("PUT on / - 405 Method Not Allowed")
  public void shouldThrowExecptionWhenHittingDefaultWithPUT() throws Exception {
      MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.ALL_VALUE)
        .content("123");
    
      this.mockMvc
              .perform(builder).andDo(print())
              .andExpect(status().is(405))
              .andExpect(status().reason(containsString("Request method 'PUT' not supported")));
  }

  @Test
  @DisplayName("GET /a - 400 Bad Request & Method Argument Type Mismatch")
  public void shouldThrowExceptionWhenHittingIdEndpointWithALetter() throws Exception {
      MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/a");
    
      this.mockMvc
              .perform(builder)
              .andDo(print())
              .andExpect(status().is(400))
              .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));

  }

}
