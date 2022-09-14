package br.fiap.Servicos;

import java.sql.Date;

public class CartaoCredito extends Cartao{

    private int idCartaoCredito;
    private double fatura;
    private Date dataVencimento;
    private double jurosVencimento;

    public CartaoCredito(int numero, double limite, int idCartaoCredito, double fatura, Date dataVencimento, double jurosVencimento) {
        super(numero, limite);
        this.idCartaoCredito = idCartaoCredito;
        this.fatura = fatura;
        this.dataVencimento = dataVencimento;
        this.jurosVencimento = jurosVencimento;
    }

    public int getIdCartaoCredito() {
        return idCartaoCredito;
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

    public double getJurosVencimento() {
        return jurosVencimento;
    }

    public void setJurosVencimento(double jurosVencimento) {
        this.jurosVencimento = jurosVencimento;
    }
}
