package br.fiap.Servicos.Contas;

import br.fiap.Cliente.Cliente;

import java.sql.Date;

public class ContaCorrente extends Conta {

	private String chavePIX;

	public ContaCorrente(Cliente cliente, int idConta, int numero, double saldo, Date dataCriacao, double juros) {
		super(cliente, idConta, numero, saldo, dataCriacao, juros);
	}

	public ContaCorrente(Cliente cliente, int idConta, int numero, double saldo, Date dataCriacao, double juros,
			String chavePIX) {
		super(cliente, idConta, numero, saldo, dataCriacao, juros);
		this.chavePIX = chavePIX;
	}

	public String getChavePIX() {
		return chavePIX;
	}

	public void setChavePIX(String chavePIX) {
		this.chavePIX = chavePIX;
	}

	@Override
	public String toString() {
		String aux = "Conta Corrente:\n";
		aux += "Numero: " + getNumero();
		aux += "\nSaldo: " + getSaldo();
		if (chavePIX != null) {
			aux += "\nChavePIX: " + getChavePIX();
		}
		return aux;
	}
}
