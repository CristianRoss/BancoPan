<!DOCTYPE html>
<html lang="pt_br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banca PAN - Cadastro cliente</title>

<link rel="stylesheet" href="../style/style.css">
<link rel="stylesheet" href="./style/style.css">

<link rel="shortcut icon"
	href="../Images/LogoPan_white.svg"
	type="image/x-icon">

</head>

<body>



	<img
		src="../Images/LogoPan_white.svg"
		alt="Logo banco PAN" class="logo_bancoPan">

	<p class="textDescricao">
		Para continuar o <br /> processo de abertura de conta juridica<br />
		insira suas informações
	</p>


	<form action="../cadastroClienteJuridico" method="post">
		<input type="text" name="nomeCliente" id="nomeCliente"
			autocomplete="off" maxlength="38" placeholder="Nome"
			title="Digite o nome corretamente"
			oninput="this.value = this.value.toUpperCase()" required>
		<div class="linha"></div>

		<input type="text" name="cnpj" id="cnpj" class="cnpj"
			autocomplete="off" maxlength="18" placeholder="CNPJ"
			title="Digite um CNPJ no formato: xxx.xxx.xxx-xx" required>
		<div class="linha"></div>

		<input type="tel" placeholder="Telefone de contato"
			title="Digite o telefone" name="telefone" id="telefone"
			maxlength="15" autocomplete="off" required>
		<div class="linha"></div>

		<input type="email" name="email" id="email" class="email"
			autocomplete="off" placeholder="Email" title="Digite o email"
			required>
		<div class="linha"></div>

		<input type="email" name="confirmar_email" id="confirmar_email"
			class="confirmar_email" autocomplete="off"
			placeholder="Confirmar email" title="Digite o email novamente"
			onkeydown="if(event.ctrlKey && event.keyCode==86){return false;}"
			required>
		<div class="linha"></div>

		<input type="text" placeholder="CEP" title="Digite o CEP" name="cep"
			id="cep" maxlength="9" autocomplete="off" required>
		<div class="linha"></div>

		<div class="endereco endereco_rua">
			<input type="text" name="rua" id="rua" class="rua" placeholder="Rua"
				title="Digite a rua" oninput="this.value = this.value.toUpperCase()"
				autocomplete="off" required> <input type="text"
				name="numero_rua" id="numero_rua" class="numero_rua"
				placeholder="N°" title="Digite o numero" maxlength="5"
				autocomplete="off" required>
		</div>
		<div class="linha"></div>

		<input type="text" name="complemento" id="complemento"
			placeholder="Complemento" title="Digite o complemento"
			oninput="this.value = this.value.toUpperCase()" autocomplete="off">
		<div class="linha"></div>

		<input type="password" name="senha" id="senha" placeholder="Senha"
			title="Digite uma senha" autocomplete="off" required>
		<div class="linha"></div>

		<input type="password" name="confirmar_senha" id="confirmar_senha"
			placeholder="Confirmar senha" title="Digite a senha novamente"
			autocomplete="off" required>
		<div class="linha"></div>

		<input type="submit" id="cadastrarCliente" value="Continuar"
			onclick="">
	</form>


	<script src="../script/util.js"></script>
	<script src="../script/clienteJuridico.js"></script>
</body>

</html>