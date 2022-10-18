<!DOCTYPE html>
<html lang="pt_br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banco PAN - Escolha um serviço</title>

<!-- <link rel="stylesheet" href="./style/style.css"> -->
<link rel="stylesheet" href="../style/styleDebito.css">

<link rel="shortcut icon"
	href="../Images/LogoPan_white.svg"
	type="image/x-icon">

</head>

<body>



	<img
		src="../Images/LogoPan_white.svg"
		alt="Logo banco PAN" class="logo_bancoPan">

	<p class="textDescricao">
		Pra cadastrar<br /> um novo serviço <br /> selecione uma opção.
	</p>


	<form action="../adicionarServicos" method="POST">

		<div class="select-box">
			<div class="select-box__current" tabindex="1">
				<div class="select-box__value">
					<input class="select-box__input" type="radio" id="0" value="Cartao Debito"
						name="Ben" checked="checked" />
					<p class="select-box__input-text">Cartão Débito</p>
				</div>
				<div class="select-box__value">
					<input class="select-box__input" type="radio" id="1" value="Cartao Credito"
						name="Ben" />
					<p class="select-box__input-text">Cartão Crédito</p>
				</div>
				<div class="select-box__value">
					<input class="select-box__input" type="radio" id="2" value="Poupanca"
						name="Ben" />
					<p class="select-box__input-text">Poupança</p>
				</div>
				<div class="select-box__value">
					<input class="select-box__input" type="radio" id="3" value="Emprestimo"
						name="Ben" />
					<p class="select-box__input-text">Emprestimo</p>
				</div>
				<div class="select-box__value">
					<input class="select-box__input" type="radio" id="4" value="Financiamento"
						name="Ben" />
					<p class="select-box__input-text">Financiamento</p>
				</div>
				<div class="select-box__value">
					<input class="select-box__input" type="radio" id="5" value="Maquininha"
						name="Ben" />
					<p class="select-box__input-text">Maquininha</p>
				</div>
				<div class="select-box__value">
					<input class="select-box__input" type="radio" id="6" value="PIX"
						name="Ben" />
					<p class="select-box__input-text">PIX</p>
				</div>
				
				<img class="select-box__icon"
					src="http://cdn.onlinewebfonts.com/svg/img_295694.svg"
					alt="Arrow Icon" aria-hidden="true" />
			</div>
			<ul class="select-box__list">
				<li><label class="select-box__option" for="0"
					aria-hidden="aria-hidden">Cartão Débito</label></li>
				<li><label class="select-box__option" for="1"
					aria-hidden="aria-hidden">Cartão Crédito</label></li>
				<li><label class="select-box__option" for="2"
					aria-hidden="aria-hidden">Poupança</label></li>
				<li><label class="select-box__option" for="3"
					aria-hidden="aria-hidden">Emprestimo</label></li>
				<li><label class="select-box__option" for="4"
					aria-hidden="aria-hidden">Financiamento</label></li>
				<li><label class="select-box__option" for="5"
					aria-hidden="aria-hidden">Maquininha</label></li>
				<li><label class="select-box__option" for="6"
					aria-hidden="aria-hidden">PIX</label></li>
			</ul>
		</div>

		<input type="submit" value="Cadastrar novo Serviço">
	</form>
</body>

</html>