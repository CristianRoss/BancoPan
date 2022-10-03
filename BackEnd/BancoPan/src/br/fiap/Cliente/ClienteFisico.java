package br.fiap.Cliente;

import java.sql.Date;

public class ClienteFisico extends Cliente{

    private String cpf;
    private String sobrenome;
    private Date dataNascimento;

    public ClienteFisico(int idCliente, String nome, String email, String endereco,int telefone,String CEP , String cpf, String sobrenome, Date dataNascimento) {
        super(idCliente, nome, email, endereco, telefone,CEP);
        this.cpf = cpf;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        String aux="Nome: "+getNome();
        aux+="\nSobrenome: "+getSobrenome();
        aux+="\nCPF: "+getCpf();
        aux+="\nemail: "+getEmail();
        aux+="\nendereco: "+getEndereco();
        aux+="\nCEP: "+getCEP();
        aux+="\naniversario: "+getDataNascimento();
        return aux;
    }
}
