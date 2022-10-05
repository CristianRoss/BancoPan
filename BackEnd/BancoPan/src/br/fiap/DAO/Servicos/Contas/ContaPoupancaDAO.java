package br.fiap.DAO.Servicos.Contas;

import br.fiap.Cliente.Cliente;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.Servicos.Contas.ContaCorrente;
import br.fiap.Servicos.Contas.ContaPoupanca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaPoupancaDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public ContaPoupancaDAO(){
        connection=new Conexao().conectar();
    }

    public ContaPoupanca pesquisar(int id){

        sql="select * from conta_poupanca where id_conta_poupanca=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();


        }catch (SQLException e){
            System.out.println("Falha ao pesquisar Conta Poupanca: "+e);
        }

        try {

            Cliente cliente=new ClienteDAO().getCliente(rs.getInt("id_cliente"));
            return new ContaPoupanca(cliente,id, rs.getInt("numero"), rs.getDouble("saldo"),
                    rs.getDate("data_criacao"),rs.getDate("data_acrescimo"), rs.getDouble("juros"));


        }catch (SQLException e){
            System.out.println("Falha ao pesquisar Conta Poupanca: "+e);
        }



        return null;
    }

}
