package br.fiap.DAO.Servicos.Contas;

import br.fiap.Cliente.Cliente;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.Servicos.Contas.ContaCorrente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaCorrenteDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public ContaCorrente pesquisar(int id){
    	
    	 connection=new Conexao().conectar();

        sql="select * from conta_corrente where id_conta_corrente=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();


        }catch (SQLException e){
            System.out.println("Falha ao pesquisar Conta Corrente: "+e);
        }

        try {

           while (rs.next())  {
               Cliente cliente=new ClienteDAO().getCliente(rs.getInt("id_cliente"));
               ContaCorrente conta = new ContaCorrente(cliente,id, rs.getInt("numero"), rs.getDouble("saldo"),
                       rs.getDate("data_criacao"),rs.getDouble("juros"),rs.getString("chave_pix"));
               
               try {
                   connection.close();
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
               
               return conta;

           }

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar Conta Corrente: "+e);
        }

        
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    public ContaCorrente pesquisarConta(int numero){
    	
    	 connection=new Conexao().conectar();

        sql="select * from conta_corrente where numero=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, numero);
            rs=ps.executeQuery();


        }catch (SQLException e){
            System.out.println("Falha ao pesquisar Conta Corrente: "+e);
        }

        try {

            while (rs.next())  {
                Cliente cliente=new ClienteDAO().getCliente(rs.getInt("id_cliente"));
                ContaCorrente conta=new ContaCorrente(cliente,rs.getInt("id_conta_corrente"), rs.getInt("numero"), rs.getDouble("saldo"),
                        rs.getDate("data_criacao"),rs.getDouble("juros"),rs.getString("chave_pix"));
                
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return conta;

            }

        }catch (SQLException e){
            System.out.println("Falha ao pesquisar Conta Corrente: "+e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    public ContaCorrente getContaporCliente(int id) {
    	
    	connection=new Conexao().conectar();
    	
    	 sql= "select id_conta_corrente from documento_conta where id_cliente=?";
    	 
    	 try {

             ps=connection.prepareStatement(sql);
             ps.setInt(1 , id);
             rs=ps.executeQuery();

         }catch (SQLException e){
             System.out.println("falha ao pegar conta corrente: "+e);
         }
    	 
    	 try {
			while (rs.next()) {
				 ContaCorrente conta=pesquisar(rs.getInt("id_conta_corrente"));
				 
				 try {
			            connection.close();
			        } catch (SQLException e) {
			            throw new RuntimeException(e);
			        }
				 
				 return conta;
			 }
		} catch (SQLException e) {
			System.out.println("falha ao pegar conta corrente: "+e);
		}
    	
    	 try {
             connection.close();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    	 
    	return null;
    }

}
