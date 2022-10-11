package br.fiap.Controle;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.fiap.Cliente.ClienteJuridico;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.DAO.Servicos.Contas.ContasDAO;
import br.fiap.DAO.Usuario.UsuarioDAO;
import br.fiap.Servicos.Contas.ContaCorrente;
import br.fiap.Servicos.Contas.DocumentoConta;
import br.fiap.Usuario.Usuario;
import br.fiap.Util.Util;

/**
 * Servlet implementation class CadastroClienteJuridicoServlet
 */
@WebServlet("/cadastroClienteJuridico")
public class CadastroClienteJuridicoServlet extends HttpServlet {
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
		double jurosConta = 6.17;
		String nome = request.getParameter("nomeCliente");
		String cnpj = request.getParameter("cnpj");
		Long telefone = Long.parseLong(request.getParameter("telefone").replaceAll("[^0-9]", ""));
		String email = request.getParameter("email");
		String cep = request.getParameter("cep");
		String rua = request.getParameter("rua");
		String nRua = request.getParameter("numero_rua");
		String complemento = request.getParameter("complemento");
		String endereco = rua + " - N° " + nRua;
		if (complemento != null && !complemento.equalsIgnoreCase(""))
			endereco += " (" + complemento + ")";
		Date datacriacao = Date.valueOf(new Util().formatarData(request.getParameter("dataNasc")));
		String senha = request.getParameter("senha");

		ClienteJuridico cliente = new ClienteJuridico(0, cnpj, nome, email, endereco, telefone, cep);
		ContaCorrente conta = new ContaCorrente(cliente, 0, 0, 0, Date.valueOf(LocalDate.now()), jurosConta);
		Usuario usuario = new Usuario(conta, senha);
		DocumentoConta docConta = new DocumentoConta(cliente, 0);
		docConta.setContaCorrente(conta);
		//TODO: Erro ao linkar contas (DOCUMENTO_CONTA_CORRENTE_FK) violated - parent key not found

		new ClienteDAO().inserirCliente(cliente);
		new ContasDAO().inserir(conta);
		new UsuarioDAO().inserir(usuario);

		RequestDispatcher rd = request.getRequestDispatcher("./pages/clienteJuridico.jsp");
		request.setAttribute("cliente", cliente);
		rd.forward(request, response);

	}

}
