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
			<h1>Meus Serviços</h1>
		</div>


		<section id="transaction">
			<!-- TODO: COLOCAR NOME -->
			<p>
				Bem vindo(a) <span class="nome"><%=cliente.getNome()%></span>!
			</p>

			<a href="#" onclick="" class="button new">+ Adicionar novo
				Serviço</a>

			<table id="data-table">
				<thead>
					<tr>
						<th><h3>Nome do Serviço</h3></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				
					<tr>
						<%
						for (Servicos servicos : new ClienteDAO().listarServicos(cliente)) {
						%>
						<td><%=servicos%></td>

						<td><a href="#">Entrar</a></td>
						<%
						}
						%>
					</tr>



					<!-- <tr>
						<td>Conta Poupança</td>
						<td><a href="#">Entrar</a></td>
					</tr>

					<tr>
						<td>Conta Corrente</td>
						<td><a href="#">Entrar</a></td>
					</tr>

					<tr>
						<td>Maquininha</td>
						<td><a href="#">Entrar</a></td>
					</tr>

					<tr>
						<td>Cartão de Crédito</td>
						<td><a href="#">Entrar</a></td>
					</tr>

					<tr>
						<td>Cartão de Débito</td>
						<td><a href="#">Entrar</a></td>
					</tr>

					<tr>
						<td>Financiamento</td>
						<td><a href="#">Entrar</a></td>
					</tr>

					<tr>
						<td>Emprestimo</td>
						<td><a href="#">Entrar</a></td>
					</tr>

					<tr>
						<td>PIX</td>
						<td><a href="#">Entrar</a></td>
					</tr>
 -->
				</tbody>
			</table>
		</section>
	</main>


</body>
</html>