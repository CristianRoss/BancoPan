package br.fiap.Servicos.Contas;

import java.sql.Date;

public class ContaCorrente extends Conta{

    private String chavePIX;

    public ContaCorrente(int idCliente, int idConta, int numero, double saldo, Date dataCriacao, double juros) {
        super(idCliente, idConta, numero, saldo, dataCriacao, juros);
    }

    public ContaCorrente(int idCliente, int idConta, int numero, double saldo, Date dataCriacao,double juros, String chavePIX) {
        super(idCliente, idConta, numero, saldo, dataCriacao, juros);
        this.chavePIX = chavePIX;
    }

    public String getChavePIX() {
        return chavePIX;
    }

    public void setChavePIX(String chavePIX) {
        this.chavePIX = chavePIX;
    }
}
