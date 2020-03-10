package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.CommentModel;

@Component("responseComment")
public class ResponseComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 349963223444L;

	private String status = "";

	private String error = "";

	private CommentModel comment;

	public ResponseComment(String status, String error, CommentModel comment) {
		super();
		this.status = status;
		this.error = error;
		this.comment = comment;
	}

	public ResponseComment() {
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

	public CommentModel getComment() {
		return comment;
	}

	public void setComment(CommentModel comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ResponseComment [status=" + status + ", error=" + error + ", comment=" + comment + "]";
	}

}
