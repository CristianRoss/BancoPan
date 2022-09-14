package br.fiap.DAO;

import br.fiap.Clientes.TipoConta;
import br.fiap.Conexao.Conexao;
import br.fiap.Servicos.Maquininha;

import java.sql.*;

public class MaquininhaDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public MaquininhaDAO(){
        connection=new Conexao().conectar();
    }

    public void inserirMaquininha(Maquininha m, TipoConta tipo) {

        sql="insert into maquininha values(?,?,?,?,?,?)";

        if (tipo==TipoConta.FISICA) {

         try {

             ps= connection.prepareStatement(sql);
             ps.setInt(1, m.getIdMaquininha());
             ps.setDouble(2, m.getTaxa());
             ps.setInt(3, m.getVendas());
             ps.setInt(4, m.getVendasPorMes());
             ps.setNull(5, Types.INTEGER);
             ps.setInt(6, m.getIdFisica());
             ps.execute();

         }catch (SQLException e) {
             System.out.println("erro ao inserir maquininha: "+e);
         }

        }else{

            try {

                ps= connection.prepareStatement(sql);
                ps.setInt(1, m.getIdMaquininha());
                ps.setDouble(2, m.getTaxa());
                ps.setInt(3, m.getVendas());
                ps.setInt(4, m.getVendasPorMes());
                ps.setInt(5, m.getIdJur());
                ps.setNull(6, Types.INTEGER);
                ps.execute();

            }catch (SQLException e) {
                System.out.println("erro ao inserir maquininha: "+e);
            }

        }

    }

    public Maquininha pesquisar(int idConta, TipoConta tipo) {

        sql="select * from maquininha";

        if (tipo==TipoConta.FISICA) {

            try {

                ps=connection.prepareStatement(sql);
                rs= ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar maquininha: "+e);
            }

            try {

                while (rs.next()) {
                    if (rs.getInt("id_conta_fisica")==idConta) {
                        Maquininha m=new Maquininha(rs.getInt("id_maquininha"), rs.getDouble("taxa"),
                                rs.getInt("vendas"), rs.getInt("vendas_por_mes"));
                        m.setIdFisica(idConta);
                        return m;
                    }
                }

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar maquininha: "+e);
            }

        }else{

            try {

                ps=connection.prepareStatement(sql);
                rs= ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar maquininha: "+e);
            }

            try {

                while (rs.next()) {
                    if (rs.getInt("id_conta_jur")==idConta) {
                        Maquininha m=new Maquininha(rs.getInt("id_maquininha"), rs.getDouble("taxa"),
                                rs.getInt("vendas"), rs.getInt("vendas_por_mes"));
                        m.setIdJur(idConta);
                        return m;
                    }
                }

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar maquininha: "+e);
            }


        }

        return null;
    }

}
