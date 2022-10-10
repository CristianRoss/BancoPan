package br.fiap.Servicos.Cartoes;

import br.fiap.Cliente.Cliente;
import br.fiap.Servicos.Servicos;

public abstract class Cartao extends Servicos {

    protected int numero;

    protected int idCartao;
    protected double limite;

    public Cartao(Cliente cliente, int idCartao, int numero, double limite) {
        super(cliente);
        this.numero = numero;
        this.limite = limite;
        this.idCartao=idCartao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public int getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(int idCartao) {
        this.idCartao = idCartao;
    }
}
