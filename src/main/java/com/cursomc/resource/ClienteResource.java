package com.cursomc.resource;


import com.cursomc.domain.Cliente;
import com.cursomc.dtos.ClienteDTO;
import com.cursomc.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	private final ClienteService service;

	public ClienteResource(ClienteService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) throws Throwable {
		Cliente obj = service.find(id);
		return ResponseEntity.ok(obj);
	}

	//@RequestMapping(method = RequestMethod.GET, value = "/{email}")
    public ResponseEntity<Cliente> find(@RequestParam(value = "value") String email){
		Cliente cliente = service.findByEmail(email);
		return ResponseEntity.ok(cliente);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO clienteDTO){
		Cliente cliente = service.fromDTO(clienteDTO);
        cliente = service.insert(cliente);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri)
				.build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
		Cliente cli = service.fromDTO(clienteDTO);
		cli.setId(id);
		cli = service.update(cli);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream()
				.map(ClienteDTO::new)
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}

}
