package br.fiap.DAO.Servicos;

import br.fiap.Cliente.Cliente;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.Servicos.Financiamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FinanciamentoDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public void inserir(Financiamentos f){

    	connection=new Conexao().conectar();
    	
        sql="insert into Financiamentos values(seq_financiamento.nextval,?,?,?,?,?,?,?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setDouble(1, f.getValorTotal());
            ps.setDouble(2, f.getValorParcelas());
            ps.setInt(3, f.getQtdParcelas());
            ps.setDouble(4, f.getJuros());
            ps.setDouble(5, f.getEntrada());
            ps.setDate(6, f.getDataRealizacao());
            ps.setDate(7, f.getData_fim());
            ps.setInt(8, f.getCliente().getIdCliente());
            ps.execute();

        }catch (SQLException e){
            System.out.println("Falha ao inserir financiamento: "+e);
        }
        
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Financiamentos> listarFinanciamentos(int idCliente){
    	
    	connection=new Conexao().conectar();
    	
        List<Financiamentos> lista=new LinkedList<Financiamentos>();

        sql="select * from Financiamentos where id_cliente=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Erro ao listar financiamentos : "+e);
        }

        try {

            while (rs.next()) {
                Cliente cliente=new ClienteDAO().getCliente(rs.getInt("id_cliente"));
                lista.add(new Financiamentos(cliente, rs.getInt("id_financiamento"), rs.getDouble("valor_total"),
                        rs.getDouble("valor_parcelas"), rs.getInt("qtd_parcelas"), rs.getDouble("juros"),
                        rs.getDate("data_realizacao"),rs.getDate("data_fim"), rs.getDouble("entrada")));
            }

        }catch (SQLException e){
            System.out.println("Erro ao listar financiamentos : "+e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

}
