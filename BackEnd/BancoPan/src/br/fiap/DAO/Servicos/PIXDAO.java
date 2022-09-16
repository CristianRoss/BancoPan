package br.fiap.DAO.Servicos;

import br.fiap.Cliente.Cliente;
import br.fiap.Conexao.Conexao;
import br.fiap.Servicos.PIX;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PIXDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public PIXDAO(){
        connection=new Conexao().conectar();
    }

    public void inserirPIX(PIX pix) {
        sql="insert into PIX values(?,?,?,?,?,?,?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, pix.getIdPIX());
            ps.setString(2, pix.getChaveConta());
            ps.setString(3, pix.getChaveDestino());
            ps.setDouble(4, pix.getValorPix());
            ps.setDate(5, pix.getData());
            ps.setDouble(6, pix.getLimiteValor());
            if (pix.getLimiteHorario()==null) {
                ps.setNull(7, Types.DATE);
            }else{
                ps.setDate(7, pix.getLimiteHorario());
            }
            ps.setInt(8, pix.getIdCliente());
            ps.execute();

        }catch (SQLException e){
            System.out.println("Falha ao inserir cliente: "+e);
        }

    }
    public PIX getPIX(int id){

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
                return new PIX(rs.getInt("id_cliente"),id,rs.getString("chave_conta"),
                        rs.getString("chave_destino"),rs.getDouble("limite_valor"),
                        rs.getDate("limite_horario"), rs.getDouble("valor"),rs.getDate("data"));
            }


        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar PIX: "+e);
        }

        return null;
    }

    public List<PIX> getPIXS(int idCliente) {
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
                lista.add(new PIX(rs.getInt("id_cliente"), rs.getInt("id_pix"), rs.getString("chave_conta"),
                        rs.getString("chave_destino"),rs.getDouble("limite_valor"),
                        rs.getDate("limite_horario"), rs.getDouble("valor"),rs.getDate("data")));
            }

        }catch (SQLException e){
            System.out.println("Falha ao listar PIXS: "+e);
        }

        return lista;
    }

}
