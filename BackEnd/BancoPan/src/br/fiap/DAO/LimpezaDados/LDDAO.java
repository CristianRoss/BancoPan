package br.fiap.DAO.LimpezaDados;

import br.fiap.Cliente.Cliente;
import br.fiap.Cliente.ClienteFisico;
import br.fiap.Cliente.ClienteJuridico;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.DAO.Servicos.CartaoDAO;
import br.fiap.DAO.Servicos.Contas.ContaCorrenteDAO;
import br.fiap.DAO.Servicos.Contas.ContasDAO;
import br.fiap.Servicos.Cartoes.Cartao;
import br.fiap.Servicos.Cartoes.CartaoDebito;
import br.fiap.Servicos.Contas.Conta;
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
        }

        try {

            while (rs.next()){
                if (rs.getString("cpf")!=null) {
                    lista.put(rs.getString("cpf"), new ClienteFisico(cont, rs.getString("nome").toUpperCase(),rs.getString("email"),rs.getString("endereco").toUpperCase(), rs.getInt("telefone"),rs.getString("cep"), rs.getString("cpf"), rs.getString("sobrenome").toUpperCase(),rs.getDate("aniversario"),rs.getString("sexo").toUpperCase()));
                }else {
                    lista.put(rs.getString("cnpj"), new ClienteJuridico(cont, rs.getString("cnpj"), rs.getString("nome").toUpperCase(), rs.getString("email"), rs.getString("endereco").toUpperCase(), rs.getInt("telefone"),rs.getString("cep")));
                }
            }

        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar tabela suja : "+e);
        }


        return lista;
    }

    public void inserirNovosClientes(Map<String, Cliente> lista){
        ClienteDAO dao=new ClienteDAO();
        for (Map.Entry<String,Cliente> clientes:lista.entrySet()) {
             dao.inserirCliente(clientes.getValue());
        }
    }


    public Map<String,Servicos> getServicos(String nomeTabela,int codProd,String tabelaServ) {
        Map<String,Servicos> lista = new HashMap<String,Servicos>();



        try {

            sql="select DISTINCT * from "+tabelaServ+" p inner join "+nomeTabela+" t on p.cod_prod = t.cod_prod where p.cod_prod in (select distinct cod_prod from "+tabelaServ+")";
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Erro ao limpar servicos :"+e);
        }

        try {

            while (rs.next()) {
                ClienteDAO dao = new ClienteDAO();

                switch (codProd) {


                    case 1: {
                        if (rs.getString("cpf")!=null) {
                            Cliente cliente=dao.getCliente(rs.getString("cpf"));
                            if (rs.getInt("numero_debito")!=0) {
                                lista.put(rs.getString("cpf"),new CartaoDebito(cliente,rs.getInt("cod_prod"),rs.getInt("numero_debito"),rs.getDouble("limite_debito")));
                            }
                        }else{
                            Cliente cliente=dao.getCliente(rs.getString("cnpj"));
                            if (rs.getInt("numero_debito")!=0) {
                                lista.put(""+rs.getInt("numero_debito"),new CartaoDebito(cliente,rs.getInt("cod_prod"),rs.getInt("numero_debito"),rs.getDouble("limite_debito")));
                            }
                        }
                        break;
                    }
                    case 2:{

                        if (rs.getString("cpf")!=null) {
                            Cliente cliente=dao.getCliente(rs.getString("cpf"));

                            if (rs.getInt("numero_conta")!=0) {
                                lista.put(rs.getString("cpf"),new ContaCorrente(cliente,rs.getInt("cod_prod"),rs.getInt("numero_conta"),rs.getDouble("saldo"),rs.getDate("data_conta"),rs.getDouble("juros")));
                            }

                        }else{
                            Cliente cliente=dao.getCliente(rs.getString("cnpj"));
                            lista.put(""+rs.getInt("numero_conta"),new ContaCorrente(cliente,rs.getInt("cod_prod"),rs.getInt("numero_conta"),rs.getDouble("saldo"),rs.getDate("data_conta"),rs.getDouble("juros")));
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

    public void inserirNovosServicos(Map<String,Servicos> lista) {
        if (lista!=null) {
            CartaoDAO cartaoDAO=new CartaoDAO();
            ContasDAO contasDAO=new ContasDAO();
            for (Map.Entry<String,Servicos> servicos:lista.entrySet()) {
                if (servicos instanceof CartaoDebito) {
                    cartaoDAO.inserir((Cartao) servicos);
                } else if (servicos instanceof ContaCorrente) {
                    contasDAO.inserir((Conta) servicos);
                }
            }
        }else {
            System.out.println("Lista de Servicos Vazia");
        }
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
