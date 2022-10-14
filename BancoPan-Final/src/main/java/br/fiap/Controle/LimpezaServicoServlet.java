package br.fiap.Controle;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.fiap.DAO.LimpezaDados.LDDAO;
import br.fiap.Servicos.Servicos;

/**
 * Servlet implementation class LimpezaServicoServlet
 */
@WebServlet("/limpezaServico")
public class LimpezaServicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tabelaClientes = request.getParameter("clientes");
		String tabelaServicos = request.getParameter("servicos");
		int codProduto = Integer.parseInt(request.getParameter("codProduto"));
		
		LDDAO dao = new LDDAO();
		
		Map<String, Servicos> servicos = dao.getServicos(tabelaClientes, codProduto, tabelaServicos);
		
		if(servicos.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("./pages/tabelaNaoEncontrada.jsp");
			rd.forward(request, response);
		}
		
		dao.inserirNovosServicos(servicos);
		
		RequestDispatcher rd = request.getRequestDispatcher("./pages/sucessoLimpeza.jsp");
		rd.forward(request, response);
	}

}
