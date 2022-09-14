package br.fiap.Servicos;

import java.sql.Date;

public class Poupanca extends Servicos{

    private int idPoupanca;
    private double saldo;
    private double juros;
    private Date dataCriacao;
    private Date dataAcrescimo;

    public Poupanca(int idPoupanca, double saldo, double juros, Date dataCriacao, Date dataAcrescimo) {
        this.idPoupanca = idPoupanca;
        this.saldo = saldo;
        this.juros = juros;
        this.dataCriacao = dataCriacao;
        this.dataAcrescimo = dataAcrescimo;
    }

    public int getIdPoupanca() {
        return idPoupanca;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAcrescimo() {
        return dataAcrescimo;
    }

    public void setDataAcrescimo(Date dataAcrescimo) {
        this.dataAcrescimo = dataAcrescimo;
    }
}
