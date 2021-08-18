<%-- 
    Document   : visualizaProduto
    Created on : 05/08/2019, 16:43:58
    Author     : admingbd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="estrutura/importBot.jsp"%>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>


        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet">
        <style type="text/css">
            .tamanho{
                width: auto;
            }
            .conteudo{
                margin-bottom: 50px !important;
            }
        </style>

        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.mask.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#produtos').dataTable({
                    "language": {
                        "paginate": {
                            "next": "Próximo",
                            "previous": "Anterior"
                        },
                        "lengthMenu": "Mostrar _MENU_ produtos",
                        "zeroRecords": "Nenhum produto encontrado - desculpe",
                        "info": "Mostrando página _PAGE_ de _PAGES_",
                        "infoEmpty": "Nenhum registro disponível",
                        "infoFiltered": "(filtrado um total de _MAX_ registros)"
                    },
                    "pagingtype": "full_numbers",
                    "ajax": "grid",
                    "columns": [
                        {"data": "codigopro"},
                        {"data": "nomepro"},
                        {"data": "tbCategoria"},
                        {"data": "precopro"},
                        {"data": "qtdpro"},
                        {"data": "descrpro"},
                        {"data": "editar"}

                    ]
                });
            });
        </script>
        <title>Visualizar Produtos</title>
        <link rel="shortcut icon" href="resources/eco.ico">
    </head>
    <body>
        <%@include file="estrutura/cabecalho.jsp"%>
        <br />
        <div class="row justify-content-center">
            ${susCad}   
            ${susEd} 
        </div>
        <div class="conteudo">
        <table id="produtos" class="table table-striped table-bordered tamanho" style="width:100%">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Categoria</th>
                    <th>Preço</th>
                    <th>Quantidade</th>
                    <th>Descrição</th>
                    <th>Editar/Excluir</th>
                </tr>
            </thead>
        </table> 
        </div>
        <br />
    <%@include file="estrutura/rodape.jsp"%> 
</body>
</html>
