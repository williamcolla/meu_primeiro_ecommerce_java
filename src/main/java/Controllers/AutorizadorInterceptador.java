/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Mapeamento.TbCliente;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author admingbd
 */
/*public class AutorizadorInterceptador extends HandlerInterceptorAdapter {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
        String uri = request.getRequestURI();
        String url_absoluta = request.getContextPath();
        
        TbCliente user = (TbCliente) request.getSession().getAttribute("usuarioLogado");
        
        if(user == null) {
            if(uri.startsWith(url_absoluta + "/user/")) {
                response.sendRedirect(url_absoluta + "/login");
                return false;
            }
        }
        
        return true;
    }
}*/
