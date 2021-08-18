<%-- 
    Document   : comprarProduto
    Created on : 14/08/2019, 17:05:17
    Author     : GBD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="estrutura/importBot.jsp"%>
        <title>Comprar</title>
        <link rel="shortcut icon" href="resources/eco.ico">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/compra.css">
        <style type="text/css">
            #imagem{
                max-width: 500px;
                height: 320px;
            }

            #image{
                width: 800px;
                height: 321px;
            }

            .tamanho{
                width: auto;
            }
        </style>
        <script type="text/javascript">
            function process(quant) {
                var value = parseInt(document.getElementById("quant").value);
                value += quant;
                if (value < 1) {
                    document.getElementById("quant").value = 1;
                } else {
                    document.getElementById("quant").value = value;
                }
            }
        </script>

    </head>
    <body>
        <%@include file="estrutura/cabecalho.jsp"%>
        <br />
        <div class="container">
            <form action="salvarCompra" id="formComprarProduto"  method="POST" >

                <div class="media">
                    <img id="imagem" class="align-self-star mr-3" src="RecuperarImagem?codigopro=${TbProduto.codigopro}">
                    <div class="media-body">
                        <div class="row justify-content-center">
                            ${erroQtd}
                            ${erroCo}
                        </div>
                        <h4 class="mt-0">${TbProduto.nomepro}</h4>
                        <h6>Preço:</h6>
                        <h5>${TbProduto.precopro}</h5>
                        <div data-app="product.quantity" id="quantidade">
                            <label>Quantidade:</label>      
                            <input type="button" id="plus" value='-' onclick="process(-1)" />
                            <input id="quant" name="quant" size="1" type="text" value="1" maxlength="5" readonly/>
                            <input type="button" id="minus" value='+' onclick="process(1)">
                        </div>
                        <jsp:useBean id="dao" class="DAO.ProdutoDAO">                       
                            <c:if test="${TbProduto.qtdpro == 0}">
                                <div class="row justify-content-left">
                                    
                                    <div class="alert alert-danger" role="alert">Produto indisponível.</div>
                                    
                                </div>
                            </c:if>
                        </jsp:useBean>
                        
                        <jsp:useBean id="da" class="DAO.ProdutoDAO">                       
                            <c:if test="${TbProduto.qtdpro > 0 && TbProduto.qtdpro < 3}">
                                <div class="row justify-content-left">
                                    <div class="alert alert-warning" role="alert">Ultimas unidades.</div>
                                </div>
                            </c:if>
                        </jsp:useBean>
                        
                        <jsp:useBean id="d" class="DAO.ProdutoDAO">                       
                            <c:if test="${TbProduto.qtdpro >= 3}">
                                <div class="row justify-content-left">
                                    <div class="alert alert-success" role="alert">Produto disponível.</div>
                                </div>
                            </c:if>
                        </jsp:useBean>

                        <div class="section" style="padding-bottom:20px;">
                            <button class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Comprar</button>
                            <input id="codigo" name="codigo" type="hidden" value="${TbProduto.codigopro}">
                        </div>   

                    </div>
                </div>
                <ul class="menu-items">
                    <h5><li class="active">Descrição</li></h5>
                </ul>
                <div style="width:100%;border-top:1px solid silver">
                    <h4><p style="padding:15px;">
                        <small>
                            ${TbProduto.descrpro}

                        </small>
                        </p></h4>
                </div>
            </form>
        </div>
        <br />
        <br />
        <%@include file="estrutura/rodape.jsp"%>
    </body>
</html>
