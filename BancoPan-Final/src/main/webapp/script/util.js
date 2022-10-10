//Mascara
function mascaraIdent(obj) {
  let inputNum = obj.value.replace(/\D/g, '')
  let numLength = inputNum.length

  if (numLength <= 11)
    mascara(obj, cpfMasc)
  else
    mascara(obj, cnpjMasc)
}

function mascara(o, f) {
  v_obj = o
  v_fun = f
  setTimeout('execmascara()', 0)
}

function execmascara() {
  v_obj.value = v_fun(v_obj.value)
}

function cpfMasc(v) {
  v = v.replace(/\D/g, '')
  v = v.replace(/(\d{3})(\d)/, '$1.$2')
  v = v.replace(/(\d{3})(\d)/, '$1.$2')
  v = v.replace(/(\d{3})(\d{1,2})$/, '$1-$2')
  return v
}

function cnpjMasc(v) {
  v = v.replace(/\D/g, '')
  v = v.replace(/(\d{2})(\d)/, '$1.$2')
  v = v.replace(/(\d{3})(\d)/, '$1.$2')
  v = v.replace(/(\d{3})(\d)/, '$1/$2')
  v = v.replace(/(\d{4})(\d)/, '$1-$2')
  return v
}

function telefoneMasc(v) {
  v = v.replace(/\D/g, '')
  v = v.replace(/^(\d\d)(\d)/g, '($1) $2')
  if (v.length < 14)
    v = v.replace(/(\d{4})(\d)/, '$1-$2')
  else
    v = v.replace(/(\d{5})(\d)/, '$1-$2')
  return v
}

function cepMasc(v) {
  v = v.replace(/D/g, '')
  v = v.replace(/^(\d{5})(\d)/, '$1-$2')
  return v
}

function setInputMasc(obj, masc){
  ['input', 'keydown', 'keyup', 'mousedown', 'mouseup', 'select', 'contextmenu', 'drop', 'focusout'].forEach(function (event){
    obj.addEventListener(event, function (e){
      mascara(obj, masc)
    })
  })
}
//Fim - Mascara

//Filtro
function setInputFilter(textbox, inputFilter, errMsg) {
  ['input', 'keydown', 'keyup', 'mousedown', 'mouseup', 'select', 'contextmenu', 'drop', 'focusout'].forEach(function (event) {
    textbox.addEventListener(event, function (e) {
      if (inputFilter(this.value)) {
        // Accepted value
        if (['keydown', 'mousedown', 'focusout'].indexOf(e.type) >= 0) {
          this.classList.remove('input-error')
          this.setCustomValidity('')
        }
        this.oldValue = this.value
        this.oldSelectionStart = this.selectionStart
        this.oldSelectionEnd = this.selectionEnd
      } else if (this.hasOwnProperty('oldValue')) {
        // Rejected value - restore the previous one
        this.classList.add('input-error')
        this.setCustomValidity(errMsg)
        this.reportValidity()
        this.value = this.oldValue
        this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd)
      } else {
        // Rejected value - nothing to restore
        this.value = ''
      }
    })
  })
}
//Fim - Filtro

//Confirmacoes
// Email
let email = document.getElementById("email")
let confirmar_email = document.getElementById("confirmar_email")

function validarEmail(){
  if(email.value != confirmar_email.value) {
    confirmar_email.setCustomValidity("Emails nao conferem");
  } else {
    confirmar_email.setCustomValidity('');
  }
}

email.onchange = validarEmail;
confirmar_email.onkeyup = validarEmail;


//Senha
let senha = document.getElementById("senha")
let confirmar_senha = document.getElementById("confirmar_senha")

function validarSenha(){
  if(senha.value != confirmar_senha.value) {
    confirmar_senha.setCustomValidity("Senhas nao conferem");
  } else {
    confirmar_senha.setCustomValidity('');
  }
}

senha.onchange = validarSenha;
confirmar_senha.onkeyup = validarSenha;
//Fim - Confirmacoes
