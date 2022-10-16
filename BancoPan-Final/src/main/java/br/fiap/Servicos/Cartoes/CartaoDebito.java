package br.fiap.Servicos.Cartoes;

import br.fiap.Cliente.Cliente;

public class CartaoDebito extends Cartao{

    public CartaoDebito(Cliente cliente, int idCartao, int numero, double limite) {
        super(cliente, idCartao, numero, limite );
    }

    @Override
    public String toString(){
        String aux="<b>Cartao de Debito:</b><br>";
        aux+="Numero: "+getNumero();
        aux+="<br>Limite: "+getLimite();
        return aux;
    }
}
