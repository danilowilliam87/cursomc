package com.cursomc.service;

import com.cursomc.domain.Cidade;
import com.cursomc.domain.Cliente;
import com.cursomc.domain.Endereco;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.dtos.ClienteDTO;
import com.cursomc.dtos.ClienteNewDTO;
import com.cursomc.dtos.ClienteResponseDTO;
import com.cursomc.repositories.CidadeRepository;
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
    private final CidadeRepository cidadeRepository;

    public ClienteService(ClienteRepository repository, EnderecoRepository endRepository, CidadeRepository cidadeRepository) {
        this.repository = repository;
        this.endRepository = endRepository;
        this.cidadeRepository = cidadeRepository;
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

    public Cliente fromDTO(ClienteNewDTO clienteNewDTO){
        Cliente cli = new Cliente(null, clienteNewDTO.getNome(),
                clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(),
                TipoCliente.toEnum(clienteNewDTO.getTipo()),
                clienteNewDTO.getSenha());
        Cidade cid = new Cidade( clienteNewDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(),
                clienteNewDTO.getComplemento(),clienteNewDTO.getBairro(), clienteNewDTO.getCep(),
        cli, cid);
        cli.getTelefones().add(clienteNewDTO.getTelefone1());
        if(clienteNewDTO.getTelefone2() != null){
            cli.getTelefones().add(clienteNewDTO.getTelefone2());
        }
        if(clienteNewDTO.getTelefone2() != null){
            cli.getTelefones().add(clienteNewDTO.getTelefone2());
        }
        if(clienteNewDTO.getTelefone3() != null){
            cli.getTelefones().add(clienteNewDTO.getTelefone2());
        }

       return cli;
    }

    public ClienteResponseDTO fromResponseDTO(Cliente cliente){
        return new ClienteResponseDTO(cliente);
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
