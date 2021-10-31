package com.cursomc.resource;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cursomc.domain.Categoria;
import com.cursomc.service.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
