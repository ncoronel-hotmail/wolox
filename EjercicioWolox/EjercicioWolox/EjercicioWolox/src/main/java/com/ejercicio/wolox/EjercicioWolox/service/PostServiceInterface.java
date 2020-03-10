package com.ejercicio.wolox.EjercicioWolox.service;

import com.ejercicio.wolox.EjercicioWolox.response.ResponsePost;
import com.ejercicio.wolox.EjercicioWolox.response.ResponsePostList;

public interface PostServiceInterface extends ServiceInterface<ResponsePostList> {

	public abstract ResponsePost findById(Integer id);

	public abstract ResponsePostList findByTipoId(Integer tipoId);

}
