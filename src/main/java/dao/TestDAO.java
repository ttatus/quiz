package dao;

import entities.Test;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by tanya on 2016-11-28.
 */
@Stateless
public class TestDAO extends BaseDAO<Test> {
    TestDAO() {
        super.setEntityClass(Test.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Test create(String name, String category) {
        return em.merge(new Test(name, category));
    }

    public List<Test> findAll() {
        TypedQuery<Test> query = em.createQuery("select a from Test a", Test.class);
        return query.getResultList();
    }

    public List<Test> findTestsByCategory(String category) {
        TypedQuery<Test> query = em.createQuery("select a from Test a where a.category = :category", Test.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    public List<String> findAllCategories() {
        Query query = em.createQuery("SELECT distinct(t.category) from Test t");
        return query.getResultList();
    }
}
