package com.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.service.exception.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Integer id)  {
		Optional<Categoria> cat = repository.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id : " + id + ", Tipo : " + Categoria.class.getName()));
		
	}
	
	
	
	
}
