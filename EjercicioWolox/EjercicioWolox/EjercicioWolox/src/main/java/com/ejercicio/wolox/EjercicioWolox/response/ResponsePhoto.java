package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.PhotoModel;

@Component("responsePhoto")
public class ResponsePhoto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3453467343L;

	private String status = "";

	private String error = "";

	private PhotoModel photo;

	public ResponsePhoto(String status, String error, PhotoModel photos) {
		super();
		this.status = status;
		this.error = error;
		this.photo = photos;
	}

	public ResponsePhoto() {
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

	public PhotoModel getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoModel photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "ResponsePhoto [status=" + status + ", error=" + error + ", photo=" + photo + "]";
	}

}
