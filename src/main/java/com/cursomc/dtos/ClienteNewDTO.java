package com.cursomc.dtos;


import com.cursomc.service.validation.ClienteInsert;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "preenchimento obrigatorio")
    @Length(min = 5, max = 120, message = "tamanho deve ser entre 5 e 40")
    private  String nome;

    @NotEmpty(message = "preenchimento obrigatorio")
    @Email(message = "Email invalido")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String cpfOuCnpj;

    private Integer tipo;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String senha;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String logradouro;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String numero;

    private String complemento;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String cep;

    private String bairro;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String telefone1;

    private String telefone2;

    private String telefone3;

    public ClienteNewDTO(String nome, String email, String cpfOuCnpj, int cod) {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }



    private Integer cidadeId;

    public ClienteNewDTO() {
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }
}
