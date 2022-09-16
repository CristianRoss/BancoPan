package br.fiap.DAO.Servicos.Contas;

import br.fiap.Conexao.Conexao;
import br.fiap.Servicos.Contas.Conta;
import br.fiap.Servicos.Contas.ContaCorrente;
import br.fiap.Servicos.Contas.ContaPoupanca;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ContasDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public ContasDAO(){
        connection=new Conexao().conectar();
    }


    public void inserir(Conta conta) {

        if (conta instanceof ContaCorrente) {

          sql="insert into conta_corrente values(?,?,?,?,?,?,?)";


            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, conta.getIdConta());
                ps.setInt(2, conta.getNumero());
                ps.setDouble(3, conta.getSaldo());
                ps.setDouble(4, conta.getJuros());
                ps.setDate(5, conta.getDataCriacao());
                ps.setInt(6, conta.getIdCliente());
                if (((ContaCorrente) conta).getChavePIX()!=null) {
                    ps.setString(7, ((ContaCorrente) conta).getChavePIX());
                }else{
                    ps.setNull(7, Types.VARCHAR);
                }
                ps.execute();
                linkarContaAoCliente(conta, conta.getIdCliente());

            }catch (SQLException e){
                System.out.println("Erro ao Linkar Contas: "+e);
            }

        }else {


            sql="insert into conta_poupanca values(?,?,?,?,?,?,?)";


            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, conta.getIdConta());
                ps.setInt(2, conta.getNumero());
                ps.setDouble(3, conta.getSaldo());
                ps.setDouble(4, conta.getJuros());
                ps.setDate(5, conta.getDataCriacao());
                ps.setDate(6, ((ContaPoupanca) conta).getDataAcrescimo());
                ps.setInt(7, conta.getIdCliente());
                ps.execute();
                linkarContaAoCliente(conta, conta.getIdCliente());

            }catch (SQLException e){
                System.out.println("Erro ao Linkar Contas: "+e);
            }

        }

    }

    public void linkarContaAoCliente(Conta conta, int idCliente) {

        sql="insert into Documento_Conta values(?,?,?,?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idCliente+""+conta.getIdConta()));
            ps.setInt(2, new Random().nextInt(9999999));
            ps.setInt(3, idCliente);
            if (conta instanceof  ContaCorrente) {
                ps.setNull(4, Types.INTEGER);
                ps.setInt(5, conta.getIdConta());
            }else{
                ps.setInt(4, conta.getIdConta());
                ps.setNull(5, Types.INTEGER);
            }
            ps.execute();

        }catch (SQLException e){
            System.out.println("Erro ao Linkar Contas: "+e);
        }

    }

    public List<Conta> listarContas(int idCliente) {
        List<Conta> lista=new LinkedList<Conta>();

        sql= "select id_poupanca,id_conta_corrente from documento_conta where id_cliente=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1 , idCliente);
            rs=ps.executeQuery();

        }catch (SQLException e){
            System.out.println("falha ao listar contas: "+e);
        }

        try {

            while (rs.next()){
                if (rs.getInt("id_poupanca")!=0) {
                    lista.add(new ContaPoupancaDAO().pesquisar(rs.getInt("id_poupanca")));
                }else if (rs.getInt("id_conta_corrente")!=0) {
                    lista.add(new ContaCorrenteDAO().pesquisar(rs.getInt("id_conta_corrente")));
                }
            }

        }catch (SQLException e){
            System.out.println("falha ao listar contas: "+e);
        }


        return lista;
    }

}
