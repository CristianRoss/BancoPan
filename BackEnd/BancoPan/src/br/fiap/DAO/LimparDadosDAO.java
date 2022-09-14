package br.fiap.DAO;

import br.fiap.Conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class LimparDadosDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public LimparDadosDAO(){
        connection=new Conexao().conectar();
    }

    public List<String> pegarDados() {

        sql = "select * from sql_teste";

        try {
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar tabela : "+e);
        }

        try {
            List<String> lista=new LinkedList<String>();
            while (rs.next()) {
                lista.add(""+rs.getInt("id_teste")+" "+rs.getInt("numero")+" "+rs.getString("nome"));
            }
            return lista;
        }catch (SQLException e) {
            System.out.println("Erro ao pesquisar tabela : "+e);
        }

        return null;

    }


}
