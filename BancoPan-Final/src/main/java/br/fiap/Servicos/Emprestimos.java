package br.fiap.Servicos;

import br.fiap.Cliente.Cliente;

import java.sql.Date;

public class Emprestimos extends Servicos{

    private int idEmprestimo;
    private double valor;
    private double juros;
    private Date dataRealizacao;
    private Date diaPagamento;
    private int qtdParcelas;

    public Emprestimos(Cliente cliente, int idEmprestimo, double valor, double juros, Date dataRealizacao, Date diaPagamento, int qtdParcelas) {
        super(cliente);
        this.idEmprestimo = idEmprestimo;
        this.valor = valor;
        this.juros = juros;
        this.dataRealizacao = dataRealizacao;
        this.diaPagamento = diaPagamento;
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

    public Date getDiaPagamento() {
        return diaPagamento;
    }

    public void setDiaPagamento(Date dataPagamento) {
        this.diaPagamento = dataPagamento;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    @Override
    public String toString(){
        String aux="<b>Emprestimo:</b><br>";
        aux+="Valor: R$"+getValor();
        aux+="<br>Quantidade de parcelas: "+getQtdParcelas();
        aux+="<br>Juros: "+getJuros()*100+"%";
        aux+="<br>Dia do Parcela: "+ getDiaPagamento().getDate();
        return aux;
    }
}
