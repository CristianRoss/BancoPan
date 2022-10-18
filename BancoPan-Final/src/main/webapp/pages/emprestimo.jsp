<!DOCTYPE html>
<html lang="pt_br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banco PAN - Emprestimo</title>

<link rel="stylesheet" href="./style/style.css">
<link rel="stylesheet" href="../style/style.css">

<link rel="shortcut icon"
	href="./Images/LogoPan_white.svg"
	type="image/x-icon">

</head>

<body>



	<img
		src="./Images/LogoPan_white.svg"
		alt="Logo banco PAN" class="logo_bancoPan">

	<p class="textDescricao">
		Pra realizar<br /> um emprestimo <br /> Digite um limite.
	</p>


	<form action="./emprestimo" method="post">
		<input type="text" name="valorEmprestimo" id="valorEmprestimo"
			autocomplete="off" maxlength="7" placeholder="Digite um valor"
			title="Digite um valor para o emprestimo" required>
		<div class="linha"></div>


		<input type="text" name="qtdParcelas" id="qtdParcelas"
			autocomplete="off" maxlength="7" placeholder="Quantidade de Parcelas"
			title="Digite a quantidade de Parcelas" required>
		<div class="linha"></div>

		<input type="text" placeholder="Dia da Parcela"
			title="Digite o dia da Parcela" name="diaParcela" id="diaParcela"
			autocomplete="off" maxlength="2" min="1" max="31" required>
		<div class="linha"></div>


		<p class="descricaoInput">Use apenas n�meros</p>

		<input type="submit" id="realizarEmprestimo"
			value="Realizar Emprestimo">
	</form>

</body>

</html>