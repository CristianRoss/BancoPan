package br.fiap.Servicos;

import br.fiap.Cliente.Cliente;

import java.sql.Date;

public class PIX extends Servicos{

    private int idPIX;
    private String chaveConta;
    private String chaveDestino;

    private Date data;
    private double limiteValor;
    private Date limiteHorario;
    private double valorPix;

    public PIX(Cliente cliente, int idPIX, String chaveConta, String chaveDestino, double valorPix, Date data) {
        super(cliente);
        this.idPIX = idPIX;
        this.chaveConta = chaveConta;
        this.chaveDestino = chaveDestino;
        this.valorPix = valorPix;
        this.data=data;
    }

    public PIX(Cliente cliente, int idPIX, String chaveConta, String chaveDestino, double limiteValor, double valorPix, Date data) {
        super(cliente);
        this.idPIX = idPIX;
        this.chaveConta = chaveConta;
        this.chaveDestino = chaveDestino;
        this.limiteValor = limiteValor;
        this.valorPix = valorPix;
        this.data=data;
    }

    public PIX(Cliente cliente, int idPIX, String chaveConta, String chaveDestino, Date limiteHorario, double valorPix, Date data) {
        super(cliente);
        this.idPIX = idPIX;
        this.chaveConta = chaveConta;
        this.chaveDestino = chaveDestino;
        this.limiteHorario = limiteHorario;
        this.valorPix = valorPix;
        this.data=data;
    }

    public PIX(Cliente cliente, int idPIX, String chaveConta, String chaveDestino, double limiteValor, Date limiteHorario, double valorPix, Date data) {
        super(cliente);
        this.idPIX = idPIX;
        this.chaveConta = chaveConta;
        this.chaveDestino = chaveDestino;
        this.limiteValor = limiteValor;
        this.limiteHorario = limiteHorario;
        this.valorPix = valorPix;
        this.data=data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdPIX() {
        return idPIX;
    }

    public String getChaveConta() {
        return chaveConta;
    }

    public void setChaveConta(String chavePix) {
        this.chaveConta = chavePix;
    }

    public String getChaveDestino() {
        return chaveDestino;
    }

    public void setChaveDestino(String chaveDestino) {
        this.chaveDestino = chaveDestino;
    }

    public double getLimiteValor() {
        return limiteValor;
    }

    public void setLimiteValor(double limite) {
        this.limiteValor = limite;
    }

    public Date getLimiteHorario() {
        return limiteHorario;
    }

    public void setLimiteHorario(Date limiteHorario) {
        this.limiteHorario = limiteHorario;
    }

    public double getValorPix() {
        return valorPix;
    }

    public void setValorPix(double valorPix) {
        this.valorPix = valorPix;
    }

    @Override
    public String toString(){
        String aux="PIX:<br>";
        aux+="Valor: "+getValorPix();
        aux+="\nPara: "+getChaveDestino();
        aux+="\nDia: "+getData();
        aux+="\nLimite"+ getLimiteValor();
        return aux;
    }
}
