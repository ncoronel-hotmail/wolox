package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.PhotoModel;

@Component("responsePhotoList")
public class ResponsePhotoList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 345366343L;

	private String status = "";

	private String error = "";

	private List<PhotoModel> photos;

	public ResponsePhotoList() {
		super();
	}
	
	
	

	public ResponsePhotoList(String status, String error, List<PhotoModel> photo) {
		super();
		this.status = status;
		this.error = error;
		this.photos = photo;
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

	public List<PhotoModel> getPhoto() {
		return photos;
	}

	public void setPhoto(List<PhotoModel> photo) {
		this.photos = photo;
	}




	@Override
	public String toString() {
		return "ResponsePhoto [status=" + status + ", error=" + error + ", photos=" + photos + "]";
	}

}
