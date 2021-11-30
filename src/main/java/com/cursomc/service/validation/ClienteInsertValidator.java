package com.cursomc.service.validation;

import com.cursomc.domain.Cliente;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.dtos.ClienteNewDTO;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.resource.exception.FieldMessage;
import com.cursomc.service.validation.util.BR;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    private final ClienteRepository repo;

    public ClienteInsertValidator(ClienteRepository repo) {
        this.repo = repo;
    }


    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();
        // inclua os testes aqui, inserindo erros na lista

        if(clienteNewDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) &&
        !BR.isValidSsn(clienteNewDTO.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

       Cliente aux = repo.findByEmail(clienteNewDTO.getEmail());
        if(aux != null){
            list.add(new FieldMessage("email", "email já pertence a um cliente"));
        }

        if(clienteNewDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) &&
                !BR.isValidTfn(clienteNewDTO.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();

    }


}
