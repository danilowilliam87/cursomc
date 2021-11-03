package com.cursomc.resource;


import com.cursomc.dtos.CategoriaDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cursomc.domain.Categoria;
import com.cursomc.service.CategoriaService;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDto>> findAll(){
		List<Categoria>list = service.findAll();
		List<CategoriaDto> listDto = list.stream()
				.map(obj -> new CategoriaDto(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDto>> findPage(
		@RequestParam(value = "page", defaultValue = "0") Integer page,
		@RequestParam(value = "linesPerPage", defaultValue = "0") Integer linesPerPage,
		@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
		@RequestParam(value = "direction", defaultValue = "ASC") String direction
	){
         Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		 Page<CategoriaDto> listDto = list.map(obj -> new CategoriaDto(obj));
		 return ResponseEntity.ok().body(listDto);
	}
}
