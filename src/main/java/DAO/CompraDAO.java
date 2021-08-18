/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Mapeamento.TbCompra;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author admingbd
 */
public class CompraDAO extends DAOGenerico<TbCompra, BigDecimal>{
    public boolean salvarCompra(TbCompra compra){
        Session s = this.getSession();
        try{
            s.beginTransaction();
            this.save(compra);
            s.getTransaction().commit();
            return true;
        }catch(HibernateException e){
            s.getTransaction().rollback();
            return false;
        }
    }
    
    public List<TbCompra> buscarComp(String cpf){
        Session s = this.getSession();
        List<TbCompra> comp = null;
        
        try{
            s.beginTransaction();
            Query q = s.createQuery(" from TbCompra where tbCliente = :cpf");
            q.setString("cpf", cpf);
            comp = findMany(q);
            for(TbCompra c : comp){
                Hibernate.initialize(c.getIdcomp());
                Hibernate.initialize(c.getTbCliente().getNomecli());
                Hibernate.initialize(c.getTbProduto().getNomepro());
                Hibernate.initialize(c.getTotalcomp());
                Hibernate.initialize(c.getDatacomp());
                Hibernate.initialize(c.getQtdcomp());
            }
            s.close();
        }catch(HibernateException e){
            e.printStackTrace();
            s.close();
        }
        
        return comp;
    }
}
