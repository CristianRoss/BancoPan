package br.fiap.Servicos;

public abstract class Cartao extends Servicos{

    protected int numero;
    protected int limite;

    public Cartao(int numero, int limite) {
        this.numero = numero;
        this.limite = limite;
    }

    public int getNumero() {
        return numero;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
}
