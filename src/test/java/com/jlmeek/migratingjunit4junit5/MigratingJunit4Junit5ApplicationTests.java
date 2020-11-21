package com.jlmeek.migratingjunit4junit5;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.jlmeek.migratingjunit4junit5.controller.WebController;

@SpringBootTest
public class MigratingJunit4Junit5ApplicationTests {

	@Autowired 
	private WebController controller;  

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
