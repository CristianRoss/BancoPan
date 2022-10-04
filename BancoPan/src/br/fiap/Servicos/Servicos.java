package br.fiap.Servicos;

import br.fiap.Cliente.Cliente;

public abstract class Servicos {

    protected Cliente Cliente;

    public Servicos(Cliente Cliente) {
        this.Cliente = Cliente;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }
}
