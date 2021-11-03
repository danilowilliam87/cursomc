package com.cursomc.dtos;

import com.cursomc.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


public class CategoriaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "o tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    public CategoriaDto() {
    }

    public CategoriaDto(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
