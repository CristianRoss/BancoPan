package br.fiap.Servicos.Cartoes;

import br.fiap.Cliente.Cliente;

import java.sql.Date;

public class CartaoCredito extends Cartao{

    private double fatura;
    private Date dataVencimento;
    private double jurosCredito;

    private int cvv;

    public CartaoCredito(Cliente cliente, int idCartao, int numero, double limite, double fatura, Date dataVencimento, double jurosCredito, int cvv) {
        super(cliente, idCartao, numero, limite);
        this.fatura = fatura;
        this.dataVencimento = dataVencimento;
        this.jurosCredito = jurosCredito;
        this.cvv=cvv;
    }

    public double getFatura() {
        return fatura;
    }

    public void setFatura(double fatura) {
        this.fatura = fatura;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getJurosCredito() {
        return jurosCredito;
    }

    public void setJurosCredito(double jurosCredito) {
        this.jurosCredito = jurosCredito;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
