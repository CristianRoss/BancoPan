package br.fiap.Usuario;

import br.fiap.Servicos.Contas.ContaCorrente;
import br.fiap.Util.Util;

public class Usuario {

    private ContaCorrente conta;
    private String senha;

    public Usuario(ContaCorrente conta, String senha) {
        this.conta = conta;
        this.senha = new Util().criptografar(senha);
    }

    public ContaCorrente getConta() {
        return conta;
    }

    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
