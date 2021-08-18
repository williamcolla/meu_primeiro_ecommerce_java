
<!DOCTYPE html>
<html>
    <head>
        <%@include file="estrutura/importBot.jsp"%>
        <link href="<%=request.getContextPath()%>/resources/css/login.css" rel="stylesheet">

        <title>Login</title>
        <link rel="shortcut icon" href="resources/eco.ico">

        <script type="text/javascript">
            $(document).ready(function () {
                $("#formLogin").submit(function (e) {

                    if ($("#email").val() == " ") {
                        alert("Digite um e-mail!");
                        e.preventDefault();
                    }
                    if ($("#senha").val() == " ") {
                        alert("Digite uma senha!");
                        e.preventDefault();          
                });
            });
        </script>
    </head>
    <body>
        <%@include file="estrutura/cabecalho.jsp"%>
        <form:form id="formLogin" action="efetuaLogin" commandName="TbCliente" method="POST">
            <!--Erro no login-->
            <c:if test="${LoginErro.isMensagem()}">
                <div class="alert alert-danger">
                    <h4>${LoginErro.getTituloMensagem()}</h4>
                    ${LoginErro.getTextMensagem()}
                </div>
            </c:if>
            <div id="login">
                <div class="container">
                    <div id="login-row" class="row justify-content-center align-items-center">
                        <div id="login-column" class="col-md-6">                         


                            <div id="login-box" class="col-md-12">
                                <form id="login-form" class="form" action="" method="post">
                                    <h3 class="text-center text-info">Login</h3>
                                    <br />
                                    <div class="row justify-content-center">
                                        ${status}
                                    </div>
                                    <div>${logar}</div>
                                    <div class="form-group">
                                        <form:input path="emailcli" type="email" class="form-control" id="email" name="email" placeholder="E-mail" value="${EmailTentativa}" required="required" data-tootle="tooltip"></form:input>
                                        </div>
                                        <br />
                                        <div class="form-group">
                                        <form:input id="senha" path="senhacli" type="password" class="form-control" name="senha" autocomplete="off" placeholder="Senha" required="required" data-tootle="tooltip"></form:input>
                                        </div>
                                        <div>${erro}</div>
                                        
                                        <div>
                                            <a href="cadastrar"><button type="button" class="btn btn-link">Não possui um cadastro? Clique aqui.</button></a>
                                        </div>

                                    <div id="register-link" class="text-right">

                                        <button class="btn btn-outline-info"  type="submit">Entrar</button>


                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
        <br />
        <br />
        <br />
        <br />
        <%@include file="estrutura/rodape.jsp"%>
    </body>
</html>


