package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.AlbumModel;

@Component("responseAlbum")
public class ResponseAlbum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 55554434L;

	private String status = "";

	private String error = "";

	private AlbumModel album;

	public ResponseAlbum(String status, String error, AlbumModel album) {
		super();
		this.status = status;
		this.error = error;
		this.album = album;
	}

	public ResponseAlbum() {
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

	public AlbumModel getAlbum() {
		return album;
	}

	public void setAlbum(AlbumModel album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "ResponseAlbum [status=" + status + ", error=" + error + ", album=" + album + "]";
	}

}
