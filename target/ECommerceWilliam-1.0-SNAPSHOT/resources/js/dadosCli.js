$(document).ready(function () {
    
    $("#cpf").mask("000.000.000-00");
    
    $("#estado").change(function () {
        alert($("#estado").val());
    });
    
    $("#formEditarDados").submit(function (e) {     
        if ($("#senha").val() != $("#senha2").val()) {
            $("#senha").css("border-color", "red");
            $("#senha2").css("border-color", "red");
            alert("As senhas devem ser iguais");
            e.preventDefault();
        } 
        
        else if($("#estado").val() == "0"){
            alert("Selecione um estado");
            e.preventDefault();
        }
        
        else {
            alert("Edição efetuada com sucesso!");
        }
    });
    
    
});