package br.fiap.DAO.Servicos;

import br.fiap.Conexao.Conexao;
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

        sql="insert into Maquininha values(?,?,?,?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1, m.getIdMaquininha());
            ps.setDouble(2, m.getTaxa());
            ps.setInt(3, m.getVendas());
            ps.setInt(4, m.getVendasPorMes());
            ps.setInt(5, m.getIdCliente());
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
                lista.add(new Maquininha(idCliente, rs.getInt("id_maquininha"), rs.getDouble("taxas"),
                        rs.getInt("vendas"), rs.getInt("vendas_por_mes")));
            }

        }catch (SQLException e){
            System.out.println("Erro ao listar maquininhas: "+e);
        }

        return lista;
    }

}
