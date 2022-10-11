<%@page import="br.fiap.Cliente.ClienteFisico"%>
<% ClienteFisico cliente = (ClienteFisico) request.getAttribute("cliente"); %>
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
			<h1>Meus Servi�os</h1>
		</div>


		<section id="transaction">
			<p>
				Bem vindo(a) <span class="nome"><%= cliente.getNome() %></span>!
			</p>

			<a href="#" onclick="" class="button new">+ Adicionar novo
				Servi�o</a>

			<table id="data-table">
				<thead>
					<tr>
						<th><h3>Servi�os</h3></th>
						<th></th>
					</tr>
				</thead>
				<tbody>

					<tr>
						<td>Conta Poupan�a</td>
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
						<td>Cart�o de Cr�dito</td>
						<td><a href="#">Entrar</a></td>
					</tr>

					<tr>
						<td>Cart�o de D�bito</td>
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

				</tbody>
			</table>
		</section>
	</main>


</body>

</html>