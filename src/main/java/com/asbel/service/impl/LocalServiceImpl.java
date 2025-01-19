package com.asbel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asbel.model.entity.Local;
import com.asbel.repository.LocalRepository;
import com.asbel.service.LocalService;

@Service
public class LocalServiceImpl implements LocalService {
	@Autowired
	private LocalRepository repo;

	@Override
	@Transactional(readOnly = true)
	public List<Local> listarTodos() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Local obtenerPorId(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Local no encontrado"));
	}

	@Override
	@Transactional
	public Local guardar(Local local) {
		return repo.save(local);
	}

	@Override
	@Transactional
	public void eliminar(Integer id) {
		Local local = repo.findById(id).orElseThrow(() -> new RuntimeException("Local no encontrado"));
		repo.delete(local);
	}
}
