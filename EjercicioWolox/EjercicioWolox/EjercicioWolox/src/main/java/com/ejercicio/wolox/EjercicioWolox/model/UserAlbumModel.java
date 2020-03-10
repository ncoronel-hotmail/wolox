package com.ejercicio.wolox.EjercicioWolox.model;

public class UserAlbumModel {

	private Integer id;

	private Integer userOwnerId;

	private Integer userInvitedId;

	private Integer albumId;

	private Boolean isRead;

	private Boolean isWrite;

	public UserAlbumModel(Integer userOwnerId, Integer userInvitedId, Integer albumId, Boolean isRead,
			Boolean isWrite) {
		super();
		this.userOwnerId = userOwnerId;
		this.userInvitedId = userInvitedId;
		this.albumId = albumId;
		this.isRead = isRead;
		this.isWrite = isWrite;
	}

	public UserAlbumModel(Integer id, Integer userOwnerId, Integer userInvitedId, Integer albumId, Boolean isRead,
			Boolean isWrite) {
		super();
		this.id = id;
		this.userOwnerId = userOwnerId;
		this.userInvitedId = userInvitedId;
		this.albumId = albumId;
		this.isRead = isRead;
		this.isWrite = isWrite;
	}

	public UserAlbumModel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserOwnerId() {
		return userOwnerId;
	}

	public void setUserOwnerId(Integer userOwnerId) {
		this.userOwnerId = userOwnerId;
	}

	public Integer getUserInvitedId() {
		return userInvitedId;
	}

	public void setUserInvitedId(Integer userInvitedId) {
		this.userInvitedId = userInvitedId;
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsWrite() {
		return isWrite;
	}

	public void setIsWrite(Boolean isWrite) {
		this.isWrite = isWrite;
	}

}
