package com.cursomc.service.validation;

import com.cursomc.domain.Cliente;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.dtos.ClienteDTO;
import com.cursomc.dtos.ClienteNewDTO;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.resource.exception.FieldMessage;
import com.cursomc.service.validation.util.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    private final ClienteRepository repo;

    @Autowired
    private HttpServletRequest request;

    public ClienteUpdateValidator(ClienteRepository repo) {
        this.repo = repo;
    }

    Map<String, String> map = (Map<String, String>) request
            .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);


    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();
        // inclua os testes aqui, inserindo erros na lista

        Cliente aux = repo.findByEmail(clienteDTO.getEmail());
        if(aux != null){
            list.add(new FieldMessage("email", "email já pertence a um cliente"));
        }

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();

    }


}
