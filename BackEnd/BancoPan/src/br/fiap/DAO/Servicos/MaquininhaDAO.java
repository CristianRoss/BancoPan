package br.fiap.DAO.Servicos;

import br.fiap.Cliente.Cliente;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.Servicos.Maquininha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MaquininhaDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql


    public MaquininhaDAO(){
        connection=new Conexao().conectar();
    }

    public void inserir(Maquininha m) {

        sql="insert into Maquininha values(seq_maquininha.nextval,?,?,?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setDouble(1, m.getTaxa());
            ps.setInt(2, m.getVendas());
            ps.setInt(3, m.getVendasPorMes());
            ps.setInt(4, m.getCliente().getIdCliente());
            ps.execute();

        }catch (SQLException e){
            System.out.println("falha ao inserir maquininha: "+e);
        }

    }

    public List<Maquininha> listarMaquininhas(int idCliente) {
        List<Maquininha> lista=new LinkedList<Maquininha>();

        sql="select * from Maquininha where id_cliente=?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs= ps.executeQuery();

        }catch (SQLException e){
            System.out.println("Erro ao listar maquininhas: "+e);
        }

        try {

            while (rs.next()){
                Cliente cliente=new ClienteDAO().getCliente(rs.getInt("id_cliente"));
                lista.add(new Maquininha(cliente, rs.getInt("id_maquininha"), rs.getDouble("taxas"),
                        rs.getInt("vendas"), rs.getInt("vendas_por_mes")));
            }

        }catch (SQLException e){
            System.out.println("Erro ao listar maquininhas: "+e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}
