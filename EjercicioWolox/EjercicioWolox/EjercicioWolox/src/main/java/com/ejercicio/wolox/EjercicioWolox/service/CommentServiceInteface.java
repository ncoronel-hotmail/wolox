package com.ejercicio.wolox.EjercicioWolox.service;

import java.util.List;

import com.ejercicio.wolox.EjercicioWolox.response.ResponseComment;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseCommentList;

public interface CommentServiceInteface extends ServiceInterface<ResponseCommentList> {

	public abstract ResponseComment findById(Integer id) ;
	public abstract ResponseCommentList findByName(String name) ;
	public abstract ResponseCommentList findByTipoId(List<Integer> tipoId);



}
