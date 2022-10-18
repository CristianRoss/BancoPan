<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banco PAN - Página Administrador</title>

<link rel="shortcut icon"
	href="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg"
	type="image/x-icon">

<link rel="stylesheet" type="text/css" href="./style/AdminStyles.css">
<link rel="stylesheet" href="./style/AdminCard.css">

</head>

<style type="text/css">
.logoBack {
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.logoBack a {
	border-radius: 10px;
	width: 4rem;
}

.logoBack a img {
	width: 4rem;
	background-color: #fff;
	border-radius: 10px;
	opacity: .5;
}
</style>

<body>

	<main>
		<div class="title_page">

			<div class="logoBack">
				<img
					src="https://www.bancopan.com.br/bancopan-institucional/conteudo/estrutura/assets/img/mh-icons/mh-icon--logo-desktop.svg"
					alt=""> <a href="./index.jsp"> <img
					src="./Images/left.png" alt="" width="100">
				</a>

			</div>




			<h1>Página Administrador</h1>
		</div>

		<div class="buttons">
			<section class="cards">
				<article class="card card--1">
					<div class="card__img"></div>
					<a href="./pages/consultaCliente.jsp" class="card_link">
						<div class="card__img--hover"></div>
					</a>
					<div class="card__info">
						<span class="card__category">Consultar Dados</span>
						<h3 class="card__title">Consulta os dados no banco novo.</h3>

					</div>
				</article>


				<article class="card card--2">
					<div class="card__img"></div>
					<a href="./pages/opcoesLimpeza.jsp" class="card_link">
						<div class="card__img--hover"></div>
					</a>
					<div class="card__info">
						<span class="card__category">Limpeza</span>
						<h3 class="card__title">Transfere os arquivos para limpeza.</h3>
					</div>
				</article>


				<article class="card card--3">
					<div class="card__img"></div>
					<a href="./pages/CSV.jsp" class="card_link">
						<div class="card__img--hover"></div>
					</a>
					<div class="card__info">
						<span class="card__category">Baixar base de dados</span>
						<h3 class="card__title">Transfere um arquivo csv com dados.</h3>

					</div>
				</article>
			</section>
		</div>
	</main>

</body>

</html>