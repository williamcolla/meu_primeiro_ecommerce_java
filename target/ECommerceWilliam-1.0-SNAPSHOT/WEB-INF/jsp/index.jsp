<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
    <head>
        <%@include file="estrutura/importBot.jsp"%>
        <style type="text/css">
            #imagem{
                max-width: 500%;
                height: 200px;
            }

            #image{
                width: 900px;
                height: 321px;
            }

            .tamanho{
                width: auto;
            }

            h6, p{
                color: black;
            }
        </style>
        <script src="<%=request.getContextPath()%>/resources/js/produtos.js"></script>
        <title>E-Commerce</title>
        <link rel="shortcut icon" href="resources/eco.ico">
    </head>

    <body>

        <%@include file="estrutura/cabecalho.jsp"%>
        <div class="container tamanho">
            <div class="row ">
                <div class="col-lg-3">

                    <h1 class="my-4">Nossas Categorias de Produtos</h1>
                    <a href="produtos"><p>Clique aqui para ser direcionado para o filtro das categorias</p></a>

                    <jsp:useBean id="dao1" class="DAO.CategoriaDAO">
                        <c:forEach var="categoria" items="${dao1.buscarCat()}">
                            <div  class="list-group-item">
                                
                                <c:out escapeXml="false" value="${categoria.nomecat}"></c:out>   

                                </div>                               
                        </c:forEach>
                    </jsp:useBean>
                    <!--a href="#" class="list-group-item">Category 2</a-->


                </div>
                <!-- /.col-lg-3 -->


                <div class="col-lg-9">

                    <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner" role="listbox">
                            <div class="carousel-item active">
                                <jsp:useBean id="da" class="DAO.ProdutoDAO">
                                    <c:forEach var="prod" items="${da.buscarProdData()}" varStatus="indice">
                                        <c:if test="${indice.index == 0}">
                                            <a href="comprarProduto?codigopro=${prod.codigopro}"><img id="image" class="d-block img-fluid" src="RecuperarImagem?codigopro=${prod.codigopro}" alt="First slide"></a>
                                            <div class="carousel-caption d-none d-md-block">
                                                <h6>Preço</h6>
                                                <p>${prod.precopro}</p>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </jsp:useBean>                             
                            </div>

                            <div class="carousel-item">
                                <jsp:useBean id="daoo" class="DAO.ProdutoDAO">
                                    <c:forEach var="pro" items="${daoo.buscarProdData()}" varStatus="indi">
                                        <c:if test="${indi.index == 1}">
                                            <a href="comprarProduto?codigopro=${pro.codigopro}"><img id="image" class="d-block img-fluid" src="RecuperarImagem?codigopro=${pro.codigopro}" alt="Second slide"></a>
                                            <div class="carousel-caption d-none d-md-block">
                                                <h6>Preço</h6>
                                                <p>${pro.precopro}</p>
                                            </div></c:if>
                                    </c:forEach>
                                </jsp:useBean>
                            </div>
                            <div class="carousel-item">
                                <jsp:useBean id="d" class="DAO.ProdutoDAO">
                                    <c:forEach var="produ" items="${d.buscarProdData()}" varStatus="ind">
                                        <c:if test="${ind.index == 2}">
                                            <a href="comprarProduto?codigopro=${produ.codigopro}"><img id="image" class="d-block img-fluid" src="RecuperarImagem?codigopro=${produ.codigopro}" alt="Third slide"></a>    
                                            <div class="carousel-caption d-none d-md-block">
                                                <h6>Preço</h6>
                                                <p>${produ.precopro}</p>
                                            </div>
                                        </c:if>
                                        </c:forEach>
                                    </jsp:useBean>  
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                    <br />

                    <div class="row justify-content-left">
                        <jsp:useBean id="daooo" class="DAO.ProdutoDAO">
                            <c:forEach var="produto" items="${daooo.buscarProd()}">
                                <div class="col-lg-4 col-md-6 mb-4">
                                    <div class="card h-100">
                                        <a href="comprarProduto?codigopro=${produto.codigopro}"><img id="imagem" class="card-img-top" src="RecuperarImagem?codigopro=${produto.codigopro}" alt=""></a>
                                        <div class="card-body">
                                            <h4 class="card-title">
                                                <a href="comprarProduto?codigopro=${produto.codigopro}">
                                                    <c:out escapeXml="false" value="${produto.nomepro}"></c:out>
                                                    </a>
                                                </h4>
                                                <h5>R$: 
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



                </div>
                <!-- /.col-lg-9 -->

            </div>
            <!-- /.row -->

        </div>
        <br /><br />


        <%@include file="estrutura/rodape.jsp"%>   

        <!--script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script-->
    </body>

</html>
