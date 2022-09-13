package br.fiap.DAO;

import br.fiap.Clientes.ContaJuridica;
import br.fiap.Conexao.Conexao;

import java.sql.*;

public class ContaJuridicaDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public ContaJuridicaDAO(){
        connection=new Conexao().conectar();
    }

    public void inserirContaJuridica(ContaJuridica conta) {

        sql = "insert into CONTA_CORRENTE_JURIDICA(id_cliente, id_conta_jur, cnpj, numero, saldo, data_criacao, status) values(?, ?, ?, ?, ?, ?, ?)";

        try {

            ps = connection.prepareStatement(sql);
            ps.setInt(1, conta.getIdCliente());
            ps.setInt(2, conta.getIdConta());
            ps.setString(3, conta.getCnpj());
            ps.setInt(5, conta.getNumero());
            ps.setDouble(6, conta.getSaldo());
            ps.setDate(7, conta.getDataCriacao());
            ps.setInt(8, conta.getStatus());
            ps.execute();

        }catch (SQLException e) {
            System.out.println("Erro ao inserir Conta Juridica: "+e);
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
            ps.setNull(7, Types.INTEGER);
            ps.setInt(8, conta.getIdConta());
            System.out.println(sql);
            ps.execute();
        }catch (SQLException e) {
            System.out.println("Erro ao inserir Cliente: "+e);
        }

    }


    public ContaJuridica pesquisarConta(int id) {
        String nome = null,sexo=null,email=null,endereco=null;

        int cel=0;

        sql = "select * from CLIENTE where id_cliente = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar conta Juridica: "+e);
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
            System.out.println("Erro ao pesquisar conta Juridica: "+e);
        }

        sql = "select * from CONTA_CORRENTE_JURIDICA where id_cliente = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar conta Juridica: "+e);
        }

        try {
            while (rs.next()) {
                if (rs.getInt("id_cliente")==id) {
                    return new ContaJuridica(id,nome,sexo,cel,email,endereco,
                            rs.getInt("numero"),rs.getDouble("saldo"),rs.getDate("data_criacao"),
                            rs.getInt("status"),rs.getInt("id_conta_jur"),
                            rs.getString("cnpj"));
                }
            }
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar conta Juridica: "+e);
        }

        return null;
    }


}
