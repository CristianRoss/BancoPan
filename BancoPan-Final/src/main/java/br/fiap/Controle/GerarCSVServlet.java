package br.fiap.Controle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.fiap.Cliente.Cliente;
import br.fiap.DAO.Cliente.ClienteDAO;

/**
 * Servlet implementation class GerarCSVServlet
 */
@WebServlet("/gerarCSV")
public class GerarCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            File f = new File("C:\\Users\\fsous\\Desktop\\clientes.csv");
            PrintStream ps = new PrintStream(f);
            //ps.println("ID_CLIENTE, CPF, CPNJ, NOME, SOBRENOME, TELEFONE, EMAIL, ENDERECO, CEP, SEXO, DATA_NASCIMENTO");

            ps.println("Nome, Sobrenome, CPF, CNPJ, email, endereco, CEP, Sexo, aniversario");

            new ClienteDAO().listarClientes();
            HashMap<Integer, Cliente> clientes = Cliente.clientes;

            for (Map.Entry<Integer, Cliente> c : clientes.entrySet()) {
                ps.println(c);
            }

            ps.println();

            ps.close();
            System.out.println("CSV gerado com Sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao tentar gerar CSV\n " + e);
        }
		
		RequestDispatcher rd = request.getRequestDispatcher("./pages/admin.jsp");
		rd.forward(request, response);
		
	}

}
