package br.fiap.Cliente;

import java.util.HashMap;
import java.util.Objects;

public abstract class Cliente {

    protected int idCliente;
    protected String nome;
    protected String email;
    protected String endereco;

    protected int telefone;

    protected String CEP;

    public static HashMap<Integer, Cliente> clientes=new HashMap<Integer, Cliente>();

    public Cliente(int idCliente, String nome, String email, String endereco, int telefone,String CEP) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone=telefone;
        this.CEP=CEP;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

}
