package com.cursomc.resource;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.domain.Categoria;
import com.cursomc.service.CategoriaService;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {

	private final CategoriaService service;

	public CategoriaResource(CategoriaService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) throws Throwable {
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok(obj);
	}
}
