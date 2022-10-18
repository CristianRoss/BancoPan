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
import br.fiap.DAO.Servicos.Contas.ContaCorrenteDAO;
import br.fiap.DAO.Servicos.Contas.ContasDAO;
import br.fiap.Servicos.Contas.ContaCorrente;

/**
 * Servlet implementation class CadastrarPIXServlet
 */
@WebServlet("/cadastrarPIX")
public class CadastrarPIXServlet extends HttpServlet {
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

		Cookie[] cookies = request.getCookies();
		Cliente cliente = null;
		
		

		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equalsIgnoreCase("cliente")) {
				cliente = Cliente.clientes.get(Integer.parseInt(cookies[i].getValue()));
				break;
			}
		}
		
		String ident=null;
		
		
		if (cliente instanceof ClienteFisico) {
			ident=((ClienteFisico)cliente).getCpf();
		}else {
			ident=((ClienteJuridico)cliente).getCnpj();
		}
		boolean atualizou=false;
		ContaCorrente conta=new ContaCorrenteDAO().getContaporCliente(cliente.getIdCliente());
		System.out.println(conta.getIdConta());
		String opcao = request.getParameter("select");
		switch (opcao) {
		case "ident": {
			atualizou=new ContasDAO().atualizarPIX(conta, ident);
			break;
		}
		case "email": {
			atualizou=new ContasDAO().atualizarPIX(conta, cliente.getEmail());
            break;
		}
		case "celular": {
            atualizou=new ContasDAO().atualizarPIX(conta, ""+cliente.getTelefone());
            break;
		}
	}
		
		if (atualizou) {
			RequestDispatcher rd = request.getRequestDispatcher("./pages/atualizouChave.jsp");
			request.setAttribute("cliente", cliente);
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("./pages/error.jsp");
			rd.forward(request, response);

		}

 }

}
