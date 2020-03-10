package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.PostModel;

@Component("responsePost")
public class ResponsePost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1404569456456L;

	private String status = "";

	private String error = "";

	private PostModel post;

	public ResponsePost(String status, String error, PostModel post) {
		super();
		this.status = status;
		this.error = error;
		this.post = post;
	}

	public ResponsePost() {
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

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "ResponsePost [status=" + status + ", error=" + error + ", post=" + post + "]";
	}

}
