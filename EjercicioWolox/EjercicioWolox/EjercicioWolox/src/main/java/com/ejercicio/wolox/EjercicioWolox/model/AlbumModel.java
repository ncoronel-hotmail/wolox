package com.ejercicio.wolox.EjercicioWolox.model;

public class AlbumModel {

	private int userId;
	private int id;
	private String title;

	public AlbumModel() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "AlbumResponse [userId=" + userId + ", id=" + id + ", title=" + title + "]";
	}

}
