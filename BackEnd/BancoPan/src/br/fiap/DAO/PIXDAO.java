package br.fiap.DAO;

import br.fiap.Clientes.TipoConta;
import br.fiap.Conexao.Conexao;
import br.fiap.Servicos.PIX;

import java.sql.*;

public class PIXDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public PIXDAO(){
        connection=new Conexao().conectar();
    }

    public void inserir(PIX pix, TipoConta tipo) {

        sql="insert into pix values(?,?,?,?,?,?,?,?)";

        if (tipo==TipoConta.FISICA) {

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, pix.getIdPIX());
                ps.setString(2, pix.getChavePix());
                ps.setString(3, pix.getChaveDestino());
                ps.setDouble(4, pix.getLimite());
                ps.setDate(5, pix.getLimiteHorario());
                ps.setDouble(6, pix.getValorPix());
                ps.setNull(7, Types.INTEGER);
                ps.setInt(8, pix.getIdFisica());
                ps.execute();

            }catch (SQLException e){
                System.out.println("Erro ao inserir PIX: "+e);
            }

        }else {

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, pix.getIdPIX());
                ps.setString(2, pix.getChavePix());
                ps.setString(3, pix.getChaveDestino());
                ps.setDouble(4, pix.getLimite());
                ps.setDate(5, pix.getLimiteHorario());
                ps.setDouble(6, pix.getValorPix());
                ps.setInt(7, pix.getIdJur());
                ps.setNull(8, Types.INTEGER);
                ps.execute();

            }catch (SQLException e){
                System.out.println("Erro ao inserir PIX: "+e);
            }

        }

    }

    public PIX pesquisar(int idConta,TipoConta tipo) {

        sql="select * from pix";

        try {

            ps= connection.prepareStatement(sql);
            rs= ps.executeQuery();

        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar PIX: "+e);
        }

        if (tipo==TipoConta.FISICA) {

            try {

                while (rs.next()) {
                    if (rs.getInt("id_conta_fisica")==idConta) {

                        PIX pix=new PIX(rs.getInt("id_pix"),rs.getString("chave_pix"),rs.getString("chave_destino"),
                                rs.getDouble("limite"), rs.getDate("limite_horario"),rs.getDouble("valor_pix"));
                        pix.setIdFisica(idConta);
                        return pix;

                    }
                }

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar PIX: "+e);
            }

        }else {

            try {

                while (rs.next()) {
                    if (rs.getInt("id_conta_jur")==idConta) {

                        PIX pix=new PIX(rs.getInt("id_pix"),rs.getString("chave_pix"),rs.getString("chave_destino"),
                                rs.getDouble("limite"), rs.getDate("limite_horario"),rs.getDouble("valor_pix"));
                        pix.setIdJur(idConta);
                        return pix;

                    }
                }

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar PIX: "+e);
            }


        }

        return null;
    }

}
