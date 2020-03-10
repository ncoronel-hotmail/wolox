package com.ejercicio.wolox.EjercicioWolox.service;

import java.util.List;

import com.ejercicio.wolox.EjercicioWolox.response.ResponsePhoto;
import com.ejercicio.wolox.EjercicioWolox.response.ResponsePhotoList;

public interface PhotoServiceInterface extends ServiceInterface<ResponsePhotoList> {
	
	public abstract ResponsePhoto findById(Integer id) ;
	public abstract ResponsePhotoList findByTipoId(List<Integer> tipoId);


}
