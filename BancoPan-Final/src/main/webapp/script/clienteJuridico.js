//Set filtros
setInputFilter(document.getElementById('nomeCliente'), function (value) {
    return /^[a-z]*$/i.test(value);
}, '')

setInputFilter(document.getElementById('cep'), function (value) {
    return /^-?\d*[-]?\d*$/.test(value);
}, '')

setInputFilter(document.getElementById('numero_rua'), function (value) {
    return /^\d*$/.test(value);
}, '')
//Fim - Set filtros

//Set mascaras
setInputMasc(document.getElementById('cnpj'), cnpjMasc)

setInputMasc(document.getElementById('telefone'), telefoneMasc)

setInputMasc(document.getElementById('cep'), cepMasc)
//Fim - Set mascaras