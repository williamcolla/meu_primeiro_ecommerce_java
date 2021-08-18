/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.CompraDAO;
import DAO.ProdutoDAO;
import DAO.UsuarioDAO;
import Mapeamento.TbCliente;
import Mapeamento.TbCompra;
import Mapeamento.TbProduto;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author GBD
 */
@Controller
public class CompraController {

    @RequestMapping("comprarProduto")
    public String retornaCadastroProd(Model model, @RequestParam(value = "codigopro") int codigo) {
        ProdutoDAO prodDAO = new ProdutoDAO();
        model.addAttribute("TbCliente", new TbCliente());
        model.addAttribute("TbProduto", prodDAO.buscarCodigo(codigo));
        return "comprarProduto";
    }

    @RequestMapping("visualizaCompra")
    public String retornaVisualizaCompra(Model model) {
        model.addAttribute("TbCompra", new TbCompra());
        return "visualizaCompra";
    }

    @RequestMapping("salvarCompra")
    public String salvarCompra(@RequestParam(value = "quant") int quant, @RequestParam(value = "codigo") int codigo, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model) throws IOException {

        System.out.println("TESTE");
        System.out.println("Id da compra " + codigo);
        System.out.println("Quantidade comprada " + quant);
        TbCompra compra = new TbCompra();

        Date hoje = new Date();
        compra.setDatacomp(hoje);

        ProdutoDAO prodDAO = new ProdutoDAO();
        TbProduto prod = prodDAO.buscarCodigo(codigo);
        compra.setTbProduto(prod);

        TbCliente cli = (TbCliente) session.getAttribute("usuarioLogado");

        int qtd = prod.getQtdpro(); // Pegando a quantidade que está no banco do produto
        int qtdAtu = qtd - quant;
        System.out.println("QUANTIDADE DO PRODUTO NO BANCO " + qtd);
        System.out.println("QUANTIDADE DEPOIS QUE O USUARIO ESCOLHEU A QUANTIDADE PARA COMPRAR " + qtdAtu);

        double total = quant * prod.getPrecopro(); // Calculando o preço total da compra
        System.out.println("TOTAL ANTES DAS CONVERSÕES: " + total);
        String totalS = String.valueOf(total);
        BigDecimal totalB = new BigDecimal(totalS);
        System.out.println("TOTAL DA COMPRA COM O BIGDECIMAL: " + totalB);
        double totalF = totalB.doubleValue();
        
        System.out.println("TOTAL DA COMPRA DEPOIS DO BIGDECIMAL: " + totalF);
                
        compra.setQtdcomp(quant);

        compra.setTotalcomp(totalF);

        compra.setTbCliente(cli);

        CompraDAO compDAO = new CompraDAO();        
        
        if (cli == null) {
            System.out.println("Logue para realizar uma compra");
            String logar = "<div class='alert alert-success alert-dismissible fade show' role='alert'><strong>Espera ai!</strong> Faça o seu login para comprar algo!<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>";
            redirectAttributes.addFlashAttribute("logar", logar);
            return "redirect:loguin";
        } else {

            if (quant > qtd || quant  <= 0) {
                System.out.println("Quantidade comprada maior que o estoque");
                String erroQtd = "<div class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Erro!</strong> Quantidade do produto maior do que possuímos no estoque.<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>";
                redirectAttributes.addFlashAttribute("erroQtd", erroQtd);
                return "redirect:comprarProduto?codigopro=" + codigo;
            } else {
                prod.setQtdpro(qtdAtu);
                prodDAO.editarProduto(prod);
                if (compDAO.salvarCompra(compra)) {
                    System.out.println("Campra efetuada com sucesso!");
                    String susCo = "<div class='alert alert-success alert-dismissible fade show' role='alert'><strong>Sucesso!</strong> Compra realizada com sucesso!<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>";
                    model.addAttribute("susCo", susCo);
                    return "visualizaCompra";
                } else {
                    System.out.println("Compra não efetuada!");
                    String erroCo = "<div class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Erro!</strong> Não foi possível realizar a compra.<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>";
                    redirectAttributes.addFlashAttribute("erroCo", erroCo);
                    return "redirect:comprarProduto";
                }
            }
        }
    }

    @RequestMapping("gridd")
    public @ResponseBody
    String gridd(HttpSession session) {
        List<TbCompra> listComp;
        String tmp;
        CompraDAO dao = new CompraDAO(); // Uso do DAO do Hibernate
//        listComp = dao.buscarComp(); // DAO para retornar uma lista de produtos
        TbCliente cli = (TbCliente) session.getAttribute("usuarioLogado");
        listComp = dao.buscarComp(cli.getCpf());
        JSONArray json = new JSONArray(); // Variável no formato Json
        Map<String, String> data = new HashMap<String, String>();
        for (int i = 0; i < listComp.size(); i++) {
            data.put("idcomp", listComp.get(i).getIdcomp() + "");
            data.put("tbCliente", listComp.get(i).getTbCliente().getNomecli() + "");
            data.put("tbProduto", listComp.get(i).getTbProduto().getNomepro() + "");
            data.put("totalcomp", listComp.get(i).getTotalcomp() + "");
            data.put("datacomp", listComp.get(i).getDatacomp() + "");
            data.put("qtdcomp", listComp.get(i).getQtdcomp() + "");
//            data.put("tbProduto", listComp.get(i).getTbProduto().getPrecopro() + "");

            json.put(data); //Formata os dados no padrão do Json
        }

        tmp = "{\"data\":" + json.toString() + "}";
        return tmp;
    }

}
