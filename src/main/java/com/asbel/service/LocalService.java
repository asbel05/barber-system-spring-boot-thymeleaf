package com.asbel.service;

import java.util.List;

import com.asbel.model.entity.Local;

public interface LocalService {
	List<Local> listarTodos();

	Local obtenerPorId(Integer id);

	Local guardar(Local local);

	void eliminar(Integer id);
}
