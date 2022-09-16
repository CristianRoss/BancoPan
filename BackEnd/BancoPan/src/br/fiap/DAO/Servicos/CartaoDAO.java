package br.fiap.DAO.Servicos;

import br.fiap.Conexao.Conexao;
import br.fiap.Servicos.Cartoes.Cartao;
import br.fiap.Servicos.Cartoes.CartaoCredito;
import br.fiap.Servicos.Cartoes.CartaoDebito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CartaoDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public CartaoDAO(){
        connection=new Conexao().conectar();
    }

    public void inserir(Cartao cartao) {

        if (cartao instanceof CartaoDebito) {

            sql="insert into cartao_debito values(?,?,?,?)";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, cartao.getIdCartao());
                ps.setInt(2, cartao.getNumero());
                ps.setDouble(3, cartao.getLimite());
                ps.setInt(4, cartao.getIdCliente());
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Falha ao inserir Cartao de Debito: "+e);
            }

        }else {

            sql="insert into cartao_credito values(?,?,?,?,?,?,?)";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, cartao.getIdCartao());
                ps.setInt(2, cartao.getNumero());
                ps.setDouble(3, ((CartaoCredito)cartao).getFatura());
                ps.setDate(4, ((CartaoCredito)cartao).getDataVencimento());
                ps.setDouble(5, ((CartaoCredito)cartao).getJurosCredito());
                ps.setDouble(6, ((CartaoCredito)cartao).getLimite());
                ps.setInt(7, cartao.getIdCliente());
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Falha ao inserir Cartao de Credito: "+e);
            }

        }

    }

    public List<Cartao> listarCartoes(int idCliente){
        List<Cartao> lista=new LinkedList<Cartao>();

        sql="select * from cartao_debito where id_cliente=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar cartao de debito: "+e);
        }

        try {

            while (rs.next()) {
                lista.add(new CartaoDebito(idCliente,rs.getInt("id_cartao_debito"), rs.getInt("numro"),
                        rs.getDouble("limite")));
            }

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar cartao de debito: "+e);
        }

        sql="select * from cartao_credito where id_cliente=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar cartao de credito: "+e);
        }

        try {

            while (rs.next()) {
                lista.add(new CartaoCredito(idCliente,
                        rs.getInt("id_cartao_debito"), rs.getInt("numro"),
                        rs.getDouble("limite"), rs.getDouble("fatura"),
                        rs.getDate("data_vencimento"),rs.getDouble("juros_credito")));
            }

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar cartao de credito: "+e);
        }

        return lista;
    }

}
