package br.fiap.Servicos;

public abstract class Servicos {

    protected int idCliente;

    public Servicos(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
