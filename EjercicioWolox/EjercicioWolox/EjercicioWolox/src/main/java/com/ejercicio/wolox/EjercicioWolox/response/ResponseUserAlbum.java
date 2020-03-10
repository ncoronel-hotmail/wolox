package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.UserAlbumModel;

@Component("responseUserAlbum")
public class ResponseUserAlbum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1433244545L;

	private String status = "";

	private String error = "";

	private UserAlbumModel userAlbum;

	public ResponseUserAlbum(String status, String error, UserAlbumModel userAlbum) {
		super();
		this.status = status;
		this.error = error;
		this.userAlbum = userAlbum;
	}

	public ResponseUserAlbum() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public UserAlbumModel getUserAlbum() {
		return userAlbum;
	}

	public void setUserAlbum(UserAlbumModel userAlbum) {
		this.userAlbum = userAlbum;
	}

	@Override
	public String toString() {
		return "ResponseUserAlbum [status=" + status + ", error=" + error + ", userAlbum=" + userAlbum + "]";
	}

}
