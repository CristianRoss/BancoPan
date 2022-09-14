package br.fiap.DAO;

import br.fiap.Clientes.TipoConta;
import br.fiap.Conexao.Conexao;
import br.fiap.Servicos.CartaoDebito;

import java.sql.*;

public class CartaoDebitoDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public CartaoDebitoDAO(){
        connection=new Conexao().conectar();
    }

    public void inserirCartaoDAO(CartaoDebito cartao, TipoConta tipo){
        if (tipo==TipoConta.FISICA) {

            sql = "insert into CARTAO_DEBITO values(?, ?, ?, ?, ?)";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, cartao.getIdCartao());
                ps.setInt(2, cartao.getNumero());
                ps.setDouble(3, cartao.getLimite());
                ps.setNull(4, Types.INTEGER);
                ps.setInt(5, cartao.getIdFisica());
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Falha ao inseir cartao: "+e);
            }

        }else{

            sql = "insert into CARTAO_DEBITO values(?, ?, ?, ?, ?)";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, cartao.getIdCartao());
                ps.setInt(2, cartao.getNumero());
                ps.setDouble(3, cartao.getLimite());
                ps.setInt(4, cartao.getIdJur());
                ps.setNull(5, Types.INTEGER);
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Falha ao inseir cartao: "+e);
            }

        }
    }

    public CartaoDebito pesquisar(int idConta,TipoConta tipo) {

        if (tipo==TipoConta.FISICA) {

            sql="select * from cartao_debito where id_conta_fisica = ?";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, idConta);
                rs=ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar CartaoDeibto: "+e);
            }

            try{

                CartaoDebito cartao = new CartaoDebito(rs.getInt("numero"),rs.getDouble("limite"), rs.getInt("id_cartao_debito"));
                cartao.setIdFisica(idConta);
                return cartao;

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar CartaoDeibto: "+e);
            }

        }else{

            sql="select * from cartao_debito where id_conta_jur = ?";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, idConta);
                rs=ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar CartaoDeibto: "+e);
            }

            try{

                CartaoDebito cartao = new CartaoDebito(rs.getInt("numero"),rs.getDouble("limite"), rs.getInt("id_cartao_debito"));
                cartao.setIdJur(idConta);
                return cartao;

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar CartaoDeibto: "+e);
            }

        }

        return null;
    }

}
