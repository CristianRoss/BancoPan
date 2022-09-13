package br.fiap.Servicos;

public class Poupanca extends Servicos{

    private int idPoupanca;
    private double saldo;
    private double juros;
    private String dataCriacao;
    private String dataAcrescimo;

    public Poupanca(int idPoupanca, double saldo, double juros, String dataCriacao, String dataAcrescimo) {
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

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataAcrescimo() {
        return dataAcrescimo;
    }

    public void setDataAcrescimo(String dataAcrescimo) {
        this.dataAcrescimo = dataAcrescimo;
    }
}
