package br.fiap.Servicos.Cartoes;

public class CartaoDebito extends Cartao{

    private int idCartao;

    public CartaoDebito(int idCliente, int idCartao, int numero, double limite) {
        super(idCliente, idCartao, numero, limite );
    }

}
