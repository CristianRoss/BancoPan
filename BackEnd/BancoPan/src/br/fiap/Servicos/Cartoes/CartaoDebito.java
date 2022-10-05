package br.fiap.Servicos.Cartoes;

import br.fiap.Cliente.Cliente;

public class CartaoDebito extends Cartao{

    private int idCartao;

    public CartaoDebito(Cliente cliente, int idCartao, int numero, double limite) {
        super(cliente, idCartao, numero, limite );
    }

}
