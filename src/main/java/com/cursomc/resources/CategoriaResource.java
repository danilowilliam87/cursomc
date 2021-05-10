package com.cursomc.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.domain.Categoria;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> testar() {
		Categoria cat1 = new Categoria(1, "informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		List<Categoria> categorias = Arrays.asList(cat1, cat2);
		return categorias;
	}
}
