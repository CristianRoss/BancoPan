package br.fiap.Servicos.Contas;

import br.fiap.Servicos.Servicos;

public class DocumentoConta extends Servicos {

    private int numero;
    private ContaCorrente ContaCorrente;
    private ContaPoupanca ContaPoupanca;

    public DocumentoConta(int idCliente, int numero) {
        super(idCliente);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ContaCorrente getContaCorrente() {
        return ContaCorrente;
    }

    public void setContaCorrente(ContaCorrente ContaCorrente) {
        this.ContaCorrente = ContaCorrente;
    }

    public ContaPoupanca getContaPoupanca() {
        return ContaPoupanca;
    }

    public void setIdContaPoupanca(ContaPoupanca ContaPoupanca) {
        this.ContaPoupanca = ContaPoupanca;
    }
}
