package br.fiap.Servicos;

import java.sql.Date;

public class PIX extends Servicos{

    private int idPIX;
    private String chavePix;
    private String chaveDestino;
    private double limite;
    private Date limiteHorario;
    private double valorPix;

    public PIX(int idPIX, String chavePix, String chaveDestino, double limite, Date limiteHorario, double valorPix) {
        this.idPIX = idPIX;
        this.chavePix = chavePix;
        this.chaveDestino = chaveDestino;
        this.limite = limite;
        this.limiteHorario = limiteHorario;
        this.valorPix = valorPix;
    }

    public int getIdPIX() {
        return idPIX;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getChaveDestino() {
        return chaveDestino;
    }

    public void setChaveDestino(String chaveDestino) {
        this.chaveDestino = chaveDestino;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
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
}
