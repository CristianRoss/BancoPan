package br.fiap.DAO.Servicos;

import br.fiap.Cliente.Cliente;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
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

            sql="insert into cartao_debito values(seq_cartao_debito.nextval,?,?,?)";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, cartao.getNumero());
                ps.setDouble(2, cartao.getLimite());
                ps.setInt(3, cartao.getCliente().getIdCliente());
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Falha ao inserir Cartao de Debito: "+e);
            }

        }else {

            sql="insert into cartao_credito values(seq_cartao_credito.nextval,?,?,?,?,?,?,?)";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, cartao.getIdCartao());
                ps.setInt(2, cartao.getNumero());
                ps.setInt(3, ((CartaoCredito)cartao).getCvv());
                ps.setDouble(4, ((CartaoCredito)cartao).getFatura());
                ps.setDate(5, ((CartaoCredito)cartao).getDataVencimento());
                ps.setDouble(6, ((CartaoCredito)cartao).getJurosCredito());
                ps.setDouble(7, ((CartaoCredito)cartao).getLimite());
                ps.setInt(8, cartao.getCliente().getIdCliente());
                ps.execute();

            }catch (SQLException e) {
                System.out.println("Falha ao inserir Cartao de Credito: "+e);
            }

        }

    }

    public List<Cartao> listarCartoes(int idCliente){
        List<Cartao> lista=new LinkedList<Cartao>();

        sql="select * from cartao_debito where ID_CLIENTE=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar cartao de debito: "+e);
        }

        try {

            while (rs.next()) {
                Cliente cliente=new ClienteDAO().getCliente(rs.getInt("id_cliente"));
                lista.add(new CartaoDebito(cliente,rs.getInt("id_cartao_debito"), rs.getInt("numero"),
                        rs.getDouble("limite")));
            }

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar cartao de debito: "+e);
        }

        sql="select * from cartao_credito where ID_CLIENTE=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar cartao de credito: "+e);
        }

        try {

            while (rs.next()) {
                Cliente cliente=new ClienteDAO().getCliente(rs.getInt("id_cliente"));
                lista.add(new CartaoCredito(cliente,
                        rs.getInt("ID_CARTAO_CREDITO"), rs.getInt("NUMERO_CREDITO"),
                        rs.getDouble("LIMITE_CREDITO"), rs.getDouble("fatura"),
                        rs.getDate("data_vencimento"),rs.getDouble("juros_credito"), rs.getInt("cvv")));
            }

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar cartao de credito: "+e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

}
