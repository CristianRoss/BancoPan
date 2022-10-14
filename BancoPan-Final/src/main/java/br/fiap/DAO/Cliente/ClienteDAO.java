package br.fiap.DAO.Cliente;

import br.fiap.Cliente.Cliente;
import br.fiap.Cliente.ClienteFisico;
import br.fiap.Cliente.ClienteJuridico;
import br.fiap.Conexao.Conexao;
import br.fiap.DAO.Servicos.*;
import br.fiap.DAO.Servicos.Contas.ContasDAO;
import br.fiap.Servicos.*;
import br.fiap.Servicos.Cartoes.Cartao;
import br.fiap.Servicos.Contas.Conta;

import java.sql.*;
import java.util.*;

public class ClienteDAO {

	private PreparedStatement ps; // configurar o sql que serexecutado
	private Connection connection; // armazena a conexestabelecida com o banco de dados
	private ResultSet rs; // armazena o resultada da pesquisa no banco de dados
	private String sql; // utilizada para montar as instrusql

	public boolean inserirCliente(Cliente cliente) {

		connection = new Conexao().conectar();
		
		sql = "insert into clientes values(seq_clientes.nextval,?,?,?,?,?,?,?,?,?,?)";

		try {

			ps = connection.prepareStatement(sql);
			ps.setString(3, cliente.getNome());
			ps.setLong(5, cliente.getTelefone());
			ps.setString(6, cliente.getEmail());
			ps.setString(7, cliente.getEndereco());
			ps.setString(8, cliente.getCEP());
			if (cliente instanceof ClienteFisico) {
				ps.setString(1, ((ClienteFisico) cliente).getCpf());
				ps.setNull(2, Types.VARCHAR);
				ps.setString(4, ((ClienteFisico) cliente).getSobrenome());
				ps.setString(9, ((ClienteFisico) cliente).getSexo());
				ps.setDate(10, ((ClienteFisico) cliente).getDataNascimento());
			} else {
				ps.setNull(1, Types.VARCHAR);
				ps.setString(2, ((ClienteJuridico) cliente).getCnpj());
				ps.setNull(4, Types.VARCHAR);
				ps.setNull(9, Types.VARCHAR);
				ps.setNull(10, Types.DATE);
			}

			ps.execute();

		} catch (SQLException e) {
			System.out.println("Falha ao inserir Cliente: " + e);
			
			try {
	            connection.close();
	        } catch (SQLException e1) {
	            throw new RuntimeException(e1);
	        }
			return false;
		}

		cliente.setIdCliente(getID(cliente));

		if (!Cliente.clientes.containsKey(cliente.getIdCliente())) {
			Cliente.clientes.put(cliente.getIdCliente(), cliente);
		}
		
		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return true;
	}

	public int getID(Cliente cliente) {

		connection = new Conexao().conectar();
		
		if (cliente instanceof ClienteFisico) {

			sql = "select id_cliente from clientes where cpf = ?";

			try {

				ps = connection.prepareStatement(sql);
				ps.setString(1, ((ClienteFisico) cliente).getCpf());
				rs = ps.executeQuery();

			} catch (SQLException e) {
				System.out.println("Falha ao pesquisar id do cliente: " + e);
			}

			try {

				while (rs.next()) {
					return rs.getInt("id_cliente");
				}

			} catch (SQLException e) {
				System.out.println("Falha ao pesquisar id do cliente: " + e);
			}

		} else {

			sql = "select id_cliente from clientes where cnpj = ?";

			try {

				ps = connection.prepareStatement(sql);
				ps.setString(1, ((ClienteJuridico) cliente).getCnpj());
				rs = ps.executeQuery();

			} catch (SQLException e) {
				System.out.println("Falha ao pesquisar id do cliente: " + e);
			}

			try {

				while (rs.next()) {
					return rs.getInt("id_cliente");
				}

			} catch (SQLException e) {
				System.out.println("Falha ao pesquisar id do cliente: " + e);
			}

		}
		
		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

		return 0;
	}

	public Cliente getCliente(String identificacao) {

		connection = new Conexao().conectar();

		if (identificacao.length() > 15) {
			sql = "select * from Clientes where cnpj = ?";

			try {

				ps = connection.prepareStatement(sql);
				ps.setString(1, identificacao);
				rs = ps.executeQuery();

			} catch (SQLException e) {
				System.out.println("Erro ao pesquisar cliente por cnpj: " + e);
			}

			try {

				while (rs.next()) {
					ClienteJuridico cliente = new ClienteJuridico(rs.getInt("id_cliente"), rs.getString("cnpj"),
							rs.getString("nome"), rs.getString("email"), rs.getString("endereco"),
							rs.getLong("telefone"), rs.getString("cep"));
					Cliente.clientes.put(cliente.getIdCliente(), cliente);
					
					try {
			            connection.close();
			        } catch (SQLException e) {
			            throw new RuntimeException(e);
			        }
					
					return cliente;
				}

			} catch (SQLException e) {
				System.out.println("Erro ao pesquisar cliente por cnpj: " + e);
			}

		} else {
			sql = "select * from Clientes where cpf = ?";

			try {

				ps = connection.prepareStatement(sql);
				ps.setString(1, identificacao);
				rs = ps.executeQuery();

			} catch (SQLException e) {
				System.out.println("Erro ao pesquisar cliente por cpf: " + e);
			}

			try {

				while (rs.next()) {
					ClienteFisico cliente = new ClienteFisico(rs.getInt("id_cliente"), rs.getString("nome"),
							rs.getString("email"), rs.getString("endereco"), rs.getLong("telefone"),
							rs.getString("cep"), rs.getString("cpf"), rs.getString("sobrenome"),
							rs.getDate("data_nascimento"), rs.getString("sexo"));
					Cliente.clientes.put(cliente.getIdCliente(), cliente);
					
					try {
			            connection.close();
			        } catch (SQLException e) {
			            throw new RuntimeException(e);
			        }
					
					return cliente;
				}

			} catch (SQLException e) {
				System.out.println("Erro ao pesquisar cliente por cpf: " + e);
			}
		}
		
		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

		return null;
	}

