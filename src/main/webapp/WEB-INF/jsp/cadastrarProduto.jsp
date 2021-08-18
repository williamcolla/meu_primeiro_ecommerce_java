<%-- 
    Document   : cadastrarProduto
    Created on : Jul 10, 2019, 9:28:33 AM
    Author     : G09
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="estrutura/importBot.jsp"%>

        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.mask.min.js"></script>


        <script type="text/javascript">
            $(document).ready(function () {
                $("#preco").mask("###0.00", {reverse: true});
                $("#qtd").mask("00000");
            });
        </script>

        <title>Cadastrar Produto</title>
        <link rel="shortcut icon" href="resources/eco.ico">

    </head>
    <body>
        <%@include file="estrutura/cabecalho.jsp"%>

        <br />
        <div class="container">
            <br>
            <br>
            <div class="row justify-content-center" id="main">

                <div class="col-md-8 well" id="rightPanel">
                    <div class="row">
                        <div class="col-md-12">
                            <form:form action="salvarProduto" id="formCadastroProduto" commandName="TbProduto" method="POST" enctype="multipart/form-data">
                                <div class="row justify-content-center">
                                    <p>${erroCad}</p>
                                </div>
                                <hr class="colorgraph">
                                <div class="form-group">
                                    <jsp:useBean id="dao" class="DAO.CategoriaDAO">
                                    <form:select id="categoria" path="tbCategoria.idcat" cssClas="form-control animated bounceInDown" class="form-control animated bounceInDown" required="requeired" data-tootle="tooltip">
                                        <form:option value="0">-- Selecione a Categoria --</form:option>
                                        <form:options items="${dao.buscarCat()}" itemValue="idcat" itemLabel="nomecat"></form:options>
                                    </form:select> 
                                </jsp:useBean>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6 col-md-6">
                                            <div class="form-group">
                                            <form:input id="nome" type="text" path="nomepro" cssClass="form-control" placeholder="Nome Produto" required="required" data-tootle="tooltip"></form:input>
                                            </div>
                                        </div>
                                        <div class="col-xs-12 col-sm-6 col-md-3">		
                                            <div class="form-group">
                                            <form:input id="preco" type="text" path="precopro" cssClass="form-control" placeholder="Preço" required="required" data-tootle="tooltip"></form:input>
                                            </div>
                                        </div>
                                            <div class="col-xs-12 col-sm-6 col-md-3">		
                                            <div class="form-group">
                                             <form:input id="qtd" type="text" path="qtdpro" cssClass="form-control" placeholder="Quantidade" required="required" data-tootle="tooltip"></form:input>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6 col-md-12">
                                            <div class="form-group">
                                            <form:textarea rows="5" height="50px" id="descricao" type="text" path="descrpro" cssClass="form-control" placeholder="Descrição" required="required" data-tootle="tooltip"></form:textarea>
                                            </div>
                                        </div>                                    
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6 col-md-6">
                                            <div class="form-group">
                                            <input id="imagem" type="file" name="file" class="form-control-file" required="required" data-tootle="tooltip"/>
                                            </div>
                                        </div>                                      
                                    </div>                                   
                                    <hr class="colorgraph">
                                    <div class="row justify-content-center">
                                        <div class="col-xs-12 col-md-6"><button type="submit" class="btn btn-success btn-block btn-lg">Cadastrar</button></div>
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



