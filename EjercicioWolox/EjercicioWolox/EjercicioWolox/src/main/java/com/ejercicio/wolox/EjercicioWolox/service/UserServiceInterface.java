package com.ejercicio.wolox.EjercicioWolox.service;

import com.ejercicio.wolox.EjercicioWolox.response.ResponseUser;
import com.ejercicio.wolox.EjercicioWolox.response.ResponseUserList;

public interface UserServiceInterface extends ServiceInterface<ResponseUserList> {

	public abstract ResponseUser findById(Integer id);
	
}
