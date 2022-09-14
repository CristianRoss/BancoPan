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

        sql = "insert into CONTA_CORRENTE_JURIDICA(id_cliente, id_conta_jur, cnpj, saldo, data_criacao) values(?, ?, ?, ?, ?)";

        try {

            ps = connection.prepareStatement(sql);
            ps.setInt(1, conta.getIdCliente());
            ps.setInt(2, conta.getIdConta());
            ps.setString(3, conta.getCnpj());
            ps.setDouble(4, conta.getSaldo());
            ps.setDate(5, conta.getDataCriacao());
            ps.execute();

        }catch (SQLException e) {
            System.out.println("Erro ao inserir Conta Juridica: "+e);
        }

        sql="insert into cliente values(?,?,?,?,?,?,?,?,?,?)";

        try{
            ps=connection.prepareStatement(sql);
            ps.setInt(1, conta.getIdCliente());
            ps.setString(2, conta.getNome());
            ps.setInt(3, conta.getCelular());
            ps.setString(4, conta.getEmail());
            ps.setString(5, conta.getEndereco());
            ps.setNull(6, Types.INTEGER);
            ps.setInt(7, conta.getIdConta());
            ps.setInt(8, conta.getNumero());
            ps.setInt(9, conta.getStatus());
            System.out.println(sql);
            ps.execute();
        }catch (SQLException e) {
            System.out.println("Erro ao inserir Cliente: "+e);
        }

    }


    public ContaJuridica pesquisarConta(int id) {
        String nome = null,email=null,endereco=null;

        int cel=0,numero=0,status=0;

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
                    cel=rs.getInt("celular");
                    email= rs.getString("email");
                    endereco=rs.getString("endereco");
                    numero=rs.getInt("numero");
                    status= rs.getInt(status);
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
                    return new ContaJuridica(id,nome,cel,email,endereco,
                            numero,rs.getDouble("saldo"),rs.getDate("data_criacao"),
                            status,rs.getInt("id_conta_jur"),
                            rs.getString("cnpj"));
                }
            }
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar conta Juridica: "+e);
        }

        return null;
    }


}
