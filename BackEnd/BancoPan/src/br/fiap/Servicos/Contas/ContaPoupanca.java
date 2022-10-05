package br.fiap.Servicos.Contas;

import br.fiap.Cliente.Cliente;

import java.sql.Date;

public class ContaPoupanca extends Conta{

    private Date dataAcrescimo;

    public ContaPoupanca(Cliente cliente, int idConta, int numero, double saldo, Date dataCriacao, Date dataAcrescimo, double juros) {
        super(cliente, idConta, numero, saldo, dataCriacao, juros);
        this.dataAcrescimo = dataAcrescimo;
    }

    public Date getDataAcrescimo() {
        return dataAcrescimo;
    }

    public void setDataAcrescimo(Date dataAcrescimo) {
        this.dataAcrescimo = dataAcrescimo;
    }
}
