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

import com.ejercicio.wolox.EjercicioWolox.response.ResponseComment;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseCommentList;
import com.ejercicio.wolox.EjercicioWolox.response.ResponsePostList;
import com.ejercicio.wolox.EjercicioWolox.service.CommentServiceInteface;
import com.ejercicio.wolox.EjercicioWolox.service.PostServiceInterface;

@RestController
@RequestMapping("/apiComment")
public class CommentsRestController {

	private static final Log LOGGER = LogFactory.getLog(CommentsRestController.class);

	@Autowired
	@Qualifier("commentService")
	private CommentServiceInteface commentService;

	@Autowired
	@Qualifier("postService")
	private PostServiceInterface postService;

	@Autowired
	@Qualifier("responseCommentList")
	private ResponseCommentList responseCommentList;

	@Autowired
	@Qualifier("responsePostList")
	private ResponsePostList responsePostList;

	@GetMapping("/comments")
	@ResponseBody

	/**
	 * Devuelve todos los comments, forma de acceder:
	 * localhost:8080/apiComment/comments Se recupera la informacion de ->
	 * https://jsonplaceholder.typicode.com/comments
	 * 
	 * @return
	 */
	private ResponseCommentList getComments() {

		LOGGER.info("Se ingreso al restController  /apiComment/comments  ");

		return commentService.findAll();

	}

	@GetMapping("/comments/{id}/id")
	@ResponseBody
	/**
	 * 
	 * @param id
	 * @return Devuelve un comment en particular filtrando por id, forma de acceder:
	 *         localhost:8080/apiComment/comments/{id}/id
	 */
	private ResponseComment getCommentById(@PathVariable("id") Integer id) {
		LOGGER.info("Se ingreso al restController  /apiComment/comments/{id}/id con los paratros GET id: " + id);

		return commentService.findById(id);

	}

	@GetMapping("/comments/{userId}/userId")
	@ResponseBody

	/**
	 * 
	 * @param userId
	 * @return
	 * 
	 *         Devuelve los comment de un user en particular, la forma de invocarlos
	 *         es : localhost:8080/apiComment/comments/{userId}/userId
	 */
	private ResponseCommentList getCommentsByUserId(@PathVariable("userId") Integer userId) {

		LOGGER.info("Se ingreso al restController  /apiComment/comments/{userId}/userId con los paratros GET userId: " + userId);

		List<Integer> lstPostId = new ArrayList<Integer>();
		responsePostList = postService.findByTipoId(userId);

		if (responsePostList.getPosts() != null) {
			lstPostId = responsePostList.getPosts().stream().map(postId -> postId.getId()).collect(Collectors.toList());
			responseCommentList = commentService.findByTipoId(lstPostId);
		}

		return responseCommentList;

	}

	@GetMapping("/comments/{name}/name")
	@ResponseBody
	/**
	 * 
	 * @param name
	 * @return
	 * 
	 *         Devuelve los comment que sean iguales al campo name, la forma de
	 *         invocarlos es : localhost:8080/apiComment/comments/{name}/name
	 */
	private ResponseCommentList getCommentsByName(@PathVariable("name") String name) {

		LOGGER.info("Se ingreso al restController  /apiComment/comments/{name}/name con los paratros GET name: " + name);

		return commentService.findByName(name);

	}

}
