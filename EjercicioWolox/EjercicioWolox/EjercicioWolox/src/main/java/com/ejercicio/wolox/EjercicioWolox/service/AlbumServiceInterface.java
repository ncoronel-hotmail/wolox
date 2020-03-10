package com.ejercicio.wolox.EjercicioWolox.service;

import com.ejercicio.wolox.EjercicioWolox.response.ResponseAlbum;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseAlbumList;

public interface AlbumServiceInterface extends ServiceInterface<ResponseAlbumList> {

	public abstract ResponseAlbum findById(Integer id);

	public abstract ResponseAlbumList findByTipoId(Integer userId);


}
