package dao;

import entities.Question;
import entities.Test;
import jsf.admin.AddTestBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanya on 2016-11-28.
 */
@Stateless
public class TestDAO extends BaseDAO<Test> {
    private final Logger log = LogManager.getLogger(TestDAO.class);
    TestDAO() {
        super.setEntityClass(Test.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Test create(String name, String category, List<Question> q) {
        Test test = new Test(name, category);
        test.setQuestions(q);
        em.persist(test);
        em.flush();
        log.info("New test created. Name: "+test.getTestName());
        return test;
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
