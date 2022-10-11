package br.fiap.Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.fiap.Cliente.Cliente;
import br.fiap.Cliente.ClienteFisico;
import br.fiap.Cliente.ClienteJuridico;
import br.fiap.DAO.Cliente.ClienteDAO;

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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String ident = request.getParameter("ident");
		String senha = request.getParameter("senha");

		Cliente cliente = new ClienteDAO().getCliente(ident);
		String pageUrl;
		
		//Cliente não encontrado
		if(cliente == null) {
			//TODO: Tratar cliente não encontrado
			System.out.println("Cliente não encontrado");
			return;
		}
		
		if(ident.length() <= 14) {
			cliente = (ClienteFisico) cliente;
			pageUrl = "./pages/clienteFisico.jsp";
			
		}
		else {
			cliente = (ClienteJuridico) cliente;
			pageUrl = "./pages/clienteJuridico.jsp";
		}
		
		//TODO: Validar senha
		
		
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher(pageUrl);
		request.setAttribute("cliente", cliente);
		rd.forward(request, response);
	}

}
