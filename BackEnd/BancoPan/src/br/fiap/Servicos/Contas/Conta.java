package br.fiap.Servicos.Contas;

import br.fiap.Cliente.Cliente;
import br.fiap.Servicos.Servicos;

import java.sql.Date;

public abstract class Conta extends Servicos {

    protected int idConta;
    protected int numero;
    protected double saldo;
    protected Date dataCriacao;

    protected double juros;

    public Conta(Cliente cliente, int idConta, int numero, double saldo, Date dataCriacao, double juros) {
        super(cliente);
        this.idConta = idConta;
        this.numero = numero;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
        this.juros=juros;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

}
