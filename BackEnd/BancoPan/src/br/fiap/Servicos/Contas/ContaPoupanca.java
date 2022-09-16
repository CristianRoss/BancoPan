package br.fiap.Servicos.Contas;

import java.sql.Date;

public class ContaPoupanca extends Conta{

    private Date dataAcrescimo;

    public ContaPoupanca(int idCliente, int idConta, int numero, double saldo, Date dataCriacao, Date dataAcrescimo, double juros) {
        super(idCliente, idConta, numero, saldo, dataCriacao, juros);
        this.dataAcrescimo = dataAcrescimo;
    }

    public Date getDataAcrescimo() {
        return dataAcrescimo;
    }

    public void setDataAcrescimo(Date dataAcrescimo) {
        this.dataAcrescimo = dataAcrescimo;
    }
}
