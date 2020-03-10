package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.AlbumModel;


@Component("responseAlbumList")
public class ResponseAlbumList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 22243444522L;

	private String status = "";

	private String error = "";

	private List<AlbumModel> albums;

	public ResponseAlbumList(String status, String error, List<AlbumModel> albums) {
		super();
		this.status = status;
		this.error = error;
		this.albums = albums;
	}

	public ResponseAlbumList() {
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

	public List<AlbumModel> getAlbums() {
		return albums;
	}

	public void setAlbums(List<AlbumModel> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "ResponseAlbum [status=" + status + ", error=" + error + ", albums=" + albums + "]";
	}

}
