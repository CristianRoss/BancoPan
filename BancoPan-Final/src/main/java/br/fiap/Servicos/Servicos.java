package br.fiap.Servicos;

import br.fiap.Cliente.Cliente;

public abstract class Servicos {

    protected Cliente cliente;

    public Servicos(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
