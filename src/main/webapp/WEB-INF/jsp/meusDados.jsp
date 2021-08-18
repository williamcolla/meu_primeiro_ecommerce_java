<%-- 
    Document   : meusDados
    Created on : 16/08/2019, 10:05:48
    Author     : GBD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="estrutura/importBot.jsp"%>
        <script src="<%=request.getContextPath()%>/resources/js/dadosCli.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.mask.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $("#cpf").mask("000.000.000-00");
            });
        </script>
        <title>Editar Dados</title>
        <link rel="shortcut icon" href="resources/eco.ico">
    </head>
    <body>
        <%@include file="estrutura/cabecalho.jsp"%>

        <div class="container">
            <br>
            <br>
            <div class="row justify-content-center" id="main">

                <div class="col-md-8 well" id="rightPanel">
                    <div class="row">
                        <div class="col-md-12">
                            <form:form action="editarUsuario" id="formEditarDados" commandName="TbCliente" method="POST" enctype="multipart/form-data">
                                <hr class="colorgraph">
                                <div class="form-group">
                                    <form:input type="text" path="nomecli" name="nome" id="nome" class="form-control input-lg" placeholder="Nome" tabindex="1"></form:input>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6 col-md-6">
                                            <div class="form-group">
                                            <form:input type="text" path="cpf" name="cpf" id="cpf" class="form-control input-lg" placeholder="CPF" tabindex="2" readonly="true"></form:input>
                                            </div>
                                        </div>
                                        <div class="col-xs-12 col-sm-6 col-md-6">		
                                            <div class="form-group">
                                            <form:input type="email" path="emailcli" name="email" id="email" class="form-control input-lg" placeholder="Email" tabindex="3"></form:input>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6 col-md-6">
                                            <div class="form-group">
                                            <form:input type="password" path="senhacli" name="senha" id="senha" class="form-control input-lg" placeholder="senha" tabindex="4"></form:input>
                                            </div>
                                        </div>
                                        <div class="col-xs-12 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="password" name="senha2" id="senha2" class="form-control input-lg" placeholder="Confirmar Senha" tabindex="5">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6 col-md-6">
                                            <div class="form-group">
                                            <form:input type="text" path="ruacli" name="rua" id="rua" class="form-control input-lg" placeholder="Rua" tabindex="6"></form:input>
                                            </div>
                                        </div>
                                        <div class="col-xs-12 col-sm-6 col-md-6">		
                                            <div class="form-group">
                                            <form:input type="text" path="numerocli" name="numero" id="numero" class="form-control input-lg" placeholder="Numero" tabindex="7"></form:input>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6 col-md-6">
                                            <div class="form-group">
                                            <form:input type="text" path="bairrocli" name="bairro" id="bairro" class="form-control input-lg" placeholder="Bairro" tabindex="8"></form:input>
                                            </div>
                                        </div>
                                        <div class="col-xs-12 col-sm-6 col-md-6">		
                                            <div class="form-group">
                                            <form:input type="text" path="cidadecli" name="cidade" id="cidade" class="form-control input-lg" placeholder="Cidade" tabindex="9"></form:input>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6 col-md-6">
                                            <div class="form-group">
                                            <form:input type="hidden" path="tipo" name="tipo" id="tipo" class="form-control input-lg" tabindex="10"></form:input>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6 col-md-12">
                                            <div class="form-group">
                                                <div class="box">
                                                <form:select id="estado" path="estadocli" cssClas="form-control" required="requeired" data-tootle="tooltip">
                                                    <form:option value="0">-- Selecione --</form:option>
                                                    <form:option value="AC">AC</form:option>
                                                    <form:option value="AL">AL</form:option>
                                                    <form:option value="AP">AP</form:option>
                                                    <form:option value="AM">AM</form:option>
                                                    <form:option value="BA">BA</form:option>
                                                    <form:option value="CE">CE</form:option>
                                                    <form:option value="DF">DF</form:option>
                                                    <form:option value="ES">ES</form:option>
                                                    <form:option value="GO">GO</form:option>
                                                    <form:option value="MA">MA</form:option>
                                                    <form:option value="MT">MT</form:option>
                                                    <form:option value="MS">MS</form:option>
                                                    <form:option value="MG">MG</form:option>
                                                    <form:option value="PA">PA</form:option>
                                                    <form:option value="PB">PB</form:option>
                                                    <form:option value="PR">PR</form:option>
                                                    <form:option value="PE">PE</form:option>
                                                    <form:option value="PI">PI</form:option>
                                                    <form:option value="RJ">RJ</form:option>
                                                    <form:option value="RN">RN</form:option>
                                                    <form:option value="RS">RS</form:option>
                                                    <form:option value="RO">RO</form:option>
                                                    <form:option value="RR">RR</form:option>
                                                    <form:option value="SC">SC</form:option>
                                                    <form:option value="SP">SP</form:option>
                                                    <form:option value="SE">SE</form:option>
                                                    <form:option value="TO">TO</form:option>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr class="colorgraph">
                                <div class="row justify-content-center">
                                    <div class="col-xs-12 col-md-6"><button type="submit" class="btn btn-success btn-block btn-lg">Editar</button></div>
                                </div>
                            </form:form>
                        </div>
                    </div>

                </div>
            </div>
        </div>      
        <br />
        <%@include file="estrutura/rodape.jsp"%> 
    </body>
</html>
