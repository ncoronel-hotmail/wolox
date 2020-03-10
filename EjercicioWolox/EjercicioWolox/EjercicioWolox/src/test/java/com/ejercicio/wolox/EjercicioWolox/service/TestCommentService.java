package com.ejercicio.wolox.EjercicioWolox.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

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

import com.ejercicio.wolox.EjercicioWolox.model.CommentModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)

public class TestCommentService {

	private static final Log LOGGER = LogFactory.getLog(TestCommentService.class);
	private static TestRestTemplate restTemplate;
	private static ParameterizedTypeReference<List<CommentModel>> commentModel;

	@BeforeAll
	public static void init() {
		LOGGER.info("Inicializando datos iniciales para prueba...");

		restTemplate = new TestRestTemplate();
		commentModel = new ParameterizedTypeReference<List<CommentModel>>() {
		};

	}
	// test que trae todos los Comments

	@Order(1)
	@Test
	public void testAllCommentsOK() throws Exception {

		ResponseEntity<List<CommentModel>> response = restTemplate
				.exchange("https://jsonplaceholder.typicode.com/comments", HttpMethod.GET, null, commentModel);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().size() > 0);

	}
	// test para validar el filtro Comment por Id

	@Order(2)
	@Test
	public void testCommentsById() throws Exception {

		ResponseEntity<CommentModel> response = restTemplate
				.getForEntity("https://jsonplaceholder.typicode.com/comments/1", CommentModel.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().getId());

	}
	// Test para validar cuando se ingresa un id que no existe

	@Order(3)
	@Test
	public void testCommentsSinContenido() throws Exception {

		ResponseEntity<CommentModel> response = restTemplate
				.getForEntity("https://jsonplaceholder.typicode.com/comments/133333333", CommentModel.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}
	// test para validar todos commets de un post

	@Order(4)
	@Test
	public void testCommentsByPostId() throws Exception {

		Integer expectedPostId[] = { 1, 2 };

		ResponseEntity<List<CommentModel>> response = restTemplate.exchange(
				"https://jsonplaceholder.typicode.com/comments?postId=1&postId=2", HttpMethod.GET, null, commentModel);

		List<Integer> LstpostId = response.getBody().stream().map(postId -> postId.getPostId()).distinct()
				.collect(Collectors.toList());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertArrayEquals(expectedPostId, LstpostId.toArray());

	}

	// test para validar todos commets por filtro name

	@Order(5)
	@Test
	public void testCommentsByName() throws Exception {

		ResponseEntity<List<CommentModel>> response = restTemplate.exchange(
				"https://jsonplaceholder.typicode.com/comments?name=id labore ex et quam laborum", HttpMethod.GET, null,
				commentModel);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		response.getBody().forEach(it -> {
			assertEquals("id labore ex et quam laborum", it.getName());
		});

	}

	// Test para validar cuando se ingresa filtro  name que no existe

	@Order(6)
	@Test
	public void testCommentsByNameSinContenido() throws Exception {

		ResponseEntity<List<CommentModel>> response = restTemplate.exchange(
				"https://jsonplaceholder.typicode.com/comments?name=nico123", HttpMethod.GET, null, commentModel);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().size() == 0);

	}

}
