<%-- 
    Document   : visualizaCompra
    Created on : 15/08/2019, 17:29:13
    Author     : G04
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
        <title>Visualizar Produtos</title>
        
        <script type="text/javascript">
            $(document).ready(function () {             
                $('#compras').dataTable({
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
                    "ajax": "gridd",
                    "columns": [
                        {"data": "tbCliente"},
                        {"data": "tbProduto"},
                        {"data": "totalcomp"},
                        {"data": "datacomp"},
                        {"data": "qtdcomp"}

                    ]
                });
            });
            $("#preco").mask("###0.00", {reverse: true});
        </script>
        
    </head>
    <body>
<%@include file="estrutura/cabecalho.jsp"%>
        <br />
        <div class="row justify-content-center">
            ${susCo}
        </div>
        <table id="compras" class="table table-striped table-bordered tamanho">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Produto</th>
                    <th id="preco">Total da Compra</th>
                    <th>Data Comprada</th>
                    <th>Quantidade Comprada</th>
                </tr>
            </thead>
        </table> 
        <br />
    <%@include file="estrutura/rodape.jsp"%> 
    </body>
</html>
