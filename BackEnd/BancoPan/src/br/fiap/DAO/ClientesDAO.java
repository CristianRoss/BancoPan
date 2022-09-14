package br.fiap.DAO;

import br.fiap.Clientes.ContaCorrente;
import br.fiap.Clientes.ContaFisica;
import br.fiap.Clientes.ContaJuridica;
import br.fiap.Conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

public class ClientesDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public ClientesDAO(){
        connection=new Conexao().conectar();
    }

    public int getIdMax() {
        sql = "select * from cliente";

        int id=0;

        try {

            ps=connection.prepareStatement(sql);
            rs= ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Erro ao pegar numero maximo de ids: "+e);
        }

        try {

            while (rs.next()) {
                    id=rs.getInt("id_cliente");
            }


        }catch (SQLException e){
            System.out.println("Erro ao pegar numero maximo de ids: "+e);
        }
        return id;
    }

    public TreeMap listarContas() {

        TreeMap lista = new TreeMap<Integer , ContaCorrente>();


        sql = "select * from CLIENTE,CONTA_CORRENTE_FISICA";

        try {

            ps = connection.prepareStatement(sql);
            rs=ps.executeQuery();

        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar Cliente: "+e);
        }

        try {

            while (rs.next()) {
                if (!lista.containsKey(rs.getInt("id_cliente"))) {
                    lista.put(rs.getInt("id_cliente"),
                            new ContaFisica(rs.getInt("id_cliente"), rs.getString("nome"),
                                    rs.getString("sexo"), rs.getInt("celular"), rs.getString("email"),
                                    rs.getString("endereco"),rs.getInt("numero"),rs.getDouble("saldo"),rs.getDate("data_criacao"),
                                    rs.getInt("status"),rs.getInt("id_conta_fisica"), rs.getString("sobrenome"),
                                    rs.getString("cpf")));
                }
            }

        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar Cliente: "+e);
        }

        sql = "select * from CLIENTE,CONTA_CORRENTE_JURIDICA";

        try {

            ps = connection.prepareStatement(sql);
            rs=ps.executeQuery();

        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar Cliente: "+e);
        }

        try {

            while (rs.next()) {
                if (!lista.containsKey(rs.getInt("id_cliente"))) {
                    lista.put(rs.getInt("id_cliente"),
                            new ContaJuridica(rs.getInt("id_cliente"),rs.getString("nome"),
                                    rs.getInt("celular"), rs.getString("email"),
                                    rs.getString("endereco"),rs.getInt("numero"),rs.getDouble("saldo"),rs.getDate("data_criacao"),
                                    rs.getInt("status"), rs.getInt("id_conta_jur"), rs.getString("cnpj")));

                }
            }

        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar Cliente: "+e);
        }

        return lista;
    }

}
