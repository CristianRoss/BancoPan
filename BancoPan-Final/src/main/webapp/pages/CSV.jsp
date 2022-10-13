<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banco PAN - Gerador CSV</title>
</head>

<link rel="shortcut icon"
	href="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg"
	type="image/x-icon">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;0,700;0,900;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">

<style>
body {
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	text-align: center;
	background: #00AFF9
		url(https://cbwconline.com/IMG/Codepen/Unplugged.png) center/cover
		no-repeat;
	height: 100vh;
	margin: 0;
	color: white;
	font-family: Roboto, -apple-system, 'Helvetica Neue', 'Segoe UI', Arial,
		sans-serif;
}

h1 {
	margin: .8em 0rem;
	font: 4em Roboto;
}

p {
	display: inline-block;
	margin: .2em 3rem;
	font: 2em Roboto;
}

a, input[type="submit"] {
	text-decoration: none;
	color: #fff;
	padding: 2rem 8vw;
	margin-top: 2rem;
	font-size: 1.2rem;
	background-color: rgb(5, 144, 206);
	color: rgb(130, 199, 231);
	cursor: pointer;
	border: none;
}

input[type="submit"] {
	margin-bottom: 8rem;
}

a:hover, input[type="submit"]:hover {
	background-color: rgb(255, 255, 255);
	color: rgb(33, 37, 41);
}

.voltar {
	margin-top: 6rem;
	background-color: rgb(206, 45, 5);
	color: rgb(255, 255, 255);
	padding: 1rem 5vw;
}
</style>

<body>

	<form action="../gerarCSV" method="POST">
		<h1>Gerar arquivo CSV</h1>
		<p>Clique para Baixar o CSV!</p>


		<div class="sumbitButton">
			<input type="submit" value="Baixar">
		</div>
	</form>


</body>

</html>