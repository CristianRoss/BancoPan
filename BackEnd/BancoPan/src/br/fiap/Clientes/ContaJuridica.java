package br.fiap.Clientes;

import java.sql.Date;

public class ContaJuridica extends ContaCorrente{

    private int id_conta_jur;
    private String cnpj;

    public ContaJuridica(int id_cliente, String nome, int celular, String email, String endereco, int numero, double saldo, Date data_criacao, int status, int id_conta_jur, String cnpj) {
        super(id_cliente, nome, celular, email, endereco, numero, saldo, data_criacao, status);
        this.id_conta_jur = id_conta_jur;
        this.cnpj = cnpj;
    }

    public int getIdConta() {
        return id_conta_jur;
    }


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
