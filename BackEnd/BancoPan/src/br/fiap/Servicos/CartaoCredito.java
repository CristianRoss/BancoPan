package br.fiap.Servicos;

public class CartaoCredito extends Cartao{

    private int idCartaoCredito;
    private double fatura;
    private String dataVencimento;
    private double jurosVencimento;

    public CartaoCredito(int numero, int limite, int idCartaoCredito, double fatura, String dataVencimento, double jurosVencimento) {
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

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getJurosVencimento() {
        return jurosVencimento;
    }

    public void setJurosVencimento(double jurosVencimento) {
        this.jurosVencimento = jurosVencimento;
    }
}
