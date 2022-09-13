package br.fiap.Clientes;

public abstract class Cliente {

    protected int id_cliente;
    protected String nome;
    protected String sexo;
    protected int celular;
    protected String email;
    protected String endereco;


    public Cliente(int id_cliente,String nome, String sexo, int celular, String email, String endereco) {
        this.nome = nome;
        this.sexo = sexo;
        this.celular = celular;
        this.email = email;
        this.endereco = endereco;
        this.id_cliente=id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
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

    public int getIdCliente() {
        return id_cliente;
    }

}
