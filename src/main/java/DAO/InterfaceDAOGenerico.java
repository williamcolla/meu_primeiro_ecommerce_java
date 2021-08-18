/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author admingbd
 */
public interface InterfaceDAOGenerico<T, ID extends Serializable> {
    public void save(T entity);
    
    public void merge(T entity);
    
    public void delete(T entity);
    
    public List<T> findMany(Query query);
    
    public T findOne(Query query);
    
    public List findAll(Class clazz);
    
    public T findByID(Class clazz, Integer id);
    
}
