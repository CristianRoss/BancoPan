package br.fiap.Servicos;

public class CartaoDebito extends Cartao{

    private int idCartaoDebito;


    public CartaoDebito(int numero, int limite, int idCartao) {
        super(numero, limite);
        this.idCartaoDebito = idCartao;
    }

    public int getIdCartao() {
        return idCartaoDebito;
    }

}
