package br.fiap.DAO;

import br.fiap.Clientes.TipoConta;
import br.fiap.Conexao.Conexao;
import br.fiap.Servicos.CartaoCredito;

import java.sql.*;

public class CartaoCreditoDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public CartaoCreditoDAO(){
        connection=new Conexao().conectar();
    }

    public void inserirCartao(CartaoCredito cartao, TipoConta tipo) {

        if (tipo==TipoConta.FISICA) {

            sql="insert into cartao_de_credito values(?, ?, ?, ?, ?, ?, ?, ?)";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, cartao.getIdCartaoCredito());
                ps.setInt(2, cartao.getNumero());
                ps.setDouble(3, cartao.getFatura());
                ps.setDate(4, cartao.getDataVencimento());
                ps.setDouble(5, cartao.getJurosVencimento());
                ps.setDouble(6, cartao.getLimite());
                ps.setNull(7, Types.INTEGER);
                ps.setInt(8, cartao.getIdFisica());
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Erro ao inserir cartao de credito: "+e);
            }

        }else{

            sql="insert into cartao_de_credito values(?, ?, ?, ?, ?, ?, ?, ?)";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, cartao.getIdCartaoCredito());
                ps.setInt(2, cartao.getNumero());
                ps.setDouble(3, cartao.getFatura());
                ps.setDate(4, cartao.getDataVencimento());
                ps.setDouble(5, cartao.getJurosVencimento());
                ps.setDouble(6, cartao.getLimite());
                ps.setInt(7, cartao.getIdJur());
                ps.setNull(8, Types.INTEGER);
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Erro ao inserir cartao de credito: "+e);
            }

        }

    }

    public CartaoCredito pesquisar(int idConta,TipoConta tipo) {

        if (tipo==TipoConta.FISICA) {

            sql = "select * from cartao_de_credito where id_conta_fisica = ?";

            try{

                ps= connection.prepareStatement(sql);
                ps.setInt(1, idConta);
                rs=ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar cartao de credito: "+e);
            }

            try {

                CartaoCredito cartao= new CartaoCredito(rs.getInt("numero"), rs.getDouble("limite"), rs.getInt("id_cartap_credito"),
                        rs.getDouble("fatura"),rs.getDate("data_vencimento"),rs.getDouble("juros_credito"));
                cartao.setIdFisica(idConta);
                return cartao;

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar cartao de credito: "+e);
            }

        }else{

            sql = "select * from cartao_de_credito where id_conta_jur = ?";

            try{

                ps= connection.prepareStatement(sql);
                ps.setInt(1, idConta);
                rs=ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar cartao de credito: "+e);
            }

            try {

                CartaoCredito cartao= new CartaoCredito(rs.getInt("numero"), rs.getDouble("limite"), rs.getInt("id_cartap_credito"),
                        rs.getDouble("fatura"),rs.getDate("data_vencimento"),rs.getDouble("juros_credito"));
                cartao.setIdJur(idConta);
                return cartao;

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar cartao de credito: "+e);
            }

        }

        return null;
    }

}
