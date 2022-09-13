package br.fiap.Servicos;

public class Emprestimos extends Servicos{

    private int idEmprestimo;
    private double valor;
    private double juros;
    private String dataRealizacao;
    private String dataPagamento;
    private int qtdParcelas;

    public Emprestimos(int idEmprestimo, double valor, double juros, String dataRealizacao, String dataPagamento, int qtdParcelas) {
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

    public String getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(String dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }
}
