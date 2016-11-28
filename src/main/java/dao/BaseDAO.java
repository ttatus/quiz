package dao;

import entities.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by tanya on 2016-11-28.
 */
@Stateless
public class BaseDAO<T> {
    @PersistenceContext(unitName = "quiz")
    protected EntityManager em;

    private Class<T> entityClass;

    protected void setEntityClass(Class<T> c) {
        this.entityClass = c;
    }

    public T find(int id) {
        return em.find(entityClass, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(T entity) {
        em.merge(entity);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(T entity) {
        em.remove(em.merge(entity));
    }
}
