package br.fiap.Cliente;

public abstract class Cliente {

    protected int idCliente;
    protected String nome;
    protected String email;
    protected String endereco;

    public Cliente(int idCliente, String nome, String email, String endereco) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
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
}
