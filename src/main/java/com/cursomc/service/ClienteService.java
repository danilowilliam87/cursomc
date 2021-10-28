package com.cursomc.service;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Cliente;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


    public Cliente buscar(Integer id)  {
        Optional<Cliente> cli = repository.findById(id);
        return cli.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id : " + id + ", Tipo : " + Cliente.class.getName()));

    }
}
