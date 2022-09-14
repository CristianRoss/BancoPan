package br.fiap.Clientes;

import java.sql.Date;

public class ContaFisica extends ContaCorrente{


    private int id_conta_fisica;
    private String sobrenome;
    private String cpf;

    private String sexo;

    public ContaFisica(int id_cliente, String nome, String sexo, int celular, String email, String endereco, int numero, double saldo, Date data_criacao, int status, int id_conta_fisica, String sobrenome, String cpf) {
        super(id_cliente, nome, celular, email, endereco, numero, saldo, data_criacao, status);
        this.sexo=sexo;
        this.id_conta_fisica = id_conta_fisica;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    public int getIdFisica() {
        return id_conta_fisica;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        String aux="Nome: "+getNome();
        aux+="\nSobrenome: "+getSobrenome();
        aux+="\nSexo: "+getSexo();
        aux+="\nCPF: "+getCpf();
        aux+="\nSaldo: "+getSaldo();
        aux+="\nNumero da Conta: "+getNumero();
        aux+="\nStatus: "+getStatus();
        aux+="\nData de Criação: "+getDataCriacao();
        aux+="\nId_Cliente: "+getIdCliente();
        aux+="\nId_Conta: "+getIdFisica();
        return aux;
    }

}
