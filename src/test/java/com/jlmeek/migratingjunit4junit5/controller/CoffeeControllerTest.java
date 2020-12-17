package com.jlmeek.migratingjunit4junit5.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CoffeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnHelloJunitTestingWhenHittingDefaultWithAGET() throws Exception{
      this.mockMvc
              .perform(get("/"))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.[0].coffeeName", containsString("Charbucks Burnt Roast")))
              .andExpect(jsonPath("$.[0].coffeeType", containsString("Dark Roast")))
              .andExpect(jsonPath("$.[0].coffeeRating", comparesEqualTo(2.0)));
  }

  @Test
  public void shouldReturnASingleCoffeeWhenHittingWithAGETResource() throws Exception {
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
  public void shouldThrowExecptionWhenHittingDefaultWithAPUT() throws Exception {
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
  public void shouldThrowExceptionWhenHittingIdEndpointWithALetter() throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/a");
    
    this.mockMvc
            .perform(builder)
            .andDo(print())
            .andExpect(status().is(400))
            .andExpect( result -> Assert.assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));

  }

}
