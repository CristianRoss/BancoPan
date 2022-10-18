<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banco PAN - Login</title>

<link rel="stylesheet" href="../style/pag_login.css">

<link rel="shortcut icon"
	href="../Images/LogoPan_white.svg"
	type="image/x-icon">
</head>
<body>
	<section class="forms-section">
		<!-- <h1 class="section-title">Animated Forms</h1> -->
		<div class="forms">
			<div class="form-wrapper is-active">
				<button type="button" class="switcher switcher-login">
					Login <span class="underline"></span>
				</button>
				<form action="../loginUsuario" class="form form-login">
					<fieldset>
						<div class="input-block">
							<label for="login-ident">CPF/CNPJ</label> <input id="login-ident"
								type="text" name="ident" maxlength="18"
								onkeypress="mascaraIdent(this)" onkeyup="mascaraIdent(this)"
								required>
						</div>
						<div class="input-block">
							<label for="login-password">Senha</label> <input
								id="login-password" name="senha" type="password" required>
						</div>
					</fieldset>
					<button type="submit" class="btn-login">Login</button>
				</form>
			</div>
			<div class="form-wrapper">
				<button type="button" class="switcher switcher-signup">
					Admin <span class="underline"></span>
				</button>
				<form action="../loginAdmin" class="form form-signup">
					<fieldset>
						<div class="input-block">
							<label for="loginAdm">Login</label> <input id="loginAdm"
								type="text" name="loginAdm" required>
						</div>
						<div class="input-block">
							<label for="signup-password">Senha</label> <input
								id="signup-password" name="adminSenha" type="password" required>
						</div>
					</fieldset>
					<button type="submit" class="btn-signup">Continue</button>
				</form>
			</div>
		</div>
	</section>

	<script src="../script/util.js"></script>
	<script src="../script/login.js"></script>
</body>
</html>