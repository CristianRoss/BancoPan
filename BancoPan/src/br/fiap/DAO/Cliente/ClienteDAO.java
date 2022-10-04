package br.fiap.DAO.Cliente;

import br.fiap.Cliente.Cliente;
import br.fiap.Cliente.ClienteFisico;
import br.fiap.Cliente.ClienteJuridico;
import br.fiap.Conexao.Conexao;

import java.sql.*;
import java.util.*;

public class ClienteDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public ClienteDAO(){
        connection=new Conexao().conectar();
    }

    public void inserirCliente(Cliente cliente) {
        sql = "insert into clientes values(seq_clientes.nextval,?,?,?,?,?,?,?,?,?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setString(3, cliente.getNome());
            ps.setInt(5,cliente.getTelefone());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getEndereco());
            ps.setString(8, cliente.getCEP());
            if (cliente instanceof ClienteFisico) {
                ps.setString(1, ((ClienteFisico) cliente).getCpf());
                ps.setNull(2, Types.VARCHAR);
                ps.setString(4, ((ClienteFisico) cliente).getSobrenome());
                ps.setString(9,((ClienteFisico) cliente).getSexo());
                ps.setDate(10, ((ClienteFisico) cliente).getDataNascimento());
            }else{
                ps.setNull(1, Types.VARCHAR);
                ps.setString(2, ((ClienteJuridico) cliente).getCnpj());
                ps.setNull(4, Types.VARCHAR);
                ps.setNull(9, Types.VARCHAR);
                ps.setNull(10, Types.DATE);
            }

            ps.execute();

        }catch (SQLException e) {
            System.out.println("Falha ao inserir Cliente: "+e);
        }

    }

    public Cliente getCliente(String identificacao){

        if (identificacao.length()>13) {
            sql = "select * from Clientes where cnpj = ?";

            try {

                ps=connection.prepareStatement(sql);
                ps.setString(1,identificacao);
                rs=ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar cliente por cnpj: "+e);
            }

            try {

                while (rs.next()) {
                    return new ClienteJuridico(rs.getInt("id_cliente"), rs.getString("cnpj"),
                            rs.getString("nome"),rs.getString("email"),
                            rs.getString("endereco"), rs.getInt("telefone"),rs.getString("cep"));
                }

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar cliente por cnpj: "+e);
            }

        }else{
            sql = "select * from Clientes where cpf = ?";

            try {

                ps=connection.prepareStatement(sql);
                ps.setString(1,identificacao);
                rs=ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar cliente por cpf: "+e);
            }

            try {

                while (rs.next()) {
                    return new ClienteFisico(rs.getInt("id_cliente"), rs.getString("nome"),
                            rs.getString("email"), rs.getString("endereco"), rs.getInt("telefone")
                            ,rs.getString("cep"),rs.getString("cpf"), rs.getString("sobrenome"),
                            rs.getDate("data_nascimento"),rs.getString("sexo"));
                }

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar cliente por cpf: "+e);
            }
        }

        return null;
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
                        rs.getString("email"), rs.getString("endereco"), rs.getInt("telefone")
                        ,rs.getString("cep"),rs.getString("cpf"), rs.getString("sobrenome"),
                        rs.getDate("data_nascimento"), rs.getString("sexo"));
              }else{
                  return new ClienteJuridico(id, rs.getString("cnpj"),
                          rs.getString("nome"),rs.getString("email"),
                          rs.getString("endereco"), rs.getInt("telefone"),rs.getString("cep"));
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
                            rs.getString("email"), rs.getString("endereco"), rs.getInt("telefone"),
                            rs.getString("cep"),rs.getString("cpf"), rs.getString("sobrenome"),
                            rs.getDate("data_nascimento"), rs.getString("sexo")));
                }else{
                    lista.add(new ClienteJuridico(rs.getInt("id_cliente"), rs.getString("cnpj"),
                            rs.getString("nome"),rs.getString("email"),
                            rs.getString("endereco"), rs.getInt("telefone"),rs.getString("cep")));
                }
            }

        }catch (SQLException e) {
            System.out.println("Falha ao listar clientes: "+e);
        }

        return lista;
    }

}
