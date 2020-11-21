package com.jlmeek.migratingjunit4junit5;

import static org.hamcrest.Matchers.containsString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jlmeek.migratingjunit4junit5.controller.WebController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WebController.class)
public class WebControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnHelloJunitTestingWhenHittingDefaultWithAGET() throws Exception{
      this.mockMvc
      .perform(get("/"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("Hello JUnit Testing!")));
  }

}
