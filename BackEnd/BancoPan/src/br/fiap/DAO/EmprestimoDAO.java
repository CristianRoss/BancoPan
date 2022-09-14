package br.fiap.DAO;

import br.fiap.Clientes.TipoConta;
import br.fiap.Conexao.Conexao;
import br.fiap.Servicos.Emprestimos;

import java.sql.*;

public class EmprestimoDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public EmprestimoDAO(){
        connection=new Conexao().conectar();
    }

    public void inserir(Emprestimos emp, TipoConta tipo) {

        sql="insert into Emprestimos values(?, ?, ?, ?, ?, ?, ?, ?)";

        if (tipo==TipoConta.FISICA) {

            try {

                ps= connection.prepareStatement(sql);
                ps.setInt(1, emp.getIdEmprestimo());
                ps.setDouble(2, emp.getValor());
                ps.setDouble(3, emp.getJuros());
                ps.setDate(4, emp.getDataRealizacao());
                ps.setDate(5, emp.getDataPagamento());
                ps.setInt(6, emp.getQtdParcelas());
                ps.setNull(7, Types.INTEGER);
                ps.setInt(8, emp.getIdFisica());
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Falha ao inserir Emprestimo: "+e);
            }

        }else{

            try {

                ps= connection.prepareStatement(sql);
                ps.setInt(1, emp.getIdEmprestimo());
                ps.setDouble(2, emp.getValor());
                ps.setDouble(3, emp.getJuros());
                ps.setDate(4, emp.getDataRealizacao());
                ps.setDate(5, emp.getDataPagamento());
                ps.setInt(6, emp.getQtdParcelas());
                ps.setInt(7, emp.getIdJur());
                ps.setNull(8, Types.INTEGER);
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Falha ao inserir Emprestimo: "+e);
            }

        }

    }

    public Emprestimos pesquisar(int idConta,TipoConta tipo) {

        if (tipo==TipoConta.FISICA) {

            sql="select * from Emprestimos where id_conta_fisica = ?";

            try {

                ps= connection.prepareStatement(sql);
                ps.setInt(1, idConta);
                rs=ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar Emrpestimo: "+e);
            }

            try {

                while (rs.next()) {
                    Emprestimos emp=new Emprestimos(rs.getInt("id_emprestimo"),rs.getDouble("valor"),
                            rs.getDouble("juros"), rs.getDate("data_de_realizacao"),
                            rs.getDate("data_de_pagamento"),rs.getInt("qtd_parcelas"));
                    emp.setIdFisica(idConta);
                    return emp;
                }

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar Emrpestimo: "+e);
            }


        }else{

            sql="select * from Emprestimos where id_conta_jur = ?";

            try {

                ps= connection.prepareStatement(sql);
                ps.setInt(1, idConta);
                rs=ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar Emrpestimo: "+e);
            }

            try {

                while (rs.next()) {
                    Emprestimos emp=new Emprestimos(rs.getInt("id_emprestimo"),rs.getDouble("valor"),
                            rs.getDouble("juros"), rs.getDate("data_de_realizacao"),
                            rs.getDate("data_de_pagamento"),rs.getInt("qtd_parcelas"));
                    emp.setIdJur(idConta);
                    return emp;
                }

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar Emrpestimo: "+e);
            }

        }

        return null;
    }

}
