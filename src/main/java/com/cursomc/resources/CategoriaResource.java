package com.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {

	@RequestMapping(method = RequestMethod.GET)
	public String testar() {
		return "REST funcionando";
	}
}
