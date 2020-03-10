package com.ejercicio.wolox.EjercicioWolox.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ejercicio.wolox.EjercicioWolox.model.CommentModel;

@Component("responseCommentList")
public class ResponseCommentList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 343223444L;

	private String status = "";

	private String error = "";

	private List<CommentModel> comments;

	public ResponseCommentList(String status, String error, List<CommentModel> comments) {
		super();
		this.status = status;
		this.error = error;
		this.comments = comments;
	}

	public ResponseCommentList() {
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

	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "ResponseComment [status=" + status + ", error=" + error + ", comments=" + comments + "]";
	}

}
