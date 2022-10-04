package br.fiap.Servicos;

import br.fiap.Cliente.Cliente;

public class Maquininha extends Servicos{

    private int idMaquininha;
    private double taxa;
    private int vendas;
    private int vendasPorMes;

    public Maquininha(Cliente Cliente, int idMaquininha, double taxa, int vendas, int vendasPorMes) {
        super(Cliente);
        this.idMaquininha = idMaquininha;
        this.taxa = taxa;
        this.vendas = vendas;
        this.vendasPorMes = vendasPorMes;
    }

    public int getIdMaquininha() {
        return idMaquininha;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public int getVendas() {
        return vendas;
    }

    public void setVendas(int vendas) {
        this.vendas = vendas;
    }

    public int getVendasPorMes() {
        return vendasPorMes;
    }

    public void setVendasPorMes(int vendasPorMes) {
        this.vendasPorMes = vendasPorMes;
    }

}
