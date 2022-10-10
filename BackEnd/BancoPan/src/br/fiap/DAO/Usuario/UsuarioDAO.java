package br.fiap.DAO.Usuario;

import br.fiap.Conexao.Conexao;
import br.fiap.Usuario.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private PreparedStatement ps; // configurar o sql que serexecutado
    private Connection connection; // armazena a conexestabelecida com o banco de dados
    private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
    private String sql; // utilizada para montar as instrusql

    public UsuarioDAO(){
        connection=new Conexao().conectar();
    }

    public void inserir(Usuario usuario) {

        sql="insert into usuarios(numero,senha) values (?,?)";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1,usuario.getNumero());
            ps.setString(2,usuario.getSenha());
            ps.execute();

        }catch (SQLException e) {
            System.out.println("falha ao inserir usuario: "+e);
        }

    }

    public Usuario getUsuario(int numeroConta){

        sql="select * from usuarios where numero = ?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1,numeroConta);
            rs= ps.executeQuery();

        }catch (SQLException e) {
            System.out.println("falha ao pesquisar usuario: "+e);
        }

        try {

            while (rs.next()){
                return new Usuario(numeroConta, rs.getString("senha"));
            }

        }catch (SQLException e) {
            System.out.println("falha ao pesquisar usuario: "+e);
        }

        return null;
    }

    public boolean fazerLogin(Usuario usuario) {

        sql="select * from usuarios where numero = ?";

        try {

            ps=connection.prepareStatement(sql);
            ps.setInt(1,usuario.getNumero());
            rs= ps.executeQuery();

        }catch (SQLException e) {
            System.out.println("falha ao pesquisar usuario: "+e);
        }

        try {

            while (rs.next()){
                if (rs.getInt("numero")==usuario.getNumero()) {
                    if (rs.getString("senha").equalsIgnoreCase(usuario.getSenha())) {
                        return true;
                    }
                }
            }

        }catch (SQLException e) {
            System.out.println("falha ao pesquisar usuario: "+e);
        }


        return false;
    }

}
