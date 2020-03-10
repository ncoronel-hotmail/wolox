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

import com.ejercicio.wolox.EjercicioWolox.response.ResponseUser;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserList;
import com.ejercicio.wolox.EjercicioWolox.service.UserServiceInterface;

@RestController
@RequestMapping("/apiUser")
public class UsersRestControllers {
	
	private static final Log LOGGER =LogFactory.getLog(UsersRestControllers.class);


	@Autowired
	@Qualifier("userService")
	private UserServiceInterface userService;

	@GetMapping("/users")
	@ResponseBody
	/**
	 * Devuelve todos los user, forma de acceder: localhost:8080/apiUser/users
	 * Se recupera la informacion de -> https://jsonplaceholder.typicode.com/users
	 * 
	 * @return
	 */
	private ResponseUserList getUsers() {
		
		LOGGER.info("Se ingreso al restController  /apiUser/users "  );

		return userService.findAll();

	}

	@GetMapping("/users/{id}/id")
	@ResponseBody
	
	/**
	 * 
	 * @param id
	 * @return Devuelve un user en particular filtrando por id, forma de acceder:
	 *         localhost:8080/apiUser/users/{id}/id
	 */
	private ResponseUser getUserById(@PathVariable("id") Integer id) {
		LOGGER.info("Se ingreso al restController  /apiUser/users/{id}/id  con los paratros GET id: " + id );

		return userService.findById(id);

	}

}
