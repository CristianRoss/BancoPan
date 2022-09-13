package br.fiap.Servicos;

public class Financiamento extends Servicos{

    private int idFinanciamneto;
    private double valorTotal;
    private double valorParcelas;
    private int qtdParcelas;
    private double juros;
    private String dataRealizacao;
    private String data_fim;
    private double entrada;

    public Financiamento(int idFinanciamneto, double valorTotal, double valorParcelas, int qtdParcelas, double juros, String dataRealizacao, String data_fim, double entrada) {
        this.idFinanciamneto = idFinanciamneto;
        this.valorTotal = valorTotal;
        this.valorParcelas = valorParcelas;
        this.qtdParcelas = qtdParcelas;
        this.juros = juros;
        this.dataRealizacao = dataRealizacao;
        this.data_fim = data_fim;
        this.entrada = entrada;
    }

    public int getIdFinanciamneto() {
        return idFinanciamneto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public String getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(String dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public double getEntrada() {
        return entrada;
    }

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }
}
