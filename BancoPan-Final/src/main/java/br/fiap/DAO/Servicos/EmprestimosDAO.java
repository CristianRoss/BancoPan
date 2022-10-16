package br.fiap.DAO.Servicos;

import br.fiap.Cliente.Cliente;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.Servicos.Emprestimos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmprestimosDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public boolean inserir(Emprestimos emp){
    	
    	connection=new Conexao().conectar();

        sql="insert into Emprestimos values(seq_emprestimos.nextval,?,?,?,?,?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setDouble(1, emp.getValor());
            ps.setDouble(2, emp.getJuros());
            ps.setInt(3, emp.getQtdParcelas());
            ps.setDate(4, emp.getDataRealizacao());
            ps.setDate(5, emp.getDiaPagamento());
            ps.setInt(6, emp.getCliente().getIdCliente());
            ps.execute();

        }catch (SQLException e){
            System.out.println("Erro ao inserir Emprestimo: "+e);
            try {
                connection.close();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
            return false;
        }
        
        try {
            connection.close();
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }
        return true;

    }

    public List<Emprestimos> lisarEmprestimos(int idCliente) {
    	 connection=new Conexao().conectar();
        List<Emprestimos> lista=new LinkedList<Emprestimos>();

        sql="select * from Emprestimos where id_cliente=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Falha ao listar emprestimos: "+e);
        }

        try {

            while (rs.next()) {
                Cliente cliente=new ClienteDAO().getCliente(rs.getInt("id_cliente"));
                lista.add(new Emprestimos(cliente, rs.getInt("id_emprestimo"),
                        rs.getDouble("valor"), rs.getDouble("juros"),
                        rs.getDate("data_realizacao"),rs.getDate("dia_parcela"),
                        rs.getInt("qtd_parcelas")));
            }

        }catch (SQLException e){
            System.out.println("Falha ao listar emprestimos: "+e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

}
