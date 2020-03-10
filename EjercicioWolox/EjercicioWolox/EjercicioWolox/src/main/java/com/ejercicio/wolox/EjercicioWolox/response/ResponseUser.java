package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.UserModel;

@Component("responseUser")
public class ResponseUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3432243223533L;

	private String status = "";

	private String error = "";

	private UserModel user;

	public ResponseUser(String status, String error, UserModel user) {
		super();
		this.status = status;
		this.error = error;
		this.user = user;
	}

	public ResponseUser() {
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

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ResponseUser [status=" + status + ", error=" + error + ", user=" + user + "]";
	}

}
