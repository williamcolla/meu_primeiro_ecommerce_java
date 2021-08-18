/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Mapeamento.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author admingbd
 */
public class DAOGenerico<T, ID extends Serializable> implements InterfaceDAOGenerico<T, ID> {
    protected Session getSession(){
        return HibernateUtil.getSession();
    }

    @Override
    public void save(T entity) {
        Session hibernateSession = this.getSession(); 
        hibernateSession.saveOrUpdate(entity);
    }

    @Override
    public void merge(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }

    @Override
    public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }

    @Override
    public List <T> findMany(Query query) {
        List<T> T;
        T = (List<T>) query.list();
        return T;
    }

    @Override
    public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }

    @Override
    public List findAll(Class clazz) {
        Session hibernateSession = this.getSession();
        List T = null;
        Query query = hibernateSession.createQuery("from " + clazz.getSimpleName());
        T = query.list();
        return T;
    }

    @Override
    public T findByID(Class clazz, Integer id) {
        Session hibernateSession = this.getSession();
        T t = null;
        t = (T) hibernateSession.get(clazz, id);
        return t;
    }
    
}
