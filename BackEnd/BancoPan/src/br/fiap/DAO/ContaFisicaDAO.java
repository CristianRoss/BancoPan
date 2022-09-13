package br.fiap.DAO;

import br.fiap.Clientes.ContaFisica;
import br.fiap.Conexao.Conexao;

import java.sql.*;

public class ContaFisicaDAO {



    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public ContaFisicaDAO(){
        connection=new Conexao().conectar();
    }

    public void inserirContaFisica(ContaFisica conta) {

        sql = "insert into CONTA_CORRENTE_FISICA(id_cliente, id_conta_fisica, cpf, sobrenome, numero, saldo, status, data_criacao) values(?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            ps = connection.prepareStatement(sql);
            ps.setInt(1, conta.getIdCliente());
            ps.setInt(2, conta.getIdFisica());
            ps.setString(3, conta.getCpf());
            ps.setString(4, conta.getSobrenome());
            ps.setInt(5, conta.getNumero());
            ps.setDouble(6, conta.getSaldo());
            ps.setInt(7, conta.getStatus());
            ps.setDate(8, conta.getDataCriacao());
            ps.execute();

        }catch (SQLException e) {
            System.out.println("Erro ao inserir Conta Fisica: "+e);
        }

        sql="insert into cliente values(?,?,?,?,?,?,?,?)";

        try{
            ps=connection.prepareStatement(sql);
            ps.setInt(1, conta.getIdCliente());
            ps.setString(2, conta.getNome());
            ps.setString(3, conta.getSexo());
            ps.setInt(4, conta.getCelular());
            ps.setString(5, conta.getEmail());
            ps.setString(6, conta.getEndereco());
            ps.setInt(7, conta.getIdFisica());
            ps.setNull(8, Types.INTEGER);
            System.out.println(sql);
            ps.execute();
        }catch (SQLException e) {
            System.out.println("Erro ao inserir Cliente: "+e);
        }

    }


    public ContaFisica pesquisarConta(int id) {
        String nome = null,sexo=null,email=null,endereco=null;

        int cel=0;

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
                     sexo=rs.getString("sexo");
                     cel=rs.getInt("celular");
                     email= rs.getString("email");
                     endereco=rs.getString("endereco");
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
                    return new ContaFisica(id,nome,sexo,cel,email,endereco,
                            rs.getInt("numero"),rs.getDouble("saldo"),rs.getDate("data_criacao"),
                            rs.getInt("status"),rs.getInt("id_conta_fisica"), rs.getString("sobrenome"),
                            rs.getString("cpf"));
                }
            }
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar conta Fisica: "+e);
        }

       return null;
    }


}
