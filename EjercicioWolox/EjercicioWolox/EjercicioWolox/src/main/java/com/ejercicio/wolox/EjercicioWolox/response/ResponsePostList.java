package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.PostModel;

@Component("responsePostList")
public class ResponsePostList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 14454556456L;

	private String status = "";

	private String error = "";

	private List<PostModel> posts;

	public ResponsePostList(String status, String error, List<PostModel> posts) {
		super();
		this.status = status;
		this.error = error;
		this.posts = posts;
	}

	public ResponsePostList() {
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

	public List<PostModel> getPosts() {
		return posts;
	}

	public void setPosts(List<PostModel> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "ResponsePost [status=" + status + ", error=" + error + ", posts=" + posts + "]";
	}

}
