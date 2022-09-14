package br.fiap.Servicos;

import java.sql.Date;

public class Emprestimos extends Servicos{

    private int idEmprestimo;
    private double valor;
    private double juros;
    private Date dataRealizacao;
    private Date dataPagamento;
    private int qtdParcelas;

    public Emprestimos(int idEmprestimo, double valor, double juros, Date dataRealizacao, Date dataPagamento, int qtdParcelas) {
        this.idEmprestimo = idEmprestimo;
        this.valor = valor;
        this.juros = juros;
        this.dataRealizacao = dataRealizacao;
        this.dataPagamento = dataPagamento;
        this.qtdParcelas = qtdParcelas;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }
}
