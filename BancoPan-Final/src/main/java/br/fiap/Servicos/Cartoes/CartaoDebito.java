package br.fiap.Servicos.Cartoes;

import br.fiap.Cliente.Cliente;

public class CartaoDebito extends Cartao{

    public CartaoDebito(Cliente cliente, int idCartao, int numero, double limite) {
        super(cliente, idCartao, numero, limite );
    }

    @Override
    public String toString(){
        String aux="Cartao de Debito:\n";
        aux+="Numero: "+getNumero();
        aux+="\nLimite: "+getLimite();
        return aux;
    }
}
