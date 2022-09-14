package br.fiap.Servicos;

public abstract class Cartao extends Servicos{

    protected int numero;
    protected double limite;

    public Cartao(int numero, double limite) {
        this.numero = numero;
        this.limite = limite;
    }

    public int getNumero() {
        return numero;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
