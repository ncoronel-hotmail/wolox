package com.ejercicio.wolox.EjercicioWolox.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.wolox.EjercicioWolox.response.ResponseAlbum;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseAlbumList;
import com.ejercicio.wolox.EjercicioWolox.service.AlbumServiceInterface;
import com.ejercicio.wolox.EjercicioWolox.service.PhotoServiceInterface;

@RestController
@RequestMapping("/apiAlbum")
public class AlbumsRestController {

	private static final Log LOGGER = LogFactory.getLog(AlbumsRestController.class);

	@Autowired
	@Qualifier("albumService")
	private AlbumServiceInterface albumService;

	@Autowired
	@Qualifier("photoService")
	private PhotoServiceInterface photoService;

	@GetMapping("/albums")
	@ResponseBody
	/**
	 * Devuelve todos los albums, forma de acceder: localhost:8080/apiAlbum/albums
	 * Se recupera la informacion de -> https://jsonplaceholder.typicode.com/albums
	 * 
	 * @return
	 */
	private ResponseAlbumList getAlbums() {

		LOGGER.info("Se ingreso al restController  /apiAlbum/albums  ");

		return albumService.findAll();

	}

	@GetMapping("/albums/{id}/id")
	@ResponseBody
	/**
	 * 
	 * @param id
	 * @return Devuelve un album en particular filtrando por id, forma de acceder:
	 *         localhost:8080/albums/{id}/id
	 */
	private ResponseAlbum getAlbumByid(@PathVariable("id") Integer id) {

		LOGGER.info("Se ingreso al restController  /apiAlbum/albums/{id}/id con los paratros GET id: " + id);

		return albumService.findById(id);

	}

	@GetMapping("/albums/{userId}/userId")
	@ResponseBody
	/**
	 * 
	 * @param userId
	 * @return
	 * 
	 *         Devuelve los albumes de un user en particular, la forma de invocarlos
	 *         es : localhost:8080/apiAlbum/albums/{userId}/userId
	 */
	private ResponseAlbumList getAlbumByUserId(@PathVariable("userId") Integer userId) {

		LOGGER.info("Se ingreso al restController  /apiAlbum/albums/{userId}/userId con los paratros GET userId: " + userId);

		return albumService.findByTipoId(userId);

	}

}
