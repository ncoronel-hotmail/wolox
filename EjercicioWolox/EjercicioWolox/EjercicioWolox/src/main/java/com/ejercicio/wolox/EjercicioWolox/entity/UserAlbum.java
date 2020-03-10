package com.ejercicio.wolox.EjercicioWolox.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_Album",uniqueConstraints=@UniqueConstraint(columnNames= {"user_OwnerId", "user_InvitedId","albumId"}))
public class UserAlbum {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "user_OwnerId",nullable=false)
	private Integer userOwnerId;

	@Column(name = "user_InvitedId",nullable=false)
	private Integer userInvitedId;

	@Column(name = "albumId",nullable=false)
	private Integer albumId;

	@Column(name = "is_read",nullable=false)
	private Boolean isRead;

	@Column(name = "is_write",nullable=false)
	private Boolean isWrite;

	public UserAlbum() {
		super();
	}

	public UserAlbum(Integer id, Integer userOwnerId, Integer userInvitedId, Integer albumId, Boolean isRead,
			Boolean isWrite) {
		super();
		this.id = id;
		this.userOwnerId = userOwnerId;
		this.userInvitedId = userInvitedId;
		this.albumId = albumId;
		this.isRead = isRead;
		this.isWrite = isWrite;
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

	@Override
	public String toString() {
		return "UserAlbum [id=" + id + ", userOwnerId=" + userOwnerId + ", userInvitedId=" + userInvitedId
				+ ", albumId=" + albumId + ", isRead=" + isRead + ", isWrite=" + isWrite + "]";
	}

}
