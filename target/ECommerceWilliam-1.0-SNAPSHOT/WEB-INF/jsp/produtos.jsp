<%-- 
    Document   : buscar
    Created on : 10/06/2019, 16:51:15
    Author     : admingbd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="estrutura/importBot.jsp"%>
        <style type="text/css">
            #imagem{
                max-width: 500%;
                height: 200px;
            }
        </style>
         <script src="<%=request.getContextPath()%>/resources/js/produtos.js"></script>
        <title>E-Commerce</title>
        <link rel="shortcut icon" href="resources/eco.ico">
    </head>
    <body>
        <%@include file="estrutura/cabecalho.jsp"%>
        <br />
        <div class="container">

            <div class="row">

                <div class="col-lg-3">

                    <h1 class="my-4">Categorias</h1>
                    
                        <jsp:useBean id="da" class="DAO.CategoriaDAO">
                            <c:forEach var="categoria" items="${da.buscarCat()}">
                                <div  class="list-group-item">
                                           &nbsp<input type="checkbox" class="form-check-input" value="${categoria.idcat}">
                                            <c:out escapeXml="false" value="${categoria.nomecat}"></c:out>   
                                            
                                </div>                               
                            </c:forEach>
                        </jsp:useBean>
                        <!--a href="#" class="list-group-item">Category 2</a-->
                   

                </div>
                <!-- /.col-lg-3 -->

                <div id="car" class="col-lg-9">



                    <div class="row">

                        <jsp:useBean id="dao" class="DAO.ProdutoDAO">
                            <c:forEach var="produto" items="${dao.buscarProd()}">                             
                                    <div class="col-lg-4 col-md-6 mb-4">
                                        <div class="card h-100">
                                            <a href="#"><img id="imagem" class="card-img-top" src="RecuperarImagem?codigopro=${produto.codigopro}" alt=""></a>
                                            <div class="card-body">
                                                <h4 class="card-title">
                                                    <a href="#">
                                                        <c:out escapeXml="false" value="${produto.nomepro}"></c:out>
                                                        </a>
                                                    </h4>
                                                    <h5>Preço: 
                                                    <c:out escapeXml="false" value="${produto.precopro}"></c:out>
                                                    </h5>
                                                </div>
                                                <div class="card-footer">
                                                    <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                                                    <div class="row right">
                                                        <a class="btn btn-outline-danger" href="comprarProduto?codigopro=${produto.codigopro}">Comprar</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                            </c:forEach>
                        </jsp:useBean>

                    </div>
                    <!-- /.row -->

                </div>
                <!-- /.col-lg-9 -->

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->
        <br /><br />

        <%@include file="estrutura/rodape.jsp"%> 
    </body>
</html>
