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

import com.ejercicio.wolox.EjercicioWolox.response.ResponsePost;
import com.ejercicio.wolox.EjercicioWolox.response.ResponsePostList;
import com.ejercicio.wolox.EjercicioWolox.service.PostServiceInterface;

@RestController
@RequestMapping("/apiPost")
public class PostRestController {
	
	private static final Log LOGGER =LogFactory.getLog(PostRestController.class);


	@Autowired
	@Qualifier("postService")
	private PostServiceInterface postService;

	@GetMapping("/posts")
	@ResponseBody
	/**
	 * Devuelve todos los post, forma de acceder: localhost:8080/apiPost/posts
	 * Se recupera la informacion de -> https://jsonplaceholder.typicode.com/posts
	 * 
	 * @return
	 */
	private ResponsePostList getPost() {
		
		LOGGER.info("Se ingreso al restController  /apiPost/posts "  );

		return postService.findAll();

	}

	@GetMapping("/posts/{id}/id")
	@ResponseBody
	/**
	 * 
	 * @param id
	 * @return Devuelve un post en particular filtrando por id, forma de acceder:
	 *         localhost:8080/apiPost"/posts/{id}/id
	 */
	private ResponsePost getPostByUd(@PathVariable("id") Integer id) {
		LOGGER.info("Se ingreso al restController  /apiPost/posts/{id}/id  con los paratros GET id: " + id );

		return postService.findById(id);

	}

	@GetMapping("/posts/{userId}/userId")
	@ResponseBody
	/**
	 * 
	 * @param userId
	 * @return Devuelve los post en un userId particular, forma de acceder:
	 *     
	 *          localhost:8080/apiPost/posts/{userId}/userId
	 */
	private ResponsePostList getPostByUserId(@PathVariable("userId") Integer userId) {
		LOGGER.info("Se ingreso al restController  /apiPost/posts/{userId}/userId  con los paratros GET userId: " + userId );

		return postService.findByTipoId(userId);

	}

}
