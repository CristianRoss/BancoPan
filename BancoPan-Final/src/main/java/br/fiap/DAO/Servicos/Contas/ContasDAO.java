package br.fiap.DAO.Servicos.Contas;

import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.Servicos.Contas.Conta;
import br.fiap.Servicos.Contas.ContaCorrente;
import br.fiap.Servicos.Contas.ContaPoupanca;

import java.sql.*;
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

          sql="insert into conta_corrente values(seq_conta_corrente.nextval,?,?,?,?,?,?)";

          int numero = new Random().nextInt(9999999);

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, numero);
                ps.setDouble(2, conta.getSaldo());
                ps.setDouble(3, conta.getJuros());
                ps.setDate(4, conta.getDataCriacao());
                ps.setInt(5, conta.getCliente().getIdCliente());
                if (((ContaCorrente) conta).getChavePIX()!=null) {
                    ps.setString(6, ((ContaCorrente) conta).getChavePIX());
                }else{
                    ps.setNull(6, Types.VARCHAR);
                }
                ps.execute();
                conta.setNumero(numero);
                conta.setIdConta(getContaid(conta));
                conta.getCliente().setIdCliente(new ClienteDAO().getID(conta.getCliente()));
                linkarContaAoCliente(conta, conta.getCliente().getIdCliente());

            }catch (SQLException e){
                System.out.println("Erro ao Linkar Contas: "+e);
            }


        }else {


            sql="insert into conta_poupanca values(seq_conta_poupanca.nextval,?,?,?,?,?,?)";


            int numero = new Random().nextInt(9999999);

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1, numero);
                ps.setDouble(2, conta.getSaldo());
                ps.setDouble(3, conta.getJuros());
                ps.setDate(4, conta.getDataCriacao());
                ps.setDate(5, ((ContaPoupanca) conta).getDataAcrescimo());
                ps.setInt(6, conta.getCliente().getIdCliente());
                ps.execute();
                conta.setNumero(numero);
                conta.setIdConta(getContaid(conta));
                conta.getCliente().setIdCliente(new ClienteDAO().getID(conta.getCliente()));
                linkarContaAoCliente(conta, conta.getCliente().getIdCliente());

            }catch (SQLException e){
                System.out.println("Erro ao Linkar Contas: "+e);
            }


        }

    }

    public void linkarContaAoCliente(Conta conta, int idCliente) {

        sql="insert into Documento_Conta values(seq_documento_conta.nextval,?,?,?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, new Random().nextInt(9999999));
            ps.setInt(2, idCliente);
            if (conta instanceof  ContaCorrente) {
                ps.setNull(3, Types.INTEGER);
                ps.setInt(4, conta.getIdConta());
            }else{
                ps.setInt(3, conta.getIdConta());
                ps.setNull(4, Types.INTEGER);
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

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public int getContaid(Conta conta) {

        if (conta instanceof ContaCorrente) {
            sql="select id_conta_corrente from Conta_Corrente where numero = ?";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1,conta.getNumero());
                rs= ps.executeQuery();

            }catch (SQLException e){
                System.out.println("Erro ao pegar id da conta: "+e);
            }

            try {

                while (rs.next()){
                    return rs.getInt("id_conta_corrente");
                }

            }catch (SQLException e){
                System.out.println("Erro ao pegar id da conta: "+e);
            }

        }else{

            sql="select id_poupanca from Conta_Poupanca where numero = ?";

            try {

                ps=connection.prepareStatement(sql);
                ps.setInt(1,conta.getNumero());
                rs= ps.executeQuery();

            }catch (SQLException e){
                System.out.println("Erro ao pegar id da conta: "+e);
            }

            try {

                while (rs.next()){
                    return rs.getInt("id_poupanca");
                }

            }catch (SQLException e){
                System.out.println("Erro ao pegar id da conta: "+e);
            }

        }
        
        return 0;
    }
        
}
