package br.fiap.Servicos.Cartoes;

import br.fiap.Cliente.Cliente;

import java.sql.Date;
import java.util.Random;

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
    
    public CartaoCredito(Cliente cliente, int idCartao, int numero, double limite, Date dataVencimento) {
        super(cliente, idCartao, numero, limite);
        this.dataVencimento = dataVencimento;
        this.jurosCredito = 6.17;
        this.cvv= new Random().nextInt(900) + 100;
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
    
    @Override
    public String toString() {
    	String aux="<B>Cartão de Crédito:</B><br>";
    	aux+="Numero: "+getNumero();
    	aux+="<br>CVV: "+getCvv();
    	aux+="<br>Fatura: R$"+getFatura();
    	aux+="<br>Limite: "+getLimite();
    	aux+="<br>Dia da Fatura: "+getDataVencimento().getDate();
    	return aux;
    }
    
}
