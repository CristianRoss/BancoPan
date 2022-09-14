package br.fiap.DAO;

import br.fiap.Clientes.TipoConta;
import br.fiap.Conexao.Conexao;
import br.fiap.Servicos.Financiamento;

import java.sql.*;

public class FinanciamentoDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public FinanciamentoDAO(){
        connection=new Conexao().conectar();
    }

    public void inserir(Financiamento f, TipoConta tipo) {

        sql="insert into Financiamento values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if (tipo==TipoConta.FISICA) {


            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, f.getIdFinanciamneto());
                ps.setDouble(2, f.getValorTotal());
                ps.setDouble(3, f.getValorParcelas());
                ps.setInt(4, f.getQtdParcelas());
                ps.setDouble(5, f.getJuros());
                ps.setDate(6, f.getDataRealizacao());
                ps.setDate(7, f.getData_fim());
                ps.setDouble(8, f.getEntrada());
                ps.setNull(9, Types.INTEGER);
                ps.setInt(10, f.getIdFisica());
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Erro ao inserir Financiamento: "+e);
            }

        }else{

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, f.getIdFinanciamneto());
                ps.setDouble(2, f.getValorTotal());
                ps.setDouble(3, f.getValorParcelas());
                ps.setInt(4, f.getQtdParcelas());
                ps.setDouble(5, f.getJuros());
                ps.setDate(6, f.getDataRealizacao());
                ps.setDate(7, f.getData_fim());
                ps.setDouble(8, f.getEntrada());
                ps.setInt(9, f.getIdJur());
                ps.setNull(10, Types.INTEGER);
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Erro ao inserir Financiamento: "+e);
            }

        }

    }

}
