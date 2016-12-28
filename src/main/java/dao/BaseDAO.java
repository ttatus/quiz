package dao;

import entities.User;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by tanya on 2016-11-28.
 */
@Log4j2
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
        try {
            em.merge(entity);
            log.info(entityClass.toString() + " updated.");
        } catch (HibernateException e) {
            log.error("Cannot update " + entityClass.toString() + " " + e.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(T entity) {

        try {
            em.remove(em.merge(entity));
            log.info(entityClass.toString() + " deleted.");
        } catch (HibernateException e) {
            log.error("Cannot delete " + entityClass.toString() + " " + e.getMessage());
        }
    }
}
