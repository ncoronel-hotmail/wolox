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

import com.ejercicio.wolox.EjercicioWolox.model.AlbumModel;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseAlbum;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseAlbumList;
import com.ejercicio.wolox.EjercicioWolox.util.WoloxUtil;

@Service("albumService")
public class AlbumService implements AlbumServiceInterface {
	
	private static final Log LOGGER =LogFactory.getLog(AlbumService.class);


	RestTemplate restTemplate = new RestTemplate();
	ParameterizedTypeReference<List<AlbumModel>> album = new ParameterizedTypeReference<List<AlbumModel>>() {
	};

	@Autowired
	@Qualifier("responseAlbumList")
	ResponseAlbumList responseAlbumList;

	@Autowired
	@Qualifier("responseAlbum")
	ResponseAlbum responseAlbum;

	@Override
	public ResponseAlbumList findAll() {

		LOGGER.info("Se ingreso al servicio  /albumService/findAll "  );

		try {

			ResponseEntity<List<AlbumModel>> response = restTemplate.exchange(WoloxUtil.URL_ALBUMS, HttpMethod.GET, null,
					album);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responseAlbumList.setAlbums(response.getBody());
				responseAlbumList.setStatus(response.getStatusCode().toString());
			}
		} catch (HttpStatusCodeException e) {
			responseAlbumList.setAlbums(null);
			responseAlbumList.setStatus(e.getStatusCode().toString());
			responseAlbumList.setError(e.getMessage());
			
		}

		return responseAlbumList;
	}



	@Override
	public ResponseAlbum findById(Integer id) {
		LOGGER.info("Se ingreso al servicio  /albumService/findById con Parametro id: "+ id);

		Map<String, Integer> vars = new HashMap<String, Integer>();
		vars.put("id", id);
		try {



			ResponseEntity<AlbumModel> response = restTemplate.getForEntity(WoloxUtil.URL_ALBUMS_BY_ID, AlbumModel.class, vars);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responseAlbum.setAlbum(response.getBody());
				responseAlbum.setStatus(response.getStatusCode().toString());
			}
		} catch (HttpStatusCodeException e) {
			responseAlbumList.setAlbums(null);
			responseAlbumList.setStatus(e.getStatusCode().toString());
			responseAlbumList.setError(e.getMessage());
			
		}
		

		return responseAlbum;
	}

	@Override
	public ResponseAlbumList findByTipoId(Integer userId) {
		
		LOGGER.info("Se ingreso al servicio  /albumService/findByTipoId con Parametro userId: "+ userId  );

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(WoloxUtil.URL_ALBUMS);
        builder.queryParam("userId", userId);

		try {
			
			ResponseEntity<List<AlbumModel>> response = restTemplate.exchange(builder.build().encode().toUri(),
					HttpMethod.GET, null, album);

		
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responseAlbumList.setAlbums(response.getBody());
				responseAlbumList.setStatus(response.getStatusCode().toString());
			}
		}catch (HttpStatusCodeException e) {
			responseAlbumList.setAlbums(null);
			responseAlbumList.setStatus(e.getStatusCode().toString());
			responseAlbumList.setError(e.getMessage());
			
		}

		return responseAlbumList;
	}

}
