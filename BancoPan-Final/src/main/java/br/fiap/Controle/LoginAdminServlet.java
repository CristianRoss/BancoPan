package br.fiap.Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/loginAdmin")
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("loginAdm");
		String senha = request.getParameter("adminSenha");
		
		if(!login.equals("Admin")) {
			//TODO: Tratar login adm errado
			return;
		}
		
		if(!senha.equals("admin")) {
			//Todo: Tratar senha adm errada
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("./pages/consultaCliente.jsp");
		rd.forward(request, response);
	}

}
