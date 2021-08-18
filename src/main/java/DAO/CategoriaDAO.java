/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Mapeamento.TbCategoria;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author admingbd
 */
public class CategoriaDAO extends DAOGenerico<TbCategoria, BigDecimal>{
    public List<TbCategoria> buscarCat(){
        Session s = this.getSession();
        List<TbCategoria> cat = null;
        
        try{
            s.beginTransaction();
            Query q = s.createQuery(" from TbCategoria");

            cat = findMany(q);
            s.close();
        }catch(HibernateException e){
            e.printStackTrace();
            s.close();
        }
        
        return cat;
    }    
}
