
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!------ Include the above in your HEAD tag ---------->



<!--div class="span4 offset2" style="margin-top:15px;">
<!--button class="btn pull-right" type="button">Login</button> -->
<!--a class="btn pull-right" type="button" href="login">Login</a>
<a class="btn pull-left" type="button" href="cadastrar">Cadastrar</a>
<!--input class="btn pull-right" type="button" onclick="window.open('/WEB-INF/jsp/loguin.jsp')" value="Login" -->
<!--/div-->


<!-- Navigation -->
<div class="fixed-top">
    <header class="topbar">
        <div class="container">
            <div class="row">
                <!-- social icon-->
                <div class="col-sm-12">
                    <ul class="social-network">
                        <li><a class="waves-effect waves-dark" href="https://pt-br.facebook.com/" target="_blank"><i class="fa fa-facebook"></i></a></li>
                        <li><a class="waves-effect waves-dark" href="https://twitter.com/login?lang=pt" target="_blank"><i class="fa fa-twitter"></i></a></li>
                        <li><a class="waves-effect waves-dark" href="https://br.linkedin.com/" target="_blank"><i class="fa fa-linkedin"></i></a></li>
                        <li><a class="waves-effect waves-dark" href="https://br.pinterest.com/" target="_blank"><i class="fa fa-pinterest"></i></a></li>
                    </ul>
                </div>               
            </div>
        </div>
    </header>
    <nav class="navbar navbar-expand-lg navbar-dark mx-background-top-linear">
        <div class="container col-md-12">
            <a class="navbar-brand" href="index">E-Commerce William</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">

                <ul class="navbar-nav ml-auto">

                    <li class="nav-item active">
                        <a class="nav-link" href="index">Página Inicial
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link" href="produtos">Produtos</a>
                    </li>

                    <c:if test="${sessionScope.usuarioLogado.tipo == true}">
                        <li class="nav-item">
                            <a class="nav-link" href="cadastrarProduto">Cadastrar Produtos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="visualizaProduto">Visualizar Produtos</a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.usuarioLogado != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="visualizaCompra">Histórico de Compras</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="meusDados?cpf=${usuarioLogado.cpf}">Meus dados</a>
                        </li>
                    </c:if>

                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                        <c:if test="${sessionScope.usuarioLogado.nomecli == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="cadastrar">Cadastrar</a>
                        </li>                      
                    </c:if>

                    <li class="nav-item">

                        <c:if test="${sessionScope.usuarioLogado.nomecli == null || sessionScope.usuarioLogado.nomecli == ''}">
                            <div style="color: greenyellow">
                                <a class="nav-link" href="loguin">Efetue o login</a>
                            </div>
                        </c:if>
                    </li>
                    <li class="nav-item">

                        <c:if test="${sessionScope.usuarioLogado.nomecli != null}">
                            <div style="color: greenyellow; text-align: right">
                                Bem vindo, ${sessionScope.usuarioLogado.nomecli}!

                            </div>
                        </c:if>
                    </li>
                    <c:if test="${sessionScope.usuarioLogado.nomecli != null}">
                        <li>

                            <div>
                                <form:form action="efetuaLogout" commandName="TbCliente" method="POST">
                                    <button type="submit" class="btn btn-link">Sair</button>
                                </form:form>
                            </div>

                        </li>
                    </c:if>



                </ul>
            </div>
        </div>
    </nav>
</div> 


