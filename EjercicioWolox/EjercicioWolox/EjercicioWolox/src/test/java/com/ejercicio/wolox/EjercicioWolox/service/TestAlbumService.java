package com.ejercicio.wolox.EjercicioWolox.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ejercicio.wolox.EjercicioWolox.model.AlbumModel;
import com.ejercicio.wolox.EjercicioWolox.model.UserModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)

public class TestAlbumService {

	private static final Log LOGGER = LogFactory.getLog(TestAlbumService.class);

	private static TestRestTemplate restTemplate;
	private static ParameterizedTypeReference<List<AlbumModel>> albumModel;

	@BeforeAll
	public static void init() {
		LOGGER.info("Inicializando datos iniciales para prueba...");

		restTemplate = new TestRestTemplate();
		albumModel = new ParameterizedTypeReference<List<AlbumModel>>() {
		};

	}

	// test que trae todos los albums
	@Order(1)
	@Test
	public void testAllAlbumsOK() throws Exception {

		ResponseEntity<List<AlbumModel>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/albums",
				HttpMethod.GET, null, albumModel);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().size() > 0);

	}

	// test para validar el filtro Album por Id
	@Order(2)
	@Test
	public void testAlbumsById() throws Exception {

		ResponseEntity<UserModel> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/albums/1",
				UserModel.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().getId());

	}

	// Test para validar cuando se ingresa un id que no existe
	@Order(3)
	@Test
	public void testAlbumsSinContenido() throws Exception {

		ResponseEntity<UserModel> response = restTemplate
				.getForEntity("https://jsonplaceholder.typicode.com/albums/13333", UserModel.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}

	// test para validar todos albumes de un user
	@Order(4)
	@Test
	public void testAlbumsByUserId() throws Exception {

		ResponseEntity<List<AlbumModel>> response = restTemplate
				.exchange("https://jsonplaceholder.typicode.com/albums?userId=1", HttpMethod.GET, null, albumModel);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		response.getBody().forEach(it -> {
			assertEquals(1, it.getUserId());
		});

	}

}
