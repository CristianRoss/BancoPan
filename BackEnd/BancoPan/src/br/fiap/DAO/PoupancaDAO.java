package br.fiap.DAO;

import br.fiap.Clientes.TipoConta;
import br.fiap.Conexao.Conexao;
import br.fiap.Servicos.Poupanca;

import java.sql.*;

public class PoupancaDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public PoupancaDAO(){
        connection=new Conexao().conectar();
    }

    public void inserir(Poupanca p, TipoConta tipo) {

        sql="insert into poupanca values(?,?,?,?,?,?,?)";

        if (tipo==TipoConta.FISICA) {

            try {

                ps= connection.prepareStatement(sql);
                ps.setInt(1, p.getIdPoupanca());
                ps.setDouble(2, p.getSaldo());
                ps.setDouble(3, p.getJuros());
                ps.setDate(4, p.getDataCriacao());
                ps.setDate(5, p.getDataAcrescimo());
                ps.setNull(6, Types.INTEGER);
                ps.setInt(7, p.getIdFisica());
                ps.execute();

            }catch (SQLException e){
                System.out.println("Erro ao inserir Poupanca: "+e);
            }

        }else{

            try {

                ps= connection.prepareStatement(sql);
                ps.setInt(1, p.getIdPoupanca());
                ps.setDouble(2, p.getSaldo());
                ps.setDouble(3, p.getJuros());
                ps.setDate(4, p.getDataCriacao());
                ps.setDate(5, p.getDataAcrescimo());
                ps.setInt(6, p.getIdJur());
                ps.setNull(7, Types.INTEGER);
                ps.execute();

            }catch (SQLException e){
                System.out.println("Erro ao inserir Poupanca: "+e);
            }

        }

    }

    public Poupanca pesquisar(int idConta,TipoConta tipo) {

        if (tipo==TipoConta.FISICA) {

            sql = "select * from poupanca where id_conta_fisica = ?";

            try {

                ps= connection.prepareStatement(sql);
                ps.setInt(1, idConta);
                rs=ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar poupanca: "+e);
            }

            try {

                while (rs.next()) {
                    Poupanca p=new Poupanca(rs.getInt("id_poupanca"),
                            rs.getDouble("saldo_poupanca"),rs.getDouble("juros_poupanca"),rs.getDate("data_criacao"),
                            rs.getDate("data_acrescimo"));
                    p.setIdFisica(idConta);
                    return p;
                }

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar poupanca: "+e);
            }

        }else{

            sql = "select * from poupanca where id_conta_jur = ?";

            try {

                ps= connection.prepareStatement(sql);
                ps.setInt(1, idConta);
                rs=ps.executeQuery();

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar poupanca: "+e);
            }

            try {

                while (rs.next()) {
                    Poupanca p=new Poupanca(rs.getInt("id_poupanca"),
                            rs.getDouble("saldo_poupanca"),rs.getDouble("juros_poupanca"),rs.getDate("data_criacao"),
                            rs.getDate("data_acrescimo"));
                    p.setIdJur(idConta);
                    return p;
                }

            }catch (SQLException e) {
                System.out.println("Erro ao pesquisar poupanca: "+e);
            }

        }

        return null;
    }


}
