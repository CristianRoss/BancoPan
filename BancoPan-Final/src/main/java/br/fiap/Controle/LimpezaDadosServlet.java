package br.fiap.Controle;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.fiap.Cliente.Cliente;
import br.fiap.DAO.LimpezaDados.LDDAO;

/**
 * Servlet implementation class LimpezaDadosServlet
 */
@WebServlet("/limpezaDados")
public class LimpezaDadosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String table = request.getParameter("tabela");
		LDDAO limpeza = new LDDAO();
		
		Map<String, Cliente> clientes = limpeza.getClientes(table);
		
		if(clientes.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("./pages/error.jsp");
			rd.forward(request, response);
		}
		
		limpeza.inserirNovosClientes(clientes);
		
		RequestDispatcher rd = request.getRequestDispatcher("./pages/sucessoLimpeza.jsp");
		rd.forward(request, response);
		
	}

}
