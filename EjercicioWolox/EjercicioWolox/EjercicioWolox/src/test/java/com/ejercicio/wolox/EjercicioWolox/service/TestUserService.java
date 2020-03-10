package com.ejercicio.wolox.EjercicioWolox.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ejercicio.wolox.EjercicioWolox.model.UserModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)

public class TestUserService {

	@Autowired
	private TestRestTemplate restTemplate;

	ParameterizedTypeReference<List<UserModel>> user = new ParameterizedTypeReference<List<UserModel>>() {
	};

	// test que trae todos los User
	@Order(1)
	@Test
	public void testAllUserOK() throws Exception {

		ResponseEntity<List<UserModel>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/users",
				HttpMethod.GET, null, user);

		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	// test para validar el filtro Post por Id

	@Order(2)
	@Test
	public void testUserById() throws Exception {

		ResponseEntity<UserModel> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users/1",
				UserModel.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().getId());

	}

	// Test para validar cuando se ingresa un id que no existe

	@Order(3)
	@Test
	public void testUserSinContenido() throws Exception {

		ResponseEntity<UserModel> response = restTemplate
				.getForEntity("https://jsonplaceholder.typicode.com/users/222222", UserModel.class);

		assertNotEquals(HttpStatus.OK, response.getStatusCode());

	}

}
