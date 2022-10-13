package br.fiap.Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.fiap.Cliente.Cliente;
import br.fiap.Cliente.ClienteFisico;
import br.fiap.Cliente.ClienteJuridico;
import br.fiap.DAO.Cliente.ClienteDAO;
import br.fiap.DAO.Usuario.UsuarioDAO;

/**
 * Servlet implementation class LoginUsuarioServlet
 */
@WebServlet("/loginUsuario")
public class LoginUsuarioServlet extends HttpServlet {
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
		String ident = request.getParameter("ident");
		String senha = request.getParameter("senha");

		Cliente cliente;
		String pageUrl;

		if (ident.length() <= 14) {
			cliente = (ClienteFisico) null;
			pageUrl = "./pages/clienteFisico.jsp";

		} else {
			cliente = (ClienteJuridico) null;
			pageUrl = "./pages/clienteJuridico.jsp";
		}

		if (new UsuarioDAO().fazerLogin(ident, senha)) {
			cliente = new ClienteDAO().getCliente(ident);
			
			Cookie c = new Cookie("cliente", ""+cliente.getIdCliente());
			c.setMaxAge(3060*60);
			response.addCookie(c);
			
			RequestDispatcher rd = request.getRequestDispatcher(pageUrl);
			request.setAttribute("cliente", cliente);
			rd.forward(request, response);
			
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("./pages/error.jsp");
		rd.forward(request, response);

	}

}
