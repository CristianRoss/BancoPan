package br.fiap.DAO.Usuario;

import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Servicos.Contas.ContaCorrenteDAO;
import br.fiap.Usuario.Usuario;
import br.fiap.Util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	private PreparedStatement ps; // configurar o sql que serexecutado
	private Connection connection; // armazena a conexestabelecida com o banco de dados
	private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
	private String sql; // utilizada para montar as instrusql

	public UsuarioDAO() {
		connection = new Conexao().conectar();
	}

	public void inserir(Usuario usuario) {

		sql = "insert into usuarios(numero,senha) values (?,?)";

		try {

			ps = connection.prepareStatement(sql);
			ps.setInt(1, usuario.getConta().getNumero());
			ps.setString(2, usuario.getSenha());
			ps.execute();

		} catch (SQLException e) {
			System.out.println("falha ao inserir usuario: " + e);
		}

	}

	public Usuario getUsuario(int numeroConta) {

		sql = "select * from usuarios where numero = ?";

		try {

			ps = connection.prepareStatement(sql);
			ps.setInt(1, numeroConta);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			System.out.println("falha ao pesquisar usuario: " + e);
		}

		try {

			ContaCorrenteDAO dao = new ContaCorrenteDAO();

			while (rs.next()) {
				return new Usuario(dao.pesquisarConta(numeroConta), rs.getString("senha"));
			}

		} catch (SQLException e) {
			System.out.println("falha ao pesquisar usuario: " + e);
		}

		return null;
	}

	public boolean fazerLogin(Usuario usuario) {

		sql = "select * from usuarios where numero = ?";

		try {

			ps = connection.prepareStatement(sql);
			ps.setInt(1, usuario.getConta().getNumero());
			rs = ps.executeQuery();

		} catch (SQLException e) {
			System.out.println("falha ao pesquisar usuario: " + e);
		}

		try {

			while (rs.next()) {
				if (rs.getInt("numero") == usuario.getConta().getNumero()) {
					if (rs.getString("senha").equals(usuario.getSenha())) {
						try {
				            connection.close();
				        } catch (SQLException e) {
				            throw new RuntimeException(e);
				        }
						return true;
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("falha ao pesquisar usuario: " + e);
		}

		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
		return false;
	}

	public boolean fazerLogin(String ident, String senha) {

		String tipoConta;
		if (ident.length() <= 14) {			
			sql = "select cpf, senha from usuarios u join conta_corrente cc on (u.numero = cc.numero) join clientes c "
					+ "on (cc.id_cliente = c.Id_cliente) where cpf = ?";
			tipoConta = "cpf";
		}
		else {
			sql = "select cnpj, senha from usuarios u join conta_corrente cc on (u.numero = cc.numero) join clientes c "
					+ "on (cc.id_cliente = c.Id_cliente) where cnpj = ?";
			tipoConta = "cnpj";
		}

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, ident);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getString(tipoConta).equals(ident)) {
					if (rs.getString("senha").equals(new Util().criptografar(senha))) {
						
						try {
				            connection.close();
				        } catch (SQLException e) {
				            throw new RuntimeException(e);
				        }
						
						return true;
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("Falha ao logar cliente: " + e);
		}

		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
		return false;
	}

}
