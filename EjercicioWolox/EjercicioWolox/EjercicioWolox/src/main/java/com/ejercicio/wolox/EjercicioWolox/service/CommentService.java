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

import com.ejercicio.wolox.EjercicioWolox.model.CommentModel;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseComment;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseCommentList;
import com.ejercicio.wolox.EjercicioWolox.util.WoloxUtil;

@Service("commentService")
public class CommentService implements CommentServiceInteface {

	private static final Log LOGGER =LogFactory.getLog(CommentService.class);

	
	RestTemplate restTemplate = new RestTemplate();
	ParameterizedTypeReference<List<CommentModel>> comment = new ParameterizedTypeReference<List<CommentModel>>() {
	};

	@Autowired
	@Qualifier("responseCommentList")
	private ResponseCommentList responseCommentList;

	@Autowired
	@Qualifier("responseComment")
	private ResponseComment responseComment;

	@Override
	public ResponseCommentList findAll() {
		LOGGER.info("Se ingreso al servicio  /commentService/findAll "  );

		try {

			ResponseEntity<List<CommentModel>> response = restTemplate.exchange(WoloxUtil.URL_COMMENTS, HttpMethod.GET, null,
					comment);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responseCommentList.setComments(response.getBody());
				responseCommentList.setStatus(response.getStatusCode().toString());
			}

		} catch (HttpStatusCodeException e) {
			responseCommentList.setComments(null);
			responseCommentList.setStatus(e.getStatusCode().toString());
			responseComment.setError(e.getMessage());
		}

		return responseCommentList;
	}

	@Override
	public ResponseCommentList findByTipoId(List<Integer> postID) {
		
		LOGGER.info("Se ingreso al servicio  /commentService/findByTipoId con parametro PostId  " + postID.toString()  );

		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(WoloxUtil.URL_COMMENTS);

		postID.forEach(item -> builder.queryParam("postId", item));

		try {

			ResponseEntity<List<CommentModel>> response = restTemplate.exchange(builder.build().encode().toUri(),
					HttpMethod.GET, null, comment);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responseCommentList.setComments(response.getBody());
				responseCommentList.setStatus(response.getStatusCode().toString());
			}

		} catch (HttpStatusCodeException e) {
			responseCommentList.setComments(null);
			responseCommentList.setStatus(e.getStatusCode().toString());
			responseComment.setError(e.getMessage());
		}

		return responseCommentList;
	}

	@Override
	public ResponseComment findById(Integer id) {
		
		LOGGER.info("Se ingreso al servicio  /commentService/findById con parametro id  " + id );

		Map<String, Integer> vars = new HashMap<String, Integer>();
		vars.put("id", id);
		try {

			ResponseEntity<CommentModel> response = restTemplate.getForEntity(WoloxUtil.URL_COMMENTS_BY_ID, CommentModel.class,
					vars);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responseComment.setComment(response.getBody());
				responseComment.setStatus(response.getStatusCode().toString());
			}
		} catch (HttpStatusCodeException e) {
			responseComment.setComment(null);
			responseComment.setStatus(e.getStatusCode().toString());
			responseComment.setError(e.getMessage());
		}

		return responseComment;
	}



	@Override
	public ResponseCommentList findByName(String name) {
		
		LOGGER.info("Se ingreso al servicio  /commentService/findByName con parametro name  " + name );

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(WoloxUtil.URL_COMMENTS);
		builder.queryParam("name", name);

		try {

			ResponseEntity<List<CommentModel>> response = restTemplate.exchange(builder.build().encode().toUri(),
					HttpMethod.GET, null, comment);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				
				if(response.getBody().size()<1) {
					responseCommentList.setComments(null);
					responseCommentList.setStatus(HttpStatus.NO_CONTENT.toString());
				}else {
					responseCommentList.setComments(response.getBody());
					responseCommentList.setStatus(response.getStatusCode().toString());
				}
				
				
			}
		} catch (HttpStatusCodeException e) {
			responseCommentList.setComments(null);
			responseCommentList.setStatus(e.getStatusCode().toString());
			responseCommentList.setError(e.getMessage());
			
		}

		return responseCommentList;

	}

	

}
