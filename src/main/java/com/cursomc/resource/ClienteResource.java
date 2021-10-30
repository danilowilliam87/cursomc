package com.cursomc.resource;


import com.cursomc.domain.Cliente;
import com.cursomc.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	private final ClienteService service;

	public ClienteResource(ClienteService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) throws Throwable {
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok(obj);
	}

	@RequestMapping("/ola")
	public String teste(){
		return "ola mundo";
	}
}
