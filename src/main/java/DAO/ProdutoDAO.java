/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Mapeamento.TbProduto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author admingbd
 */
public class ProdutoDAO extends DAOGenerico<TbProduto, BigDecimal>{
    public boolean salvarProduto(TbProduto produto){
        Session s = this.getSession();
        try{
            s.beginTransaction();
            this.save(produto);
            s.getTransaction().commit();
            return true;
        }catch(HibernateException e){
            s.getTransaction().rollback();
            return false;
        }
    }
    
    public boolean editarProduto(TbProduto produto){
        Session s = this.getSession();
        try{
            s.beginTransaction();
            this.merge(produto);
            s.getTransaction().commit();
            return true;
        }catch(HibernateException e){
            s.getTransaction().rollback();
            return false;
        }
    }
    
    public TbProduto buscarCodigo(int codigoprod){
        Session s = this.getSession();
        TbProduto prod = null;
        
        try{
            s.beginTransaction();
            Query q = s.createQuery(" from TbProduto where codigopro = :codigopro");
            q.setInteger("codigopro", codigoprod);
            prod = findOne(q);
            s.close();
        }catch(HibernateException e){
            e.printStackTrace();
            s.close();
        }
        
        return prod;
    } 
    
    public List<TbProduto> buscarProd(){
        Session s = this.getSession();
        List<TbProduto> prod = null;
        
        try{
            s.beginTransaction();
            Query q = s.createQuery(" from TbProduto");
            prod = findMany(q);
            for(TbProduto p : prod){
                Hibernate.initialize(p.getCodigopro());
                Hibernate.initialize(p.getNomepro());
                Hibernate.initialize(p.getTbCategoria().getNomecat());
                Hibernate.initialize(p.getPrecopro());
                Hibernate.initialize(p.getQtdpro());
                Hibernate.initialize(p.getDescrpro());
            }
            s.close();
        }catch(HibernateException e){
            e.printStackTrace();
            s.close();
        }
        
        return prod;
    }
    
    public List<TbProduto> buscarProdCat(String categorias){
        Session s = this.getSession();
        List<TbProduto> produtos = null;
        List<String> list = new ArrayList (Arrays.asList(categorias.split(",")));
        List<Integer> cats = new ArrayList<Integer>() ;
        for (String str : list){
            System.out.println("teste: "+str);
            cats.add(Integer.parseInt(str));        
        }
        try{
            s.beginTransaction(); 
            Query q = s.createQuery("from TbProduto where tbCategoria.idcat in (:categorias)");
            q.setParameterList("categorias", cats);
            produtos = findMany(q);
            for(TbProduto p:produtos){
                System.out.println("Nome: " + p.getNomepro() + " Categoria: " + p.getTbCategoria().getNomecat());
            }
            s.close();
            
        }catch(HibernateException e){
            e.printStackTrace();
            s.close();
        }
        return produtos;
    }
    
    public List<TbProduto> buscarProdData(){
        Session s = this.getSession();
        List<TbProduto> prod = null;
        
        try{
            s.beginTransaction();
            Query q = s.createQuery(" from TbProduto order by prodda desc");
            q.setMaxResults(3);
            prod = findMany(q);
            for(TbProduto p : prod){
                Hibernate.initialize(p.getCodigopro());
                Hibernate.initialize(p.getNomepro());
                Hibernate.initialize(p.getTbCategoria().getNomecat());
                Hibernate.initialize(p.getPrecopro());
                Hibernate.initialize(p.getQtdpro());
                Hibernate.initialize(p.getDescrpro());
            }
            s.close();
        }catch(HibernateException e){
            e.printStackTrace();
            s.close();
        }
        
        return prod;
    }
    
    public boolean deletarProduto(TbProduto produto){
        Session s = this.getSession();
        
        try{
            s.beginTransaction();
            this.delete(produto);
            
            s.getTransaction().commit();
            
            return true;
        }catch(HibernateException e){
            s.getTransaction().rollback();
            return false;
        }       
    }
}
