package com.ejercicio.wolox.EjercicioWolox.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ejercicio.wolox.EjercicioWolox.dto.UserAlbumDto;
import com.ejercicio.wolox.EjercicioWolox.entity.UserAlbum;
import com.ejercicio.wolox.EjercicioWolox.model.UserAlbumModel;
import com.ejercicio.wolox.EjercicioWolox.repository.UserAlbumRepository;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserAlbum;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserAlbumList;

@Service("userAlbumService")
public class UserAlbumService implements UserAlbumInterface {
	
	private static final Log LOGGER =LogFactory.getLog(UserAlbumService.class);


	@Autowired
	@Qualifier("responseUserAlbum")
	private ResponseUserAlbum responseUserAlbum;
	

	@Autowired
	@Qualifier("responseUserAlbumList")
	private ResponseUserAlbumList responseUserAlbumList;

	@Autowired
	@Qualifier("userAlbumRepository")
	private UserAlbumRepository userAlbumRepository;

	@Autowired
	@Qualifier("userAlbumDto")
	private UserAlbumDto userAlbumDto;

	@Override
	public ResponseUserAlbum add(UserAlbumModel userAlbum) {
		
		LOGGER.info("Se ingreso al servicio  /userAlbumService/add con parametros userAlbum: " +userAlbum.toString()  );

		try {
			UserAlbum response = userAlbumRepository.save(userAlbumDto.userAlbumModelToUserAlbum(userAlbum));
			if (response != null) {
				responseUserAlbum = userAlbumDto.userAlbumToResponseUserAlbum(response);
				responseUserAlbum.setStatus(HttpStatus.CREATED.toString());
			}

		} catch (Exception e) {
			responseUserAlbum.setError(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			responseUserAlbum.setUserAlbum(null);
			responseUserAlbum.setStatus(null);

		}

		return responseUserAlbum;
	}

	@Override
	public ResponseUserAlbum update(UserAlbumModel userAlModel) {
		
		LOGGER.info("Se ingreso al servicio  /userAlbumService/update con parametros userAlModel: " +userAlModel.toString()  );

		try {

			int response = 0;
			
			response = userAlbumRepository.updatePermisoAlbum(userAlModel.getIsRead(), userAlModel.getIsWrite(),
					userAlModel.getAlbumId(), userAlModel.getUserInvitedId());

			if (response > 0) {
				responseUserAlbum = findByAlbumIdAndUserInvitedId(userAlModel);
				responseUserAlbum.setStatus(HttpStatus.OK.toString());
			}

		} catch (Exception e) {
			responseUserAlbum.setError(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			responseUserAlbum.setUserAlbum(null);
			responseUserAlbum.setStatus(null);

		}

		return responseUserAlbum;
	}

	@Override
	public ResponseUserAlbum findByAlbumIdAndUserInvitedId(UserAlbumModel userAlModel) {
		
		LOGGER.info("Se ingreso al servicio  /userAlbumService/findByAlbumIdAndUserInvitedId con parametros userAlModel: " +userAlModel.toString()  );

		try {
			Optional<UserAlbum> response =userAlbumRepository.findByAlbumIdAndUserInvitedId(userAlModel.getAlbumId(),
					userAlModel.getUserInvitedId());


			if(response.isPresent()) {
				responseUserAlbum = userAlbumDto.userAlbumToResponseUserAlbum(response.get());
				responseUserAlbum.setStatus(HttpStatus.OK.toString());
			}
			
			

		} catch (Exception e) {
			responseUserAlbum.setError(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			responseUserAlbum.setUserAlbum(null);
			responseUserAlbum.setStatus(null);

		}

		return responseUserAlbum;
	}

	@Override
	public ResponseUserAlbumList findByIsReadOrIsWriteAndAlbumId(String isRead,String isWrite,Integer albumId) {
		
		List<UserAlbum> response = new ArrayList<UserAlbum>();
		LOGGER.info("Se ingreso al servicio  /userAlbumService/findByAlbumIdAndIsReadOrIsWrite con parametros albumId: " + albumId + " , isRead: " + isRead + ", isWrite:" + isWrite );

		try {
			if(isRead.equals("NULL")&& !isWrite.equals("NULL")) {
				response = userAlbumRepository.findByAlbumIdAndIsWrite(albumId, Boolean.parseBoolean(isWrite));
			}else if(!isRead.equals("NULL")&& isWrite.equals("NULL")){
				response = userAlbumRepository.findByAlbumIdAndIsRead(albumId, Boolean.parseBoolean(isRead));
			}else {
				response = userAlbumRepository.findByAlbumIdAndIsReadAndIsWrite(Boolean.parseBoolean(isRead), Boolean.parseBoolean(isWrite), albumId);
			}
			
			if (response.size() >0) {
				responseUserAlbumList = userAlbumDto.userAlbumListToResponseUserAlbumList(response);
				responseUserAlbumList.setStatus(HttpStatus.OK.toString());
			}else {
				responseUserAlbumList = userAlbumDto.userAlbumListToResponseUserAlbumList(response);
				responseUserAlbumList.setStatus(HttpStatus.NO_CONTENT.toString());
			}

		} catch (Exception e) {
			responseUserAlbumList.setError(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			responseUserAlbumList.setUsersAlbums(null);
			responseUserAlbumList.setStatus(null);

		}

		return responseUserAlbumList;
	}

}
