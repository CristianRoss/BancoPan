package br.fiap.Servicos.Cartoes;

import java.sql.Date;

public class CartaoCredito extends Cartao{

    private double fatura;
    private Date dataVencimento;
    private double jurosCredito;

    public CartaoCredito(int idCliente, int idCartao, int numero, double limite, double fatura, Date dataVencimento, double jurosCredito) {
        super(idCliente, idCartao, numero, limite);
        this.fatura = fatura;
        this.dataVencimento = dataVencimento;
        this.jurosCredito = jurosCredito;
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
}
