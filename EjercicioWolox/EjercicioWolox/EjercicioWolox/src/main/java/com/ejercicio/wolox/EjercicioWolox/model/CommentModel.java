package com.ejercicio.wolox.EjercicioWolox.model;

public class CommentModel {

	private int id;

	private int postId;

	private String name;

	private String email;

	private String body;

	public CommentModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "CommentResponse [id=" + id + ", postId=" + postId + ", name=" + name + ", email=" + email + ", body="
				+ body + "]";
	}

}
