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
		Categoria obj = service.find(id);
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

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update( @RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
          service.delete(id);
		  return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/teste/{id}", method = RequestMethod.GET)
	public String teste(@PathVariable Integer id){
		return service.teste(id);
	}
}
