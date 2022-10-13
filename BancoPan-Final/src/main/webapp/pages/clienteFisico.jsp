<%@page import="br.fiap.Servicos.Servicos"%>
<%@page import="br.fiap.DAO.Cliente.ClienteDAO"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="br.fiap.Cliente.ClienteFisico"%>
<%
ClienteFisico cliente = (ClienteFisico) request.getAttribute("cliente");
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg"
	type="image/x-icon">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./style/pageClienteFisico.css">
<title>Banco PAN - Cliente Fisico</title>
</head>

<body>


	<main class="container">

		<div class="titleContainer">
            <h1>Meus Serviços</h1>
            <a href="./index.jsp">Voltar</a>
        </div>


		<section id="transaction">
			<p>
				Bem vindo(a) <span class="nome"><%=cliente.getNome()%></span>!
			</p>

			<a href="pages/paginaNaoServicos.jsp" onclick="" class="button new">+
				Adicionar novo Serviço</a>

			<table id="data-table">
				<thead>
					<tr>
						<th><h3>Serviços</h3></th>
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