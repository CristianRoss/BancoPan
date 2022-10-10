package br.fiap.Usuario;

import br.fiap.Util.Util;

public class Usuario {

    private int numero;
    private String senha;

    public Usuario(int numero, String senha) {
        this.numero = numero;
        this.senha = new Util().criptografar(senha);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
