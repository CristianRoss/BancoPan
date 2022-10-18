<!DOCTYPE html>
<html lang="pt_br">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Banco PAN - Cadastrar Chave PIX</title>

  <link rel="stylesheet" href="../style/style.css">
  <link rel="stylesheet" href="../style/opcoesPIX.css">


  <link rel="shortcut icon" href="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg"
    type="image/x-icon">

</head>

<body>



  <img src="https://accountstemplates.bancopan.com.br/assets/img/LogoPan_white.svg" alt="Logo banco PAN"
    class="logo_bancoPan">

  <p class="textDescricao">Selecione Uma opção de cadastro para PIX</p>
  


<form action="../cadastrarPIX">

<div class="wrapper">
        <input type="radio" name="select" id="option-1" value="ident" checked>
        <input type="radio" name="select" id="option-2" value="email">
        <input type="radio" name="select" id="option-3" value="celular">

        <label for="option-1" class="option option-1">
            <div class="dot"></div>
            <span>CPF/CNPJ</span>
        </label>

        <label for="option-2" class="option option-2">
            <div class="dot"></div>
            <span>E-mail</span>
        </label>

        <label for="option-3" class="option option-3">
            <div class="dot"></div>
            <span>Celular</span>
        </label>      
        
    </div>
    
    <input type="submit" name="cadastrar" value="Cadastrar" id="btn">

</form>

</body>

</html>