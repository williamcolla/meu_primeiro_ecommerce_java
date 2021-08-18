/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.ProdutoDAO;
import Mapeamento.TbProduto;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author G09
 */
@Controller
public class ProdutoController {

    @RequestMapping("cadastrarProduto")
    public String retornaCadastroProd(Model model) {
        model.addAttribute("TbProduto", new TbProduto());
        return "cadastrarProduto";
    }

    @RequestMapping("produtos")
    public String retornaProd(Model model) {
        model.addAttribute("TbProduto", new TbProduto());
        return "produtos";
    }

    @RequestMapping("visualizaProduto")
    public String retornaVisualiza(Model model) {
        model.addAttribute("TbProduto", new TbProduto());
        return "visualizaProduto";
    }

    @RequestMapping("editarProd")
    public String retornaEditarProd(Model model, @RequestParam(value = "codigopro", required = false) int codigopro) {
        ProdutoDAO prodDAO = new ProdutoDAO();
        TbProduto produtoEd = prodDAO.buscarCodigo(codigopro);
        
        System.out.println("IMAGEM DO PRODUTO EDITANDO: " + produtoEd.getImagempro());
        model.addAttribute("TbProduto", prodDAO.buscarCodigo(codigopro));

        return "editarProd";
    }

    @RequestMapping("excluirProd")
    public String excluirProd(@ModelAttribute("TbProduto") TbProduto produto, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ProdutoDAO prodDAO = new ProdutoDAO();
        if (prodDAO.deletarProduto(produto)) {
            System.out.println("Produto deletado com sucesso!");
        } else {
            System.out.println("Produto não deletado!");
        }

        return "visualizaProduto";
    }

    @RequestMapping("salvarProduto")
    public String salvarProduto(@RequestParam(value = "file", required = false) MultipartFile file, @ModelAttribute("TbProduto") TbProduto produto, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) throws IOException {

        System.out.println("TESTE");
        System.out.println(produto.getCodigopro());
        System.out.println(produto.getTbCategoria());
        System.out.println(produto.getNomepro());
        System.out.println(produto.getPrecopro());
        System.out.println(produto.getQtdpro());
        System.out.println(produto.getDescrpro());

        Date hoje = new Date();
        produto.setDataPro(hoje);

        double preco = produto.getPrecopro();
        String precoS = String.valueOf(preco);
        BigDecimal precoB = new BigDecimal(precoS);
        double precoF = precoB.doubleValue();

        System.out.println("PRECO FINAL DEPOIS DO BIG DECIMAL: " + precoF);

        produto.setPrecopro(precoF);

        ProdutoDAO prodDAO = new ProdutoDAO();

        if (produto.getCodigopro() == 0) {
            System.out.println("ANTES DA VERIFICACAO DO EDITAR" + file.isEmpty());
            produto.setImagempro(file.getBytes());
            
            if (prodDAO.salvarProduto(produto)) {
                System.out.println("Cadastro efetuado com sucesso!");
                String susCad = "<div class='alert alert-success alert-dismissible fade show' role='alert'><strong>Sucesso!</strong> Produto cadastrado com sucesso!<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>";
                model.addAttribute("susCad", susCad);
            } else {
                System.out.println("Cadastro não efetuado!");
                String erroCad = "<div class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Erro!</strong> Não foi possível cadastrar esse produto, verifque os dados!<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>";
                redirectAttributes.addFlashAttribute("erroCad", erroCad);
                return "redirect:cadastrarProduto";
            }
        } else {
            
            if (file.isEmpty()) {
                TbProduto produtoEd = prodDAO.buscarCodigo(produto.getCodigopro());
                produto.setImagempro(produtoEd.getImagempro());
                System.out.println("IMAGEM NA EDIÇÃO: " + produto.getImagempro());
            } else {

                produto.setImagempro(file.getBytes());

            }

            if (prodDAO.editarProduto(produto)) {
                System.out.println("Edição feita com sucesso!");
                String susEd = "<div class='alert alert-success alert-dismissible fade show' role='alert'><strong>Sucesso!</strong> Produto editado com sucesso!<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>";
                model.addAttribute("susEd", susEd);
            } else {
                System.out.println("Não foi possível editar o produto!");
                String erroEd = "<div class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Erro!</strong> Não foi possível editar esse produto, verifque os dados!<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>";
                redirectAttributes.addFlashAttribute("erroEd", erroEd);
                return "redirect:editarProd";
            }
        }

        return "visualizaProduto";
    }

