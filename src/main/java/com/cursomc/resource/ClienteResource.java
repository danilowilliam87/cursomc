package com.cursomc.resource;


import com.cursomc.domain.Cliente;
import com.cursomc.domain.Endereco;
import com.cursomc.dtos.ClienteDTO;
import com.cursomc.dtos.ClienteNewDTO;
import com.cursomc.dtos.ClienteResponseDTO;
import com.cursomc.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.parser.TagElement;
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
	public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Integer id) throws Throwable {
		Cliente cliente = service.find(id);
		ClienteResponseDTO objResponse = new ClienteResponseDTO(service.find(id));
		return ResponseEntity.ok(objResponse);
	}

	//@RequestMapping(method = RequestMethod.GET, value = "/{email}")
    public ResponseEntity<Cliente> find(@RequestParam(value = "value") String email){
		Cliente cliente = service.findByEmail(email);
		return ResponseEntity.ok(cliente);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO){
		Cliente cliente = service.fromDTO(clienteNewDTO);
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
