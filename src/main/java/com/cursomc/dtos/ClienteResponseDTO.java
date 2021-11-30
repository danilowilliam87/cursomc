package com.cursomc.dtos;

import com.cursomc.domain.Cliente;

import java.io.Serializable;
import java.util.Objects;


public class ClienteResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private String tipo;
    private String logradouro;
    private String numero;
    private String telefoneprincipal;


    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Integer id, String nome, String email, String cpfOuCnpj, String tipo, String logradouro, String numero, String telefoneprincipal) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.telefoneprincipal = telefoneprincipal;
    }

    public ClienteResponseDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpfOuCnpj = cliente.getCpfOuCnpj();
        this.tipo = (cliente.getTipo().getCod()) == 1 ? "PESSOA_FÍSICA" : "PESSOA JURÍDICA";
        this.logradouro = cliente.getEnderecos().get(0).getLogradouro();
        this.numero = cliente.getEnderecos().get(0).getNumero();
        this.telefoneprincipal = cliente.getTelefones().toString();
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getTelefoneprincipal() {
        return telefoneprincipal;
    }

    public void setTelefoneprincipal(String telefoneprincipal) {
        this.telefoneprincipal = telefoneprincipal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteResponseDTO that = (ClienteResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(cpfOuCnpj, that.cpfOuCnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, cpfOuCnpj);
    }

    @Override
    public String toString() {
        return "ClienteResponseDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpfOuCnpj='" + cpfOuCnpj + '\'' +
                ", tipo='" + tipo + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", telefoneprincipal='" + telefoneprincipal + '\'' +
                '}';
    }
}
