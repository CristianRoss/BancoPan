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
import br.fiap.DAO.Servicos.CartaoDAO;
import br.fiap.Servicos.Cartoes.CartaoDebito;

/**
 * Servlet implementation class CartaoDebitoServlet
 */
@WebServlet("/cartaoDebito")
public class CartaoDebitoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double limite = Double.parseDouble(request.getParameter("limiteDebito"));
		Cookie[] cookies = request.getCookies();
		Cliente cliente = null;
		String pageUrl = null;
		
		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equalsIgnoreCase("cliente")) {
				cliente = Cliente.clientes.get(Integer.parseInt(cookies[i].getValue()));
				break;
			}
		}

		try {
			cliente = (ClienteFisico) cliente;
			pageUrl = "./pages/clienteFisico.jsp";
		} catch (Exception e) {
			cliente = (ClienteJuridico) cliente;
			pageUrl = "./pages/clienteJuridico.jsp";
		}
		
		if(!new CartaoDAO().inserir(new CartaoDebito(cliente, 0, 0, limite))) {
			RequestDispatcher rd = request.getRequestDispatcher("./pages/error.jsp");
			rd.forward(request, response);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(pageUrl);
		request.setAttribute("cliente", cliente);
		rd.forward(request, response);
	}

}
