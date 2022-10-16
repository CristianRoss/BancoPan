<!DOCTYPE html>
<html lang="pt_br">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Banco PAN - Cart�o Cr�dito</title>

  <link rel="stylesheet" href="./style/style.css">

  <link rel="shortcut icon" href="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg"
    type="image/x-icon">

</head>

<body>

  <img src="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg" alt="Logo banco PAN"
    class="logo_bancoPan">

  <p class="textDescricao">Pra cadastrar<br />
    um cart�o cr�dito <br />
    Digite um limite.
  </p>


  <form action="./cartaoCredito" method="post">
    <input type="text" name="limiteCebito" id="limiteCebito" autocomplete="off" maxlength="7"
      placeholder="Digite um limite" title="Limite cart�o cr�dito" required>
    <div class="linha"></div>



    <p class="descricaoInput">Use apenas n�meros</p>

    <input type="submit" id="cadastrarCartaoCebito" value="Cadastrar">
  </form>

</body>

</html>