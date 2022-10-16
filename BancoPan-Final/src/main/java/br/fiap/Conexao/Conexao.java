package br.fiap.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String caminho = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private String login = "rm87896";
    private String senha = "311002";
    private Connection conexao;  //armazena a conexque serestabelecida com o banco

    public Connection conectar() {
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(caminho, login, senha);
        }
        catch(ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver de conexao\n"+e);
        }
        catch(SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados\n"+e);
        }

        return conexao;
    }
    
    public Connection Desconectar() {
    	try {
			this.conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return conexao;
    }

}
