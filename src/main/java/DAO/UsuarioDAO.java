/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Mapeamento.TbCliente;
import java.math.BigDecimal;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author admingbd
 */
public class UsuarioDAO extends DAOGenerico<TbCliente, BigDecimal>{
    public TbCliente buscarLogin(TbCliente cliente){
        Session s = this.getSession();
        TbCliente user = null;
        
        try{
            s.beginTransaction();
            Query q = s.createQuery(" from TbCliente where emailcli = :emailcli and senhacli = :senhacli");
            q.setString("emailcli", cliente.getEmailcli());
            q.setString("senhacli", cliente.getSenhacli());
            user = findOne(q);
            s.close();
        }catch(HibernateException e){
            e.printStackTrace();
            s.close();
        }
        
        return user;
    }
    
    public TbCliente buscarEmail(TbCliente cliente){
        Session s = this.getSession();
        TbCliente user = null;
        
        try{
            s.beginTransaction();
            Query q = s.createQuery(" from TbCliente where emailcli = :emailcli");
            q.setString("emailcli", cliente.getEmailcli());
            user = findOne(q);
            s.close();
        }catch(HibernateException e){
            e.printStackTrace();
            s.close();
        }
        
        return user;
    }
    
    
    public TbCliente buscarCpf(TbCliente cliente){
        Session s = this.getSession();
        TbCliente user = null;
        
        try{
            s.beginTransaction();
            Query q = s.createQuery(" from TbCliente where cpf = :cpf");
            q.setString("cpf", cliente.getCpf());
            user = findOne(q);
            s.close();
        }catch(HibernateException e){
            e.printStackTrace();
            s.close();
        }
        
        return user;
    }
    
    public TbCliente buscarCpf1(String cpf){
        Session s = this.getSession();
        TbCliente user = null;
        
        try{
            s.beginTransaction();
            Query q = s.createQuery(" from TbCliente where cpf = :cpf");
            q.setString("cpf", cpf);
            user = findOne(q);
            s.close();
        }catch(HibernateException e){
            e.printStackTrace();
            s.close();
        }
        
        return user;
    }
    
    public boolean salvarUsuario(TbCliente cliente) {
        Session s = this.getSession();
        try{
            s.beginTransaction();
            this.save(cliente);
            s.getTransaction().commit();
            return true;
        }catch(HibernateException e){
            s.getTransaction().rollback();
            return false;
        }
    }
    
    public boolean editarUsuario(TbCliente cliente) {
        Session s = this.getSession();
        try{
            s.beginTransaction();
            this.merge(cliente);
            s.getTransaction().commit();
            return true;
        }catch(HibernateException e){
            s.getTransaction().rollback();
            return false;
        }
    }
}
