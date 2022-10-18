package br.fiap.DAO.LimpezaDados;

import br.fiap.Cliente.Cliente;
import br.fiap.Cliente.ClienteFisico;
import br.fiap.Cliente.ClienteJuridico;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.DAO.Servicos.CartaoDAO;
import br.fiap.DAO.Servicos.Contas.ContasDAO;
import br.fiap.Servicos.Cartoes.CartaoDebito;
import br.fiap.Servicos.Contas.ContaCorrente;
import br.fiap.Util.Util;
import br.fiap.Servicos.Servicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LDDAO {

	private PreparedStatement ps; // configurar o sql que serexecutado
	private Connection connection; // armazena a conexestabelecida com o banco de dados
	private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
	private String sql; // utilizada para montar as instrusql

	private int cont;

	public Map<String, Cliente> getClientes(String nomeTabela) {
		
		connection = new Conexao().conectar();
		
		Map<String, Cliente> lista = new HashMap<String, Cliente>();

		sql = "select * from " + nomeTabela;

		try {

			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar tabela suja : " + e);
		}

		try {

			while (rs.next()) {
				if (rs.getString("cpf") != null) {
					if (Util.isCPF(rs.getString("cpf")))
					lista.put(rs.getString("cpf"),
							new ClienteFisico(cont, rs.getString("nome").toUpperCase(), rs.getString("email"),
									rs.getString("endereco").toUpperCase(), rs.getLong("telefone"), rs.getString("cep"),
									rs.getString("cpf"), rs.getString("sobrenome").toUpperCase(),
									rs.getDate("aniversario"), rs.getString("sexo").toUpperCase()));
					
				} else {
					if (Util.isCNPJ(rs.getString("cnpj"))) {
						lista.put(rs.getString("cnpj"),
								new ClienteJuridico(cont, rs.getString("cnpj"), rs.getString("nome").toUpperCase(),
										rs.getString("email"), rs.getString("endereco").toUpperCase(),
										rs.getLong("telefone"), rs.getString("cep")));
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar tabela suja : " + e);
		}
		
		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

		return lista;
	}

	public void inserirNovosClientes(Map<String, Cliente> lista) {
		ClienteDAO dao = new ClienteDAO();
		for (Map.Entry<String, Cliente> clientes : lista.entrySet()) {
			dao.inserirCliente(clientes.getValue());
		}
	}

	public Map<String, Servicos> getServicos(String nomeTabela, int codProd, String tabelaServ) {
		connection = new Conexao().conectar();
		Map<String, Servicos> lista = new HashMap<String, Servicos>();

		try {
			
//			sql = "select * from " + tabelaServ + " p inner join " + nomeTabela
//					+ " t on p.cod_prod = t.cod_prod where p.cod_prod in (select cod_prod from " + tabelaServ
//					+ ")";

			sql = "select distinct numero_debito,limite_debito,numero_conta,saldo,data_conta,juros,cpf,cnpj from "+tabelaServ+" p inner join "+nomeTabela+" t \n"
					+ "on p.cod_prod = t.cod_prod where p.cod_prod in \n"
					+ "(select distinct cod_prod from "+tabelaServ+")";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			System.out.println("Erro ao limpar servicos :" + e);
		}

		try {

			while (rs.next()) {
				ClienteDAO dao = new ClienteDAO();

				switch (codProd) {

				case 1: {
					if (rs.getString("cpf") != null) {
						if (Util.isCPF(rs.getString("cpf"))) {
						Cliente cliente = dao.getCliente(rs.getString("cpf"));
						if (rs.getInt("numero_debito") != 0) {
							lista.put(rs.getString("cpf"), new CartaoDebito(cliente, codProd,
									rs.getInt("numero_debito"), rs.getDouble("limite_debito")));
						  }
						}
					} else {
						if (Util.isCNPJ(rs.getString("cnpj"))) {
							Cliente cliente = dao.getCliente(rs.getString("cnpj"));
							if (rs.getInt("numero_debito") != 0) {
								lista.put(rs.getString("cnpj"), new CartaoDebito(cliente, codProd,
										rs.getInt("numero_debito"), rs.getDouble("limite_debito")));
							}
						}
					}
					break;
				}
				case 2: {

					if (rs.getString("cpf") != null) {
						if (Util.isCPF(rs.getString("cpf"))) {
							Cliente cliente = dao.getCliente(rs.getString("cpf"));
							
							if (cliente!=null) {
								System.out.println(cliente);

								if (rs.getInt("numero_conta") != 0) {
									lista.put(rs.getString("cpf"),
											new ContaCorrente(cliente, codProd, rs.getInt("numero_conta"),
													rs.getDouble("saldo"), rs.getDate("data_conta"), rs.getDouble("juros")));
									System.out.println("\n\n"+rs.getInt("numero_conta"));
								}
							}
						}

					} else {
						if (Util.isCNPJ(rs.getString("cnpj"))) {
							Cliente cliente = dao.getCliente(rs.getString("cnpj"));
							lista.put(rs.getString("cnpj"),
									new ContaCorrente(cliente, codProd, rs.getInt("numero_conta"),
											rs.getDouble("saldo"), rs.getDate("data_conta"), rs.getDouble("juros")));
						}
					}

					break;
				}

				}

			}

		} catch (SQLException e) {
			System.out.println("Erro ao limpar servicos :" + e);
		}
		
		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
		lista.forEach((key,value) -> {
			System.out.println(key);
			System.out.println("\n"+value);
		});

		return lista;
	}

	public void inserirNovosServicos(Map<String, Servicos> lista) {
		if (lista != null) {
			CartaoDAO cartaoDAO = new CartaoDAO();
			ContasDAO contasDAO = new ContasDAO();
			lista.forEach((key, value) -> {
				if (value instanceof CartaoDebito) {
					cartaoDAO.inserir((CartaoDebito) value);
				} else if (value instanceof ContaCorrente) {
					contasDAO.inserir((ContaCorrente) value);
				}
			});
		} else {
			System.out.println("Lista de Servicos Vazia");
		}
	}

	public int getIdCliente(String cpf) {
		
		connection = new Conexao().conectar();

		sql = "select id_cliente from Clientes where cpf = ?";

		try {

			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			System.out.println("Falha ao pesquisar id do cliente: " + e);
		}

		try {

			while (rs.next()) {
				
				int id=rs.getInt("id_cliente");
				
				try {
		            connection.close();
		        } catch (SQLException e) {
		            throw new RuntimeException(e);
		        }
				
				return id;
			}

		} catch (SQLException e) {
			System.out.println("Falha ao pesquisar id do cliente: " + e);
		}
		
		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

		return 0;
	}

}
