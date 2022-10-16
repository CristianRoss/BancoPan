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
    
    @Override
    public String toString() {
        String aux = " ";
        aux += getNome() + ", ";         //Nome
        aux+= " , ";                     // Sobrenome
        aux+= " , ";                     // CPF
        aux+= getCnpj() + ",";           // CNPJ 
        aux+= getEmail() + ", ";         // email
        aux+= getEndereco() + ", ";      // endereco
        aux+= getCEP() + ", ";           //CEP 
        aux+= " , ";                     // Sexo
        aux+= " ,  ";                    // aniversario
        return aux;
    }
}
