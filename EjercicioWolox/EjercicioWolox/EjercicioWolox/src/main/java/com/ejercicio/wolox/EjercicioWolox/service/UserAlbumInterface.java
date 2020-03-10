package com.ejercicio.wolox.EjercicioWolox.service;

import com.ejercicio.wolox.EjercicioWolox.model.UserAlbumModel;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserAlbum;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserAlbumList;

public interface UserAlbumInterface {
	
	
	public abstract ResponseUserAlbum  add(UserAlbumModel userAlbum);
	public abstract ResponseUserAlbum update(UserAlbumModel userAlModel);
	public abstract ResponseUserAlbum findByAlbumIdAndUserInvitedId(UserAlbumModel userAlModel);
	public abstract ResponseUserAlbumList findByIsReadOrIsWriteAndAlbumId(String isRead,String isWrite,Integer albumId);


}
