package br.fiap.Clientes;


import java.sql.Date;
import java.util.TreeMap;

public abstract class ContaCorrente extends Cliente{

    protected int numero;
    protected double saldo;
    protected Date data_criacao;
    protected int status;

    public ContaCorrente(int id_cliente, String nome, int celular, String email, String endereco, int numero, double saldo, Date data_criacao, int status) {
        super(id_cliente, nome, celular, email, endereco);
        this.numero = numero;
        this.saldo = saldo;
        this.data_criacao = data_criacao;
        this.status = status;
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
        return data_criacao;
    }

    public void setDataCriacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
