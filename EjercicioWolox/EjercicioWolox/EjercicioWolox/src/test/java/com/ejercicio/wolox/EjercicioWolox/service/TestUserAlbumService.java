package com.ejercicio.wolox.EjercicioWolox.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.ejercicio.wolox.EjercicioWolox.model.UserAlbumModel;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserAlbum;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserAlbumList;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestUserAlbumService {
	private static final Log LOGGER = LogFactory.getLog(TestUserAlbumService.class);

	@Autowired
	private UserAlbumInterface userAlbumService;

	private static UserAlbumModel userAlbum;

	@BeforeAll
	public static void init() {
		LOGGER.info("Inicializando datos iniciales para prueba...");

		userAlbum = new UserAlbumModel();
		userAlbum.setIsRead(false);
		userAlbum.setIsWrite(false);
		userAlbum.setUserOwnerId(1);
		userAlbum.setUserInvitedId((int) (Math.random() * 1000 + 1));
		userAlbum.setAlbumId((int) (Math.random() * 100 + 1));

	}

	// test para agregar un objeto UserAlbum
	@Order(1)
	@Test
	public void testAddUserAlbum_OK() throws Exception {

		ResponseUserAlbum result = userAlbumService.add(userAlbum);
		assertNotNull(result);
		assertEquals(HttpStatus.CREATED.toString(), result.getStatus().toString());

	}
	// test para actualizar un objeto UserAlbum

	@Order(2)
	@Test
	public void testUpdateUserAlbum() throws Exception {

		UserAlbumModel userAlbumTest = userAlbum;
		userAlbumTest.setIsRead(true);
		userAlbumTest.setIsWrite(true);

		ResponseUserAlbum result = userAlbumService.update(userAlbumTest);

		assertNotNull(result);
		assertEquals(HttpStatus.OK.toString(), result.getStatus().toString());

	}

	// Test para encontrar un objeto UserAlbum filtrandolo por albumID y
	// UserInvitedId
	@Order(3)
	@Test
	public void testFindByAlbumIdAndUserInvitedIdTest() throws Exception {

		ResponseUserAlbum result = userAlbumService.findByAlbumIdAndUserInvitedId(userAlbum);
		assertNotNull(result);
		assertEquals(HttpStatus.OK.toString(), result.getStatus().toString());

	}



	// Test para validar la constraint, no puede repetir un albumId+userOwnerId+
	@Order(4)
	@Test
	public void testAddUserAlbumErrorUniqueConstraint() throws Exception {

		ResponseUserAlbum result = userAlbumService.add(userAlbum);

		assertNull(result.getUserAlbum());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.toString(), result.getError());

	}

// Test para validar restriccion de campos no null
	@Order(5)
	@Test
	public void testaddUserAlbumValuesNull() throws Exception {

		UserAlbumModel userAlbumTest = userAlbum;
		userAlbumTest.setUserInvitedId(null);

		ResponseUserAlbum result = userAlbumService.add(userAlbumTest);

		assertNull(result.getUserAlbum());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.toString(), result.getError());

	}

}
