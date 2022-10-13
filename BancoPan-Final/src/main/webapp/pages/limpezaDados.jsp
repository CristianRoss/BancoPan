<!DOCTYPE html>
<html lang="pt_br">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Banco PAN - Consultar cliente</title>

  <link rel="stylesheet" href="../style/style.css">

  <link rel="shortcut icon" href="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg"
    type="image/x-icon">

</head>

<body>



  <img src="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg" alt="Logo banco PAN"
    class="logo_bancoPan">

  <p class="textDescricao">Digite o nome da tabela que será limpa</p>


  <form action="../limpezaDados" method="post">
    <input type="text" name="tabela" id="tabela" autocomplete="off"
      placeholder="Digite o nome da tabela" title="Digite o nome da tabela" required>
    <div class="linha"></div>

    <input type="submit" id="pesquisarTabela" value="Pesquisar">
  </form>


  <script src="../script/util.js"></script>
</body>

</html>