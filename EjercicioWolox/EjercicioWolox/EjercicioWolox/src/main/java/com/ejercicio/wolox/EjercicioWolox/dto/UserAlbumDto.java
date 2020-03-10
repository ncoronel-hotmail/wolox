package com.ejercicio.wolox.EjercicioWolox.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.entity.UserAlbum;
import com.ejercicio.wolox.EjercicioWolox.model.UserAlbumModel;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserAlbum;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserAlbumList;

@Component("userAlbumDto")
/*
 * Clase encargada de entity a clase de model de datos o viceversa
 */
public class UserAlbumDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 13342545L;
	
	
	@Autowired
	@Qualifier("responseUserAlbumList")
	private ResponseUserAlbumList responseUserAlbumList;
	
	@Autowired
	@Qualifier("responseUserAlbum")
	private ResponseUserAlbum responseUserAlbum;
	
	



	public UserAlbum userAlbumModelToUserAlbum(UserAlbumModel userAlbum) {
		UserAlbum userAlbumEntity = new UserAlbum();
		userAlbumEntity.setUserOwnerId(userAlbum.getUserOwnerId());
		userAlbumEntity.setUserInvitedId(userAlbum.getUserInvitedId());
		userAlbumEntity.setAlbumId(userAlbum.getAlbumId());
		userAlbumEntity.setIsRead(userAlbum.getIsRead());
		userAlbumEntity.setIsWrite(userAlbum.getIsWrite());

		return userAlbumEntity;

	}

	public UserAlbumModel userAlbumToUserAlbumModel(UserAlbum userAlbum) {
		UserAlbumModel userAlbumModel = new UserAlbumModel();
		userAlbumModel.setUserOwnerId(userAlbum.getUserOwnerId());
		userAlbumModel.setUserInvitedId(userAlbum.getUserInvitedId());
		userAlbumModel.setAlbumId(userAlbum.getAlbumId());
		userAlbumModel.setIsRead(userAlbum.getIsRead());
		userAlbumModel.setIsWrite(userAlbum.getIsWrite());
		userAlbumModel.setId(userAlbum.getId());

		return userAlbumModel;

	}

	public ResponseUserAlbum userAlbumToResponseUserAlbum(UserAlbum userAlbum) {
		responseUserAlbum.setUserAlbum(userAlbumToUserAlbumModel(userAlbum));
		return responseUserAlbum;

	}
	
	
	public ResponseUserAlbumList userAlbumListToResponseUserAlbumList(List<UserAlbum> userAlbum){
		List<UserAlbumModel> usersAlbum = new ArrayList<UserAlbumModel>();
		

		for(UserAlbum item: userAlbum) {
			UserAlbumModel userAlbumModel = new  UserAlbumModel();
			userAlbumModel.setId(item.getId());
			userAlbumModel.setAlbumId(item.getAlbumId());
			userAlbumModel.setIsRead(item.getIsRead());
			userAlbumModel.setIsWrite(item.getIsWrite());
			userAlbumModel.setUserInvitedId(item.getUserInvitedId());
			userAlbumModel.setUserOwnerId(item.getUserOwnerId());
			usersAlbum.add(userAlbumModel);
		}
		
		responseUserAlbumList.setUsersAlbums(usersAlbum);
		
		return responseUserAlbumList;
	}

}
