/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//$('#form').validate({
//    // O campo Senha é de preenchimento obrigatório (required)
//    senha: {
//        required: true
//    },
//    // O campo Confirma Senha é de preenchimento obrigatório (required) 
//    // e deve ser igual ao campo Senha (equalTo)
//    senha2: {
//        required: true,
//        equalTo: "#senha"
//    }
//})
$(document).ready(function () {

    $("#cpf").mask("000.000.000-00");

//    $("#estado").change(function () {
//        alert($("#estado").val());
//    }); 

    $("#formCadastroCliente").submit(function (e) {
        var cpf = formCadastrar.cpf.value; 
        
        if ($("#senha").val() != $("#senha2").val()) {
            $("#senha").css("border-color", "red");
            $("#senha2").css("border-color", "red");
            alert("As senhas devem ser iguais");
            e.preventDefault();
        }

        else if (cpf.length < 14) {
            $("#cpf").css("border-color", "red");
            alert("Digite corretamente o cpf!");
            e.preventDefault();
        } 
        else if ($("#estado").val() == "0") {
            alert("Selecione um estado");
            e.preventDefault();
        } 
        else {
            alert("Cadastro efetuado com sucesso!");
        }
    });


});








