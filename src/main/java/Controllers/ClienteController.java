/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.UsuarioDAO;
import Mapeamento.HibernateUtil;
import Mapeamento.TbCliente;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.validation.Valid;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author admingbd
 */
@Controller
public class ClienteController {

    @RequestMapping("cadastrar")
    public String retornaCadastro(Model model) {
        model.addAttribute("TbCliente", new TbCliente());
        return "cadastrar";
    }

    @RequestMapping("meusDados")
    public String retornaDados(Model model, @RequestParam(value = "cpf", required = false) String cpf) {
        UsuarioDAO useDAO = new UsuarioDAO();
        model.addAttribute("TbCliente", useDAO.buscarCpf1(cpf));

        return "meusDados";
    }

    @RequestMapping("salvarUsuario")
    public String salvarUsuario(Model model, @ModelAttribute("TbCliente") TbCliente cliente, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        //if (cliente.getCpf() == 0) {
        //System.out.println("É obrigatório digitar um CPF!");
        System.out.println("entrou");
        System.out.println("Vendo o que sera passado");
        System.out.println(cliente.getNomecli());
        System.out.println(cliente.getCpf());
        System.out.println(cliente.getEmailcli());
        System.out.println(cliente.getSenhacli());
        System.out.println(cliente.getRuacli());
        System.out.println(cliente.getNumerocli());
        System.out.println(cliente.getBairrocli());
        System.out.println(cliente.getCidadecli());
        System.out.println(cliente.getEstadocli());
        System.out.println(cliente.getTipo());
        System.out.println("FIM DOS TESTES");

        UsuarioDAO userDAO = new UsuarioDAO();
        TbCliente user = userDAO.buscarCpf(cliente);
        TbCliente user1 = userDAO.buscarEmail(cliente);

        if (user1 != null) {
            System.out.println("Email ja cadastrado no banco");
            return "redirect: cadastrar";
        }

        if (user != null) {
            System.out.println("Cpf ja cadastrado no banco");
            return "redirect: cadastrar";
        }

        /*if(user != null){
            System.out.println("Usuário logado!");
            return "redirect: cadastrar";
        }
        
        else{
            System.out.println("CPF já cadastrado no banco!");
            return "redirect: cadastar";
        }*/
        if (userDAO.buscarLogin(cliente) != null) {
            System.out.println("Erro no cadastro!");
            return "redirect:cadastrar";
        }

        if (userDAO.salvarUsuario(cliente)) {
            System.out.println("Cadastro efetuado com sucesso!");
            String status = "<div class='alert alert-success alert-dismissible fade show' role='alert'><strong>Sucesso!</strong> Faça o seu login agora.<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>";
            model.addAttribute("status", status);
        } else {
            System.out.println("Cadastro não efetuado!");
        }

        return "loguin";
    }

    @RequestMapping("editarUsuario")
    public String editarUsuario(@ModelAttribute("TbCliente") TbCliente cliente, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session) {

        //if (cliente.getCpf() == 0) {
        //System.out.println("É obrigatório digitar um CPF!");
        System.out.println("entrou");
        System.out.println("Vendo o que sera passado");
        System.out.println(cliente.getNomecli());
        System.out.println(cliente.getCpf());
        System.out.println(cliente.getEmailcli());
        System.out.println(cliente.getSenhacli());
        System.out.println(cliente.getRuacli());
        System.out.println(cliente.getNumerocli());
        System.out.println(cliente.getBairrocli());
        System.out.println(cliente.getCidadecli());
        System.out.println(cliente.getEstadocli());
        System.out.println(cliente.getTipo());
        System.out.println("FIM DOS TESTES");

        UsuarioDAO userDAO = new UsuarioDAO();
        TbCliente user1 = userDAO.buscarEmail(cliente);

        /*if(user != null){
            System.out.println("Usuário logado!");
            return "redirect: cadastrar";
        }
        
        else{
            System.out.println("CPF já cadastrado no banco!");
            return "redirect: cadastar";
        }*/
        
        if (cliente.getTipo() == true) {
            if (userDAO.editarUsuario(cliente)) {
                cliente.setTipo(true);
                System.out.println("Cadastro efetuado com sucesso!");
                session.removeAttribute("usuarioLogado");
                TbCliente user2 = userDAO.buscarCpf(cliente);
                session.setAttribute("usuarioLogado", user2);

            } else {
                System.out.println("Cadastro não efetuado!");
            }
        }
        else{
            if (userDAO.editarUsuario(cliente)) {
                cliente.setTipo(false);
                System.out.println("Cadastro efetuado com sucesso!");
                session.removeAttribute("usuarioLogado");
                TbCliente user2 = userDAO.buscarCpf(cliente);
                session.setAttribute("usuarioLogado", user2);

            } else {
                System.out.println("Cadastro não efetuado!");
            }
        }

        return "index";
    }
}
