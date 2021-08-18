$(document).ready(function () {
    $(".form-check-input").click(function () {
        var valores_cat = [];
//        if($(".form-check-input:checked").prop(checked == false)){
//            $("#car").html("");
//            $("#car").append("<h1>Voltou</h1>");
//        }

        $(".form-check-input:checked").each(function (i) {
            valores_cat[i] = $(this).val();
        });


        $.ajax({
            method: "POST",
            url: "filtrarPorCategoria",
            data: {"categorias": valores_cat.toString()},
            dataType: "json",
            success: function (produtos) {
                $("#car").html("");
                for (var i = 0; i < produtos["data"].length; i++) {
                    $("#car").append("\
                    <div class='container'>\n\
                        <div class='row'>\n\
                            <div class='col-lg-9'>\n\
                                <div class='row'>\n\
                                    <div class='col-lg-4 col-md-6 mb-4'>\n\
                                        <div class='card h-100'>\n\
                                            <a href='#'><img id='imagem' class='card-img-top' src='RecuperarImagem?codigopro=" + produtos["data"][i]["codigopro"] + "' alt=''></a>\n\
                                            <div class='card-body'>\n\
                                                <h4 class='card-title'>\n\
                                                    <a href='#'>" + produtos["data"][i]["nomepro"] + "</a>\n\
                                                </h4>\n\
                                                <h5>Preço: " + produtos["data"][i]["precopro"] + "\
                                                </h5>\n\
                                                <p class='card-text'>Quantidade: " + produtos["data"][i]["qtdpro"] + "</p>\n\
                                            </div>\n\
                                            <div class='card-footer'>\n\
                                                <small class='text-muted'>&#9733; &#9733; &#9733; &#9733; &#9734;</small>\n\
                                                <div class='row right'>\n\
                                                    <a class='btn btn-outline-danger' href='comprarProduto?codigopro=" + produtos["data"][i]["codigopro"] +"'>Comprar</a>\n\
                                                </div>\n\
                                            </div>\n\
                                        </div>\n\
                                    </div>\n\
                                </div>\n\
                            </div>\n\
                        </div>\n\
                    </div>"
                            );
                }
            
            }
        });
    });

});