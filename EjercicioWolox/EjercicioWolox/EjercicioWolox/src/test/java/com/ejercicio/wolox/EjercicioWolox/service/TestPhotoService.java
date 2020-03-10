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

import com.ejercicio.wolox.EjercicioWolox.model.PhotoModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)

public class TestPhotoService {

	private static final Log LOGGER = LogFactory.getLog(TestPhotoService.class);
	private static TestRestTemplate restTemplate;
	private static ParameterizedTypeReference<List<PhotoModel>> photoModel;

	@BeforeAll
	public static void init() {
		LOGGER.info("Inicializando datos iniciales para prueba...");

		restTemplate = new TestRestTemplate();
		photoModel = new ParameterizedTypeReference<List<PhotoModel>>() {
		};

	}
	// test que trae todos las photos
	@Order(1)
	@Test
	public void testAllPhotosOK() throws Exception {

		ResponseEntity<List<PhotoModel>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/photos",
				HttpMethod.GET, null, photoModel);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().size() > 0);

	}
	// test para validar el filtro photo por Id

	@Order(2)
	@Test
	public void testAlbumsById() throws Exception {

		ResponseEntity<PhotoModel> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/photos/1",
				PhotoModel.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().getId());

	}
	// Test para validar cuando se ingresa un id que no existe

	@Order(3)
	@Test
	public void testPhotoSinContenido() throws Exception {

		ResponseEntity<PhotoModel> response = restTemplate
				.getForEntity("https://jsonplaceholder.typicode.com/photos/133333333", PhotoModel.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}
	// test para validar todas las photo de un album

	@Order(4)
	@Test
	public void testPhotoByAlbumID() throws Exception {

		Integer expectedPostId[] = { 1, 2 };

		ResponseEntity<List<PhotoModel>> response = restTemplate.exchange(
				"https://jsonplaceholder.typicode.com/photos?albumId=1&albumId=2", HttpMethod.GET, null, photoModel);

		List<Integer> LstpostId = response.getBody().stream().map(albumId -> albumId.getAlbumId()).distinct()
				.collect(Collectors.toList());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertArrayEquals(expectedPostId, LstpostId.toArray());

	}

}
