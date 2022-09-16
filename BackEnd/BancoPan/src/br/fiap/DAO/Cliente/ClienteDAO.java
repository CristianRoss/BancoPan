package br.fiap.DAO.Cliente;

import br.fiap.Cliente.Cliente;
import br.fiap.Cliente.ClienteFisico;
import br.fiap.Cliente.ClienteJuridico;
import br.fiap.Conexao.Conexao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClienteDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public ClienteDAO(){
        connection=new Conexao().conectar();
    }

    public void inserirCliente(Cliente cliente) {
        sql = "insert into clientes values(?,?,?,?,?,?,?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1 ,cliente.getIdCliente());
            ps.setString(4, cliente.getNome());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getEndereco());
            if (cliente instanceof ClienteFisico) {
                ps.setString(2, ((ClienteFisico) cliente).getCpf());
                ps.setNull(3, Types.VARCHAR);
                ps.setString(5, ((ClienteFisico) cliente).getSobrenome());
                ps.setDate(8, ((ClienteFisico) cliente).getDataNascimento());
            }else{
                ps.setNull(2, Types.VARCHAR);
                ps.setString(3, ((ClienteJuridico) cliente).getCnpj());
                ps.setNull(5, Types.VARCHAR);
                ps.setNull(8, Types.DATE);
            }

            ps.execute();

        }catch (SQLException e) {
            System.out.println("Falha ao inserir Cliente: "+e);
        }

    }

    public Cliente getCliente(int id) {
        sql = "select * from Clientes where id_cliente = ?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();

        }catch (SQLException e) {
            System.out.println("Falha ao pesquisar Cliente: "+e);
        }

        try {

            while (rs.next()) {
              if (rs.getString("cpf")!=null) {
                return new ClienteFisico(id, rs.getString("nome"),
                        rs.getString("email"), rs.getString("endereco"),
                        rs.getString("cpf"), rs.getString("sobrenome"),
                        rs.getDate("data_nascimento"));
              }else{
                  return new ClienteJuridico(id, rs.getString("cnpj"),
                          rs.getString("nome"),rs.getString("email"),
                          rs.getString("endereco"));
              }
            }

        }catch (SQLException e) {
            System.out.println("Falha ao pesquisar Cliente: "+e);
        }

        return null;
    }

    public List<Cliente> listarClientes(){
        List<Cliente> lista=new LinkedList<Cliente>();

        sql="select * from Clientes";

        try {

            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();

        }catch (SQLException e) {
            System.out.println("Falha ao listar clientes: "+e);
        }

        try {

            while (rs.next()) {
                if (rs.getString("cpf")!=null) {
                    lista.add(new ClienteFisico(rs.getInt("id_cliente"), rs.getString("nome"),
                            rs.getString("email"), rs.getString("endereco"),
                            rs.getString("cpf"), rs.getString("sobrenome"),
                            rs.getDate("data_nascimento")));
                }else{
                    lista.add(new ClienteJuridico(rs.getInt("id_cliente"), rs.getString("cnpj"),
                            rs.getString("nome"),rs.getString("email"),
                            rs.getString("endereco")));
                }
            }

        }catch (SQLException e) {
            System.out.println("Falha ao listar clientes: "+e);
        }

        return lista;
    }

}
