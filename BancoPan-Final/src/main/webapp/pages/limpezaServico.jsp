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


  <form action="../limpezaServico" method="post">
    <input type="text" name="clientes" id="tabela" autocomplete="off"
      placeholder="Tabela de Clientes" title="Digite o nome da tabela de Clientes" required>
    <div class="linha"></div>
    
    <input type="text" name="servicos" id="tabela" autocomplete="off"
      placeholder="Tabela de Serviços" title="Digite o nome da tabela de Serviços" required>
    <div class="linha"></div>
    
    <input type="number" name="codProduto" id="tabela" autocomplete="off"
      placeholder="Código do produto" title="Digite o código do produto" required>
    <div class="linha"></div>

    <input type="submit" id="pesquisarTabela" value="Limpar">
  </form>


  <script src="../script/util.js"></script>
</body>

</html>