	public Cliente getCliente(int id) {

		connection = new Conexao().conectar();
		
		if (!Cliente.clientes.containsKey(id)) {
			sql = "select * from Clientes where id_cliente = ?";

			try {

				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();

			} catch (SQLException e) {
				System.out.println("Falha ao pesquisar Cliente: " + e);
			}

			try {

				while (rs.next()) {
					if (rs.getString("cpf") != null) {
						ClienteFisico cliente = new ClienteFisico(id, rs.getString("nome"), rs.getString("email"),
								rs.getString("endereco"), rs.getLong("telefone"), rs.getString("cep"),
								rs.getString("cpf"), rs.getString("sobrenome"), rs.getDate("data_nascimento"),
								rs.getString("sexo"));
						Cliente.clientes.put(cliente.getIdCliente(), cliente);
						
						try {
				            connection.close();
				        } catch (SQLException e) {
				            throw new RuntimeException(e);
				        }
						
						return cliente;
					} else {
						ClienteJuridico cliente = new ClienteJuridico(id, rs.getString("cnpj"), rs.getString("nome"),
								rs.getString("email"), rs.getString("endereco"), rs.getLong("telefone"),
								rs.getString("cep"));
						Cliente.clientes.put(cliente.getIdCliente(), cliente);
						
						try {
				            connection.close();
				        } catch (SQLException e) {
				            throw new RuntimeException(e);
				        }
						
						return cliente;
					}

				}

			} catch (SQLException e) {
				System.out.println("Falha ao pesquisar Cliente: " + e);
			}
		} else {
			
			try {
	            connection.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
			
			return Cliente.clientes.get(id);
		}
		
		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

		return null;
	}

	public void listarClientes() {
		
		connection = new Conexao().conectar();

		sql = "select * from Clientes";

		try {

			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			System.out.println("Falha ao listar clientes: " + e);
		}

		try {

			while (rs.next()) {
				if (rs.getString("cpf") != null) {
					Cliente.clientes.put(rs.getInt("id_cliente"),
							new ClienteFisico(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("email"),
									rs.getString("endereco"), rs.getLong("telefone"), rs.getString("cep"),
									rs.getString("cpf"), rs.getString("sobrenome"), rs.getDate("data_nascimento"),
									rs.getString("sexo")));
				} else {
					Cliente.clientes.put(rs.getInt("id_cliente"),
							new ClienteJuridico(rs.getInt("id_cliente"), rs.getString("cnpj"), rs.getString("nome"),
									rs.getString("email"), rs.getString("endereco"), rs.getLong("telefone"),
									rs.getString("cep")));
				}
			}

		} catch (SQLException e) {
			System.out.println("Falha ao listar clientes: " + e);
		}

		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
	}

	public List<Servicos> listarServicos(Cliente cliente) {

		connection = new Conexao().conectar();
		
		ArrayList<Servicos> lista = new ArrayList<Servicos>();

		List<Conta> contas = new ContasDAO().listarContas(cliente.getIdCliente());
		if (contas != null) {
			for (Conta s : contas) {
				lista.add(s);
			}
		}

		List<Cartao> cartoes = new CartaoDAO().listarCartoes(cliente.getIdCliente());
		if (cartoes != null) {
			for (Cartao c : cartoes) {
				lista.add(c);
			}
		}

		List<PIX> pixs = new PIXDAO().getPIXS(cliente.getIdCliente());
		if (pixs != null) {
			for (PIX p : pixs) {
				lista.add(p);
			}
		}

		List<Emprestimos> emprestimos = new EmprestimosDAO().lisarEmprestimos(cliente.getIdCliente());
		if (emprestimos != null) {
			for (Emprestimos s : emprestimos) {
				lista.add(s);
			}
		}

		List<Maquininha> maquininhas = new MaquininhaDAO().listarMaquininhas(cliente.getIdCliente());
		if (maquininhas != null) {
			for (Maquininha s : maquininhas) {
				lista.add(s);
			}
		}

		List<Financiamentos> financiamentos = new FinanciamentoDAO().listarFinanciamentos(cliente.getIdCliente());
		if (financiamentos != null) {
			for (Financiamentos s : financiamentos) {
				lista.add(s);
			}
		}

		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return lista;
	}

}
