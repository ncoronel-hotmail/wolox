package com.ejercicio.wolox.EjercicioWolox.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ejercicio.wolox.EjercicioWolox.model.PostModel;
import com.ejercicio.wolox.EjercicioWolox.response.ResponsePost;
import com.ejercicio.wolox.EjercicioWolox.response.ResponsePostList;
import com.ejercicio.wolox.EjercicioWolox.util.WoloxUtil;

@Service("postService")
public class PostService implements PostServiceInterface {
	
	private static final Log LOGGER =LogFactory.getLog(PostService.class);


	RestTemplate restTemplate = new RestTemplate();
	ParameterizedTypeReference<List<PostModel>> post = new ParameterizedTypeReference<List<PostModel>>() {
	};

	@Autowired
	@Qualifier("responsePostList")
	ResponsePostList responsePostList;

	@Autowired
	@Qualifier("responsePost")
	ResponsePost responsePost;

	@Override
	public ResponsePostList findAll() {
		LOGGER.info("Se ingreso al servicio  /postService/findAll "  );

		try {
			ResponseEntity<List<PostModel>> response = restTemplate.exchange(WoloxUtil.URL_POST, HttpMethod.GET, null, post);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responsePostList.setPosts(response.getBody());
				responsePostList.setStatus(response.getStatusCode().toString());
			}

		} catch (HttpStatusCodeException e) {
			responsePostList.setPosts(null);
			responsePostList.setStatus(e.getStatusCode().toString());
			responsePostList.setError(e.getMessage());
		}

		return responsePostList;
	}

	@Override
	public ResponsePostList findByTipoId(Integer userId) {
		LOGGER.info("Se ingreso al servicio  /postService/findByTipoId con parametro userId  " + userId );

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(WoloxUtil.URL_POST);
		builder.queryParam("userId", userId);
		try {
			ResponseEntity<List<PostModel>> response = restTemplate.exchange(builder.build().encode().toUri(),
					HttpMethod.GET, null, post);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responsePostList.setPosts(response.getBody());
				responsePostList.setStatus(response.getStatusCode().toString());
			}
		} catch (HttpStatusCodeException e) {
			responsePostList.setPosts(null);
			responsePostList.setStatus(e.getStatusCode().toString());
			responsePostList.setError(e.getMessage());
		}

		return responsePostList;
	}

	@Override
	public ResponsePost findById(Integer id) {
		
		LOGGER.info("Se ingreso al servicio  /postService/findById con parametro id  " + id );

		Map<String, Integer> vars = new HashMap<String, Integer>();
		vars.put("id", id);
		try {

			ResponseEntity<PostModel> response = restTemplate.getForEntity(WoloxUtil.URL_POST_BY_ID, PostModel.class, vars);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responsePost.setPost(response.getBody());
				responsePost.setStatus(response.getStatusCode().toString());
			}
		} catch (HttpStatusCodeException e) {
			responsePost.setPost(null);
			responsePost.setStatus(e.getStatusCode().toString());
			responsePost.setError(e.getMessage());
		}

		return responsePost;

	}

}