    @RequestMapping(value = "RecuperarImagem", method = RequestMethod.GET)
    public @ResponseBody
    String exibirImagem(@RequestParam(value = "codigopro") int codigopro, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpg");
        System.out.println("AQUI" + codigopro);
        byte[] buffer = new byte[1024];
        int bytesread = 0;

        ServletOutputStream out = response.getOutputStream();
        InputStream is;

        ProdutoDAO produtoDAO = new ProdutoDAO();
        TbProduto produto = produtoDAO.buscarCodigo(codigopro);

        if (produto.getImagempro() == null) { // Se não tem foto, coloca padrão
            is = request.getSession().getServletContext().getResourceAsStream("produtoSemFoto.png");
            while ((bytesread = is.read(buffer)) != -1) {
                out.write(buffer, 0, bytesread);
            }
        } else { // Se tem foto
            is = new ByteArrayInputStream(produto.getImagempro());
            while ((bytesread = is.read(buffer)) != -1) {
                out.write(buffer, 0, bytesread);
            }
            out.flush();
        }
        out.close();
        is.close();

        return "imagem";
    }

    @RequestMapping("grid")
    public @ResponseBody
    String grid() {
        List<TbProduto> listProd;
        String tmp;
        ProdutoDAO dao = new ProdutoDAO(); // Uso do DAO do Hibernate
        listProd = dao.buscarProd(); // DAO para retornar uma lista de produtos
        JSONArray json = new JSONArray(); // Variável no formato Json
        Map<String, String> data = new HashMap<String, String>();
        for (int i = 0; i < listProd.size(); i++) {
            data.put("codigopro", listProd.get(i).getCodigopro() + "");
            data.put("nomepro", listProd.get(i).getNomepro() + "");
            data.put("tbCategoria", listProd.get(i).getTbCategoria().getNomecat() + "");
            data.put("precopro", listProd.get(i).getPrecopro() + "");
            data.put("qtdpro", listProd.get(i).getQtdpro() + "");
            data.put("descrpro", listProd.get(i).getDescrpro() + "");
            data.put("editar", "<a class='btn btn-outline-primary' href='editarProd?codigopro=" + listProd.get(i).getCodigopro() + "'>Editar</a>"
                    + " <a class='btn btn-outline-danger' href='excluirProd?codigopro=" + listProd.get(i).getCodigopro() + "'>Excluir</a>");
            json.put(data); //Formata os dados no padrão do Json
        }

        tmp = "{\"data\":" + json.toString() + "}";
        return tmp;
    }

    @RequestMapping("filtrarPorCategoria")
    public @ResponseBody
    String filtrarPorCategoria(@RequestParam(value = "categorias") String categorias) {
        List<TbProduto> produtos;
        ProdutoDAO dao = new ProdutoDAO();
        if (categorias == "") {
            produtos = dao.buscarProd();
            System.out.println("Cateorias quando é nula: " + categorias);
        } else {
            produtos = dao.buscarProdCat(categorias);
            System.out.println("Categorias quando não é nula: " + categorias);
        }

        String tmp;
        JSONArray json = new JSONArray(); // Variável no formato Json
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < produtos.size(); i++) {
            data.put("codigopro", produtos.get(i).getCodigopro() + "");
            data.put("nomepro", produtos.get(i).getNomepro() + "");
            data.put("tbCategoria", produtos.get(i).getTbCategoria().getNomecat() + "");
            data.put("precopro", produtos.get(i).getPrecopro() + "");
            data.put("qtdpro", produtos.get(i).getQtdpro() + "");
            data.put("descrpro", produtos.get(i).getDescrpro() + "");

            json.put(data); //Formata os dados no padrão do Json
        }

        tmp = "{\"data\":" + json.toString() + "}";
        return tmp;
    }

}
