<%@page import="br.fiap.DAO.Cliente.ClienteDAO"%>
<%@page import="br.fiap.Servicos.Servicos"%>
<%@page import="br.fiap.Cliente.ClienteJuridico"%>
<%
ClienteJuridico cliente = (ClienteJuridico) request.getAttribute("cliente");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banca PAN - Cliente Juridico</title>

<link rel="shortcut icon"
	href="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg"
	type="image/x-icon">
<link rel="stylesheet" href="./style/pageClienteFisico.css">
</head>
<body>


	<main class="container">

		<div class="titleContainer">
            <h1>Meus Servi�os</h1>
            <a href="./index.jsp">Voltar</a>
        </div>


		<section id="transaction">
			<p>
				Bem vindo(a) <span class="nome"><%=cliente.getNome()%></span>!
			</p>

			<a href="pages/paginaNaoServicos.jsp" class="button new">+
				Adicionar novo Servi�o</a>

			<table id="data-table">
				<thead>
					<tr>
						<th><h3>Nome do Servi�o</h3></th>
						<th></th>
					</tr>
				</thead>
				<tbody>

					<%
					for (Servicos servicos : new ClienteDAO().listarServicos(cliente)) {
					%>
					<tr>
						<td><%=servicos%></td>
					</tr>
					<%
					}
					%>

				</tbody>
			</table>
		</section>
	</main>


</body>
</html>