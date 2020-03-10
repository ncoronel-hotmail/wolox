package com.ejercicio.wolox.EjercicioWolox.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.wolox.EjercicioWolox.model.UserAlbumModel;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserAlbum;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserAlbumList;
import com.ejercicio.wolox.EjercicioWolox.service.UserAlbumInterface;

@RestController
@RequestMapping("/apiUserAlbum")
public class UserAlbumRestController {
	
	private static final Log LOGGER =LogFactory.getLog(UserAlbumRestController.class);

	
	@Autowired
	@Qualifier("userAlbumService")
	private UserAlbumInterface userAlbumService;
	
	
	@PostMapping("/usersAlbum/add")
	@ResponseBody
	/**
	 * 
	 * @param userAlbum
	 * @return Devuelve un ResponseUserAlbum luego de ser insertado en la base:
	 *         localhost:8080/apiUserAlbum/usersAlbum
	 */
	private ResponseUserAlbum add(@RequestBody UserAlbumModel userAlbum) {
		
		LOGGER.info("Se ingreso al restController  /apiUserAlbum/add como parametros con POST userAlbum: " +userAlbum.toString()  );


		return userAlbumService.add(userAlbum);

	}
	
	@PutMapping("/usersAlbum/update")
	@ResponseBody
	/**
	 * Metodo que actualiza los permiso de los albumes compartidos
	 * @param userAlbum
	 * @return
	 */
	private ResponseUserAlbum update(@RequestBody UserAlbumModel userAlbum) {
		
		LOGGER.info("Se ingreso al restController  /apiUserAlbum/update como parametros con PUT userAlbum: " +userAlbum.toString()  );


		return userAlbumService.update(userAlbum);

	}
	
	
	@GetMapping("/usersAlbum/permisos")
	@ResponseBody
	/**
	 * Metodo que trae un album particular filtrado por un tipo de permiso
	 * @param albumId
	 * @param isRead
	 * @param isWrite
	 * @return
	 */
	private ResponseUserAlbumList permisos(@RequestParam(name = "albumId") Integer albumId, @RequestParam(name = "isRead" , required=false,defaultValue = "NULL") String isRead,@RequestParam(name = "isWrite",  required=false,defaultValue = "NULL") String isWrite) {

		LOGGER.info("Se ingreso al restController  /apiUserAlbum/permisos como parametros con GET albumId: " + albumId + ", isRead: " + isRead +" , isWrite" + isWrite);

		return userAlbumService.findByIsReadOrIsWriteAndAlbumId(isRead,isWrite,albumId);

	}


}
