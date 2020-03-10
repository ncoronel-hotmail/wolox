package com.ejercicio.wolox.EjercicioWolox.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ejercicio.wolox.EjercicioWolox.model.PostModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)

public class TestPostService {
	private static final Log LOGGER = LogFactory.getLog(TestPostService.class);
	private static TestRestTemplate restTemplate;
	private static ParameterizedTypeReference<List<PostModel>> postModel;

	@BeforeAll
	public static void init() {
		LOGGER.info("Inicializando datos iniciales para prueba...");

		restTemplate = new TestRestTemplate();
		postModel = new ParameterizedTypeReference<List<PostModel>>() {
		};

	}
	// test que trae todos los Post

	@Order(1)
	@Test
	public void testAllPostOK() throws Exception {

		ResponseEntity<List<PostModel>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts",
				HttpMethod.GET, null, postModel);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().size() > 0);

	}
	// test para validar el filtro Post por Id

	@Order(2)
	@Test
	public void testPostById() throws Exception {

		ResponseEntity<PostModel> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1",
				PostModel.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().getId());

	}
	// Test para validar cuando se ingresa un id que no existe

	@Order(3)
	@Test
	public void testPostSinContenido() throws Exception {

		ResponseEntity<PostModel> response = restTemplate
				.getForEntity("https://jsonplaceholder.typicode.com/posts/134343", PostModel.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}
	// test para validar todos post de un user

	@Order(4)
	@Test
	public void testPostByUserId() throws Exception {

		ResponseEntity<List<PostModel>> response = restTemplate
				.exchange("https://jsonplaceholder.typicode.com/posts?userId=1", HttpMethod.GET, null, postModel);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		response.getBody().forEach(it -> {
			assertEquals(1, it.getUserId());
		});

	}

}
