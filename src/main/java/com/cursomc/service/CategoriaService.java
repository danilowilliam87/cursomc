package com.cursomc.service;

import com.cursomc.domain.Categoria;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.service.exception.DataIntegrityException;
import com.cursomc.service.exception.MethodArgumentException;
import com.cursomc.service.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.util.Optional;


@Service
public class CategoriaService {

	private final CategoriaRepository repository;

	public CategoriaService(CategoriaRepository repository) {
		this.repository = repository;
	}

	public Categoria find(Integer id)  {
			Optional<Categoria> cat = repository.findById(id);
			return cat.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id : " + id + ", Tipo : " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj){
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Categoria obj){
		find(obj.getId());
		return repository.save(obj);
	}

	public void delete(Integer id){
		find(id);
		try{
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produto");
		}
	}


	}
	
	

