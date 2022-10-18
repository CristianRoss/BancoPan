<!DOCTYPE html>
<html lang="pt_br">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Banco PAN - Financiamento</title>

  <link rel="stylesheet" href="./style/style.css">
  <link rel="stylesheet" href="../style/style.css">

  <link rel="shortcut icon" href="./Images/LogoPan_white.svg"
    type="image/x-icon">

</head>

<body>



  <img src="./Images/LogoPan_white.svg" alt="Logo banco PAN"
    class="logo_bancoPan">

  <p class="textDescricao">Pra realizar<br />
    um financimento <br />
    Preencha o formulário:
  </p>


  <form action="./financiamento" method="post">
    <input type="text" name="valorTotal" id="valorTotal" autocomplete="off" maxlength="7"
      placeholder="Digite o valor total" title="Digite o valor total" required>
    <div class="linha"></div>


    <input type="text" name="qtdParcelas" id="qtdParcelas" autocomplete="off" maxlength="7"
      placeholder="Quantidade de Parcelas" title="Digite a quantidade de Parcelas" required>
    <div class="linha"></div>

    <input type="text" name="entrada" id="entrada" autocomplete="off" maxlength="7"
      placeholder="Digite o valor da entrada" title="Digite o valor da entrada" required>
    <div class="linha"></div>

    <p class="descricaoInput">Use apenas números</p>

    <input type="submit" id="realizarFinancimento" value="Realizar financimento">
  </form>

</body>

</html>