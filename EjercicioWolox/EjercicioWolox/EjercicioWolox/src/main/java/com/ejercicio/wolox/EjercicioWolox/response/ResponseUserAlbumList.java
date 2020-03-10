package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.UserAlbumModel;

@Component("responseUserAlbumList")
public class ResponseUserAlbumList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 14466656456L;

	private String status = "";

	private String error = "";

	private List<UserAlbumModel> usersAlbums;

	public ResponseUserAlbumList(String status, String error, List<UserAlbumModel> usersAlbums) {
		super();
		this.status = status;
		this.error = error;
		this.usersAlbums = usersAlbums;
	}

	public ResponseUserAlbumList() {
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

	public List<UserAlbumModel> getUsersAlbums() {
		return usersAlbums;
	}

	public void setUsersAlbums(List<UserAlbumModel> usersAlbums) {
		this.usersAlbums = usersAlbums;
	}

	@Override
	public String toString() {
		return "ResponseUserAlbumList [status=" + status + ", error=" + error + ", usersAlbums=" + usersAlbums + "]";
	}

}
