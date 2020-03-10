package com.ejercicio.wolox.EjercicioWolox.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.wolox.EjercicioWolox.response.ResponseAlbumList;
import com.ejercicio.wolox.EjercicioWolox.response.ResponsePhoto;
import com.ejercicio.wolox.EjercicioWolox.response.ResponsePhotoList;
import com.ejercicio.wolox.EjercicioWolox.service.AlbumServiceInterface;
import com.ejercicio.wolox.EjercicioWolox.service.PhotoServiceInterface;

@RestController
@RequestMapping("/apiPhoto")
public class PhotoRestController {
	
	private static final Log LOGGER =LogFactory.getLog(PhotoRestController.class);

	
	@Autowired
	@Qualifier("photoService")
	private PhotoServiceInterface photoService;
	
	
	@Autowired
	@Qualifier("albumService")
	private AlbumServiceInterface albumService;
	
	
	@Autowired
	@Qualifier("responseAlbumList")
	private ResponseAlbumList responseAlbumList;
	
	@Autowired
	@Qualifier("responsePhotoList")
	private ResponsePhotoList responsePhotoList;
	
	@GetMapping("/photos")
	@ResponseBody
	
	/**
	 * Devuelve todos los albums, forma de acceder: localhost:8080/apiPhoto/photos
	 * Se recupera la informacion de -> https://jsonplaceholder.typicode.com/photos
	 * 
	 * @return
	 */
	private ResponsePhotoList getPhotos() {
		
		LOGGER.info("Se ingreso al restController  /apiPhoto/photos "  );

		return photoService.findAll();

	}

	@GetMapping("/photos/{id}/id")
	@ResponseBody
	/**
	 * 
	 * @param id
	 * @return Devuelve una photo en particular filtrando por id, forma de acceder:
	 *         localhost:8080/apiPhoto/photos/{id}/id
	 *        
	 */
	private ResponsePhoto getPhotoById(@PathVariable("id") Integer id) {

		LOGGER.info("Se ingreso al restController  /apiPhoto/photos/{id}/id con los paratros GET id: " + id );

		return photoService.findById(id);

	}

	@GetMapping("/photos/{userId}/userId")
	@ResponseBody
	
	/**
	 * 
	 * @param userId
	 * @return Devuelve las photos en un userId particular, forma de acceder:
	 *     
	 *         localhost:8080/apiPhoto/photos/{userId}/userId
	 */
	private ResponsePhotoList getPhotosByUserId(@PathVariable("userId")Integer userId) {
		
		LOGGER.info("Se ingreso al restController  /apiPhoto/photos/{userId}/userId con los paratros GET id: " + userId );

		List<Integer> LstalbumId= new ArrayList<Integer>();
		responseAlbumList= albumService.findByTipoId(userId);
		
		if(responseAlbumList.getAlbums()!=null) {
			LstalbumId= responseAlbumList.getAlbums().stream().map(albumId->albumId.getId()).collect(Collectors.toList());
			responsePhotoList=photoService.findByTipoId(LstalbumId);
		}

		return responsePhotoList;

	}

}
