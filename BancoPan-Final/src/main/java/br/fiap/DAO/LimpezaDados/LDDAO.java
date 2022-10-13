package br.fiap.DAO.LimpezaDados;

import br.fiap.Cliente.Cliente;
import br.fiap.Cliente.ClienteFisico;
import br.fiap.Cliente.ClienteJuridico;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.Servicos.Cartoes.CartaoDebito;
import br.fiap.Servicos.Contas.ContaCorrente;
import br.fiap.Servicos.Servicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LDDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    private int cont;

    public LDDAO(){
        connection=new Conexao().conectar();
    }

    public Map<String, Cliente> getClientes(String nomeTabela) {
        Map<String, Cliente> lista=new HashMap<String, Cliente>();

        sql="select * from "+nomeTabela;

        try {

            ps= connection.prepareStatement(sql);
            rs=ps.executeQuery();

        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar tabela suja : "+e);
            return lista;
        }

        try {

            while (rs.next()){
                if (rs.getString("cpf")!=null) {
                    lista.put(rs.getString("cpf"), new ClienteFisico(cont, rs.getString("nome").toUpperCase(),rs.getString("email"),rs.getString("endereco"), rs.getLong("telefone"),rs.getString("cep"), rs.getString("cpf"), rs.getString("sobrenome").toUpperCase(),rs.getDate("aniversario"),rs.getString("sexo")));
                }else {
                    lista.put(rs.getString("cnpj"), new ClienteJuridico(cont, rs.getString("cnpj"), rs.getString("nome").toUpperCase(), rs.getString("email"), rs.getString("endereco"), rs.getLong("telefone"),rs.getString("cep")));
                }
            }

        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar tabela suja : "+e);
            return lista;
        }


        return lista;
    }

    public void inserirNovosClientes(Map<String, Cliente> lista){
        ClienteDAO dao=new ClienteDAO();
        for (Map.Entry<String,Cliente> clientes:lista.entrySet()) {
             dao.inserirCliente(clientes.getValue());
        }
    }

    @Deprecated
    public Map<String,Servicos> getServicos(String nomeTabela,int codProd) {
        Map<String,Servicos> lista = new HashMap<String,Servicos>();

        sql = "select * from "+nomeTabela+" where codProd = ?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1,codProd);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Erro ao limpar servicos :"+e);
        }

        try {
           ClienteDAO dao = new ClienteDAO();

            while (rs.next()) {

                Cliente cliente=dao.getCliente(rs.getInt("id_cliente"));

                switch (codProd) {

                    case 1: {
                        if (rs.getString("cpf")!=null) {

                            lista.put(""+rs.getInt("id"),new CartaoDebito(cliente,rs.getInt("id"),rs.getInt("numero_debito"),rs.getDouble("limite_debito")));
                        }else{
                            lista.put(""+rs.getInt("id"),new CartaoDebito(cliente,rs.getInt("id"),rs.getInt("numero_debito"),rs.getDouble("limite_debito")));
                        }
                        break;
                    }
                    case 2:{

                        if (rs.getString("cpf")!=null) {

                            lista.put(""+rs.getInt("id"),new ContaCorrente(cliente,rs.getInt("id"),rs.getInt("numero"),rs.getDouble("saldo"),rs.getDate("data"),rs.getDouble("juros")));
                        }else{
                            lista.put(""+rs.getInt("id"),new ContaCorrente(cliente,rs.getInt("id"),rs.getInt("numero"),rs.getDouble("saldo"),rs.getDate("data"),rs.getDouble("juros")));
                        }

                        break;
                    }

                }

            }

        }catch (SQLException e){
            System.out.println("Erro ao limpar servicos :"+e);
        }

        return lista;
    }

    public int getIdCliente(String cpf){

        sql = "select id_cliente from Clientes where cpf = ?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setString(1,cpf);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar id do cliente: "+e);
        }

        try {

            while (rs.next()){
                return rs.getInt("id_cliente");
            }

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar id do cliente: "+e);
        }

        return 0;
    }

}
