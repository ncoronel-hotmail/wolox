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

import com.ejercicio.wolox.EjercicioWolox.model.UserModel;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUser;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserList;
import com.ejercicio.wolox.EjercicioWolox.util.WoloxUtil;

@Service("userService")
public class UserService implements UserServiceInterface {
	
	private static final Log LOGGER =LogFactory.getLog(UserService.class);


	RestTemplate restTemplate = new RestTemplate();
	ParameterizedTypeReference<List<UserModel>> user = new ParameterizedTypeReference<List<UserModel>>() {
	};

	@Autowired
	@Qualifier("responseUserList")
	private ResponseUserList responseUserList;

	@Autowired
	@Qualifier("responseUser")
	private ResponseUser responseUser;

	@Override
	public ResponseUserList findAll() {
		LOGGER.info("Se ingreso al servicio  /userService/findAll "  );

		try {
			ResponseEntity<List<UserModel>> response = restTemplate.exchange(WoloxUtil.URL_USERS, HttpMethod.GET, null,
					user);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responseUserList.setUsers(response.getBody());
				responseUserList.setStatus(response.getStatusCode().toString());
			}
		} catch (HttpStatusCodeException e) {
			responseUserList.setUsers(null);
			responseUserList.setStatus(e.getStatusCode().toString());
			responseUserList.setError(e.getMessage());
		}

		return responseUserList;
	}

	@Override
	public ResponseUser findById(Integer id) {
		
		LOGGER.info("Se ingreso al servicio  /userService/findById con parametros Id: " + id  );

		Map<String, Integer> vars = new HashMap<String, Integer>();
		vars.put("id", id);
		try {
			ResponseEntity<UserModel> response = restTemplate.getForEntity(WoloxUtil.URL_USER_BY_ID, UserModel.class, vars);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				responseUser.setUser(response.getBody());
				responseUser.setStatus(response.getStatusCode().toString());
			}
		} catch (HttpStatusCodeException e) {
			responseUser.setUser(null);
			responseUser.setStatus(e.getStatusCode().toString());
			responseUser.setError(e.getMessage());
		}

		return responseUser;
	}

}
