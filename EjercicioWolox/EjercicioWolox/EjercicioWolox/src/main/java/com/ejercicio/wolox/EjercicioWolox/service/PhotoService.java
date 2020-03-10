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

import com.ejercicio.wolox.EjercicioWolox.model.PhotoModel;
import com.ejercicio.wolox.EjercicioWolox.response.ResponsePhoto;
import com.ejercicio.wolox.EjercicioWolox.response.ResponsePhotoList;
import com.ejercicio.wolox.EjercicioWolox.util.WoloxUtil;

@Service("photoService")
public class PhotoService implements PhotoServiceInterface {
	
	private static final Log LOGGER =LogFactory.getLog(PhotoService.class);


	RestTemplate restTemplate = new RestTemplate();
	ParameterizedTypeReference<List<PhotoModel>> photo = new ParameterizedTypeReference<List<PhotoModel>>() {
	};

	@Autowired
	@Qualifier("responsePhotoList")
	private ResponsePhotoList responsePhotoList;

	@Autowired
	@Qualifier("responsePhoto")
	private ResponsePhoto responsePhoto;

	@Override
	public ResponsePhotoList findAll() {
		LOGGER.info("Se ingreso al servicio  /photoService/findAll "  );

		try {

			ResponseEntity<List<PhotoModel>> response = restTemplate.exchange(WoloxUtil.URL_PHOTOS, HttpMethod.GET, null,
					photo);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responsePhotoList.setPhoto(response.getBody());
				responsePhotoList.setStatus(response.getStatusCode().toString());
			}

		} catch (HttpStatusCodeException e) {
			responsePhotoList.setPhoto(null);
			responsePhotoList.setStatus(e.getStatusCode().toString());
			responsePhotoList.setError(e.getMessage());
		}

		return responsePhotoList;
	}

	@Override
	public ResponsePhotoList findByTipoId(List<Integer> albumId) {
		LOGGER.info("Se ingreso al servicio  /photoService/findByTipoId con parametro albumId  " + albumId.toString()  );

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(WoloxUtil.URL_PHOTOS);

		albumId.forEach(item -> builder.queryParam("albumId", item));
		try {

			ResponseEntity<List<PhotoModel>> response = restTemplate.exchange(builder.build().encode().toUri(),
					HttpMethod.GET, null, photo);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responsePhotoList.setPhoto(response.getBody());
				responsePhotoList.setStatus(response.getStatusCode().toString());
			}
		} catch (HttpStatusCodeException e) {
			responsePhotoList.setPhoto(null);
			responsePhotoList.setStatus(e.getStatusCode().toString());
			responsePhotoList.setError(e.getMessage());
		}

		return responsePhotoList;
	}

	@Override
	public ResponsePhoto findById(Integer id) {
		
		LOGGER.info("Se ingreso al servicio  /photoService/findById con parametro id  " + id );

		Map<String, Integer> vars = new HashMap<String, Integer>();
		vars.put("id", id);
		try {

			ResponseEntity<PhotoModel> response = restTemplate.getForEntity(WoloxUtil.URL_PHOTOS_BY_ID, PhotoModel.class, vars);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responsePhoto.setPhoto(response.getBody());
				responsePhoto.setStatus(response.getStatusCode().toString());
			}
		} catch (HttpStatusCodeException e) {
			responsePhoto.setPhoto(null);
			responsePhoto.setStatus(e.getStatusCode().toString());
			responsePhoto.setError(e.getMessage());
		}

		return responsePhoto;
	}



}
