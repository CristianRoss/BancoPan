package br.fiap.Cliente;

public class ClienteJuridico extends Cliente{

    private String cnpj;

    public ClienteJuridico(int idCliente, String cnpj, String nome, String email, String endereco, Long telefone,String CEP) {
        super(idCliente, nome, email, endereco,telefone,CEP);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
