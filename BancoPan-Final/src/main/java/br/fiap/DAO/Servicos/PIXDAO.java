package br.fiap.DAO.Servicos;

import br.fiap.Cliente.Cliente;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.Servicos.PIX;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PIXDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public void inserirPIX(PIX pix) {
    	
    	connection=new Conexao().conectar();
    	
        sql="insert into PIX values(seq_pix.nextval,?,?,?,?,?,?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setString(1, pix.getChaveConta());
            ps.setString(2, pix.getChaveDestino());
            ps.setDouble(3, pix.getValorPix());
            ps.setDate(4, pix.getData());
            ps.setDouble(5, pix.getLimiteValor());
            if (pix.getLimiteHorario()==null) {
                ps.setNull(6, Types.DATE);
            }else{
                ps.setDate(6, pix.getLimiteHorario());
            }
            ps.setInt(7, pix.getCliente().getIdCliente());
            ps.execute();

        }catch (SQLException e){
            System.out.println("Falha ao inserir pix: "+e);
        }
        
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
    
    public PIX getPIX(int id){
    	
    	connection=new Conexao().conectar();

        sql="select * from PIX where id_pix=?";

        try {

            ps= connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();

        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar PIX: "+e);
        }

        try {

            if (!rs.wasNull()) {
                Cliente cliente=new ClienteDAO().getCliente(rs.getInt("id_cliente"));
                PIX pix=new PIX(cliente,id,rs.getString("chave_conta"),
                        rs.getString("chave_destino"),rs.getDouble("limite_valor"),
                        rs.getDate("limite_horario"), rs.getDouble("valor"),rs.getDate("data"));
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                
                return pix;
                
            }


        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar PIX: "+e);
        }
        
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<PIX> getPIXS(int idCliente) {
    	connection=new Conexao().conectar();
        List<PIX> lista = new LinkedList<PIX>();

        sql="select * from PIX where id_cliente=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Falha ao listar PIXS: "+e);
        }

        try {

            while (rs.next()) {
                Cliente cliente=new ClienteDAO().getCliente(rs.getInt("id_cliente"));
                lista.add(new PIX(cliente, rs.getInt("id_pix"), rs.getString("chave_conta"),
                        rs.getString("chave_destino"),rs.getDouble("limite_valor"),
                        rs.getDate("limite_horario"), rs.getDouble("valor"),rs.getDate("data")));
            }

        }catch (SQLException e){
            System.out.println("Falha ao listar PIXS: "+e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
    
    

}
