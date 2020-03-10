package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.UserModel;

@Component("responseUserList")
public class ResponseUserList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3443223533L;

	private String status = "";

	private String error = "";

	private List<UserModel> users;

	public ResponseUserList(String status, String error, List<UserModel> users) {
		super();
		this.status = status;
		this.error = error;
		this.users = users;
	}

	public ResponseUserList() {
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

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "ResponseUser [status=" + status + ", error=" + error + ", users=" + users + "]";
	}

}
