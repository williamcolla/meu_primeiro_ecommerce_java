/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.ProdutoDAO;
import Mapeamento.TbProduto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author admingbd
 */


@Controller
public class IndexController {
    @RequestMapping("index")
    public String retornaIndex(Model model){
        String str = "Hello, World";
        model.addAttribute("str", str);
     
        return "index";
    }
    
    @RequestMapping("buscarProd")
    public String buscarProd(Model model){
        ProdutoDAO daoProd = new ProdutoDAO();
        List<TbProduto> ultProd = daoProd.buscarProd();
        
        model.addAttribute("UltimosProdutos", ultProd);
        
        return "index";
    }
}
