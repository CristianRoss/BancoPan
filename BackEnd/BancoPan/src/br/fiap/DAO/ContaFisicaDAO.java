package br.fiap.DAO;

import br.fiap.Clientes.Cliente;
import br.fiap.Clientes.ContaFisica;
import br.fiap.Conexao.Conexao;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public class ContaFisicaDAO {



    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public ContaFisicaDAO(){
        connection=new Conexao().conectar();
    }

    public void inserirContaFisica(ContaFisica conta) {

        sql = "insert into CONTA_CORRENTE_FISICA values(?, ?, ?, ?, ?, ?, ?)";

        try {

            ps = connection.prepareStatement(sql);
            ps.setInt(1, conta.getIdCliente());
            ps.setInt(2, conta.getIdFisica());
            ps.setString(3, conta.getCpf());
            ps.setString(4, conta.getSobrenome());
            ps.setDouble(5, conta.getSaldo());
            ps.setString(6, conta.getSexo());
            ps.setDate(7, conta.getDataCriacao());
            ps.execute();

        }catch (SQLException e) {
            System.out.println("Erro ao inserir Conta Fisica: "+e);
        }

        sql="insert into cliente values(?,?,?,?,?,?,?,?,?)";

        try{
            ps=connection.prepareStatement(sql);
            ps.setInt(1, conta.getIdCliente());
            ps.setString(2, conta.getNome());
            ps.setInt(3, conta.getCelular());
            ps.setString(4, conta.getEmail());
            ps.setString(5, conta.getEndereco());
            ps.setInt(6, conta.getIdFisica());
            ps.setNull(7, Types.INTEGER);
            ps.setInt(8, conta.getNumero());
            ps.setInt(9, conta.getStatus());
            ps.execute();
        }catch (SQLException e) {
            System.out.println("Erro ao inserir Cliente: "+e);
        }

    }


    public ContaFisica pesquisarConta(int id) {
        String nome = null,email=null,endereco=null;

        int cel=0,numero=0,status=0;

        sql = "select * from CLIENTE where id_cliente = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar conta Fisica: "+e);
        }

        try {
            while (rs.next()) {
                if (rs.getInt("id_cliente")==id) {
                     nome=rs.getString("nome");
                     cel=rs.getInt("celular");
                     email= rs.getString("email");
                     endereco=rs.getString("endereco");
                     numero=rs.getInt("numero");
                     status=rs.getInt("status");
                }
            }
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar conta Fisica: "+e);
        }

        sql = "select * from CONTA_CORRENTE_FISICA where id_cliente = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar conta Fisica: "+e);
        }

        try {
            while (rs.next()) {
                if (rs.getInt("id_cliente")==id) {
                    return new ContaFisica(id,nome,rs.getString("sexo"),cel,email,endereco,
                            numero,rs.getDouble("saldo"),rs.getDate("data_criacao"),
                            status,rs.getInt("id_conta_fisica"), rs.getString("sobrenome"),
                            rs.getString("cpf"));
                }
            }
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar conta Fisica: "+e);
        }

       return null;
    }


}
