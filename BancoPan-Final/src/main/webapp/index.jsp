<!DOCTYPE html>
<html class="no-js" lang="pt-br">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="description"
	content="An example pen showing how a basic CSS Grid container can create a nice, responsive card layout.">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="./style/pag_inicial.css">

<link rel="shortcut icon"
	href="./Images/LogoPan_white.svg"
	type="image/x-icon">

<title>Banco PAN</title>
</head>
<body>
	<div class="grid">

		<div class="grid__item">
			<div class="card">
				<img class="card__img"
					src="https://www.bancopan.com.br/bancopan-institucional/conteudo/home/assets/img/produtos/product-01-desk.png"
					alt="Desert">
				<div class="card__content">
					<h1 class="card__header">Entre</h1>
					<p class="card__text">
						Entre com suas informações de <strong>Login e Senha</strong> e
						tenha acesso aos serviços oferecidos pelo Banco PAN.
					</p>
					<a href="./pages/login.jsp" class="card__btn">Ir<span>&rarr;</span></a>
				</div>
			</div>
		</div>
		<div class="grid__item">
			<div class="card">
				<img class="card__img"
					src="https://images.pexels.com/photos/6205505/pexels-photo-6205505.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
					alt="Canyons">
				<div class="card__content">
					<h1 class="card__header">Faça o cadastro</h1>
					<p class="card__text">
						Preencha o fomulário com suas <strong>credenciais</strong> e
						tenha acesso aos serviços oferecidos pelo Banco PAN.
					</p>
					<a href="./pages/cadastroClienteFisico.jsp" class="card__btn">Pessoa
						Fisíca <span>&rarr;</span>
					</a> <br /> <a href="./pages/cadastroClienteJuridico.jsp"
						class="card__btn">Pessoa Jurídica <span>&rarr;</span></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
