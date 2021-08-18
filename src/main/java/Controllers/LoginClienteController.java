/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.UsuarioDAO;
import Mapeamento.TbCliente;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author admingbd
 */
@Controller
public class LoginClienteController {

    @RequestMapping("loguin")
    public String retornaLogin(Model model) {
        model.addAttribute("TbCliente", new TbCliente());
        return "loguin";
    }

    @RequestMapping("efetuaLogin")
    public String efetuaLogin(Model model, @ModelAttribute("TbCliente") TbCliente cliente, BindingResult result, HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        /*if(result.hasErrors()){
            // Seta o email da ultima tentativa
            redirectAttributes.addFlashAttribute("EmailTentativa", cliente.getEmailcli());
            // Seta erros para redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usuarios", result);
            
            return "redirect:" + request.getHeader("Referer");
        }*/
        System.out.println("VENDO O QUE SERA ESCRITO NOS CAMPOS EMAIL E SENHA");
        System.out.println(cliente.getEmailcli());
        System.out.println(cliente.getSenhacli());
        System.out.println("FIM DO TESTE!");

        UsuarioDAO dao = new UsuarioDAO();
        TbCliente user = dao.buscarLogin(cliente);


        if (user != null) {
            // Remove caso haja na sessao
            session.removeAttribute("usuarioLogado");
            // Seta a sessao do usuario
            session.setAttribute("usuarioLogado", user);

            // Seta o sucesso de login
            System.out.println("Loguin efetuado com sucesso!");
            String sus = "<div class='alert alert-success alert-dismissible fade show' role='alert'><strong>Sucesso!</strong> Login realizado com sucesso!<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>";
            // Redireciona para a página anterior
            return "redirect: index";
            
        } else {
            // Seta o erro de login
            System.out.println("Erro no login, email ou senha incorretos");
            String erro = "<small id='emailHelp' class='form-text text-muted'>E-mail ou senha errados!</small>";
            model.addAttribute("erro", erro);
            // Seta o email da ultima tentativa
            System.out.println("EmailTentativa " + cliente.getEmailcli());
            System.out.println("SenhaTentativa " + cliente.getSenhacli());
            return "loguin";
        }
    }

    @RequestMapping("efetuaLogout")
    public String efetuaLogout(@ModelAttribute("TbCliente") TbCliente cliente, BindingResult result, HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        session.removeAttribute("usuarioLogado");
        return "index";
    }
}

