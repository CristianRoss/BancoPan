package br.fiap.Cliente;

import java.sql.Date;

public class ClienteFisico extends Cliente{

    private String cpf;
    private String sobrenome;
    private Date dataNascimento;

    private String sexo;

    public ClienteFisico(int idCliente, String nome, String email, String endereco,Long telefone,String CEP , String cpf, String sobrenome, Date dataNascimento,String sexo) {
        super(idCliente, nome, email, endereco, telefone,CEP);
        this.cpf = cpf;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.sexo=sexo;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        String aux = " ";
        aux += getNome() + ", ";
        aux+= getSobrenome() + ", ";
        aux+= getCpf() + ", ";
        aux+= getEmail() + ", ";
        aux+= getEndereco() + ", ";
        aux+= getCEP() + ", ";
        aux+= getSexo() + ", ";
        aux+= getDataNascimento() + "";
        aux+= "";
        return aux;
    }
}
