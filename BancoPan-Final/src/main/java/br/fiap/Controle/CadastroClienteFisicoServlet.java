package br.fiap.Controle;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.fiap.Cliente.Cliente;
import br.fiap.Cliente.ClienteFisico;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.DAO.Servicos.Contas.ContaCorrenteDAO;
import br.fiap.DAO.Servicos.Contas.ContasDAO;
import br.fiap.DAO.Usuario.UsuarioDAO;
import br.fiap.Servicos.Contas.ContaCorrente;
import br.fiap.Usuario.Usuario;
import br.fiap.Util.Util;

import java.sql.Date;

/**
 * Servlet implementation class cadastroClienteFisicoServlet
 */
@WebServlet("/cadastroClienteFisico")
public class CadastroClienteFisicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nomeCliente");
		String sobrenome = request.getParameter("sobrenomeCliente");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String sexo = request.getParameter("sexo");
		Date dataNasc = Date.valueOf(new Util().formatarData(request.getParameter("dataNasc")));
		Long telefone = Long.parseLong(request.getParameter("telefone").replaceAll("[^0-9]", ""));
		String cep = request.getParameter("cep");
		String rua = request.getParameter("rua");
		String nRua = request.getParameter("numero_rua");
		String complemento = request.getParameter("complemento");
		String endereco = rua + " - N° " + nRua;
		if (complemento != null)
			endereco += " (" + complemento + ")";

		String senha = request.getParameter("senha");

		ClienteFisico cliente = new ClienteFisico(0, nome, email, endereco, telefone, cep, cpf, sobrenome, dataNasc,
				sexo);										//Data != null
		ContaCorrente conta = new ContaCorrente(cliente, 0, 0, 0, null, 6.17);
		Usuario usuario = new Usuario(conta, senha);
		
		System.out.println(cliente);

		new ClienteDAO().inserirCliente(cliente);
		new ContasDAO().inserir(conta);
		new UsuarioDAO().inserir(usuario);

	}

}
