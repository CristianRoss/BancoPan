package br.fiap.Cliente;

public class ClienteJuridico extends Cliente{

    private String cnpj;

    public ClienteJuridico(int idCliente, String cnpj, String nome, String email, String endereco) {
        super(idCliente, nome, email, endereco);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
