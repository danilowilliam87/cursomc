package com.cursomc.service;

import com.cursomc.domain.Cliente;
import com.cursomc.dtos.ClienteDTO;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.repositories.EnderecoRepository;
import com.cursomc.service.exception.DataIntegrityException;
import com.cursomc.service.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final EnderecoRepository endRepository;

    public ClienteService(ClienteRepository repository, EnderecoRepository endRepository) {
        this.repository = repository;
        this.endRepository = endRepository;
    }


    public Cliente find(Integer id)  {
        Optional<Cliente> cli = repository.findById(id);
        return cli.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id : " + id + ", Tipo : " + Cliente.class.getName()));

    }

    public Cliente findByEmail(String email){
        return repository.findByEmail(email);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO){
       return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    @Transactional
    public Cliente insert(Cliente cliente){
        cliente.setId(null);
        cliente = repository.save(cliente);
        endRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente update(Cliente cliente){
        Cliente cli  = find(cliente.getId());
        updateData(cli, cliente);
        return repository.save(cli);
    }

    public void delete(Integer id){
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados ");
        }
    }

    private void updateData(Cliente newObj, Cliente cli){
        newObj.setNome(cli.getNome());
        newObj.setEmail(cli.getEmail());
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }
}
