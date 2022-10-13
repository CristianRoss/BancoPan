package br.fiap.Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdicionarServicosServlet
 */
@WebServlet("/adicionarServicos")
public class AdicionarServicosServlet extends HttpServlet {
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
		String resp = request.getParameter("Ben");

		String url = "./pages/error.jsp";
		
		switch (resp) {
		
		case "Cartao Debito":
			url = "./pages/cartaoDebito.jsp";
			break;

		case "Cartao Credito":

			break;

		case "Poupanca":

			break;

		case "Emprestimo":

			break;

		case "Financiamento":

			break;

		case "Maquininha":

			break;

		case "PIX":

			break;

		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
