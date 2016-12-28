package dao;

import entities.Question;
import entities.Test;
import entities.User;
import lombok.extern.log4j.Log4j2;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by tanya on 2016-11-28.
 */
@Log4j2
@Stateless
public class QuestionDAO extends BaseDAO<Question> {
    QuestionDAO() { super.setEntityClass(Question.class);}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Question create(Question question) {
        return em.merge(question);
    }

    public List<Question> findAll() {
        TypedQuery<Question> query = em.createQuery("SELECT a from Question a", Question.class);
        return query.getResultList();
    }

    public List<Question> findQuestionsByTest(Test test) {
        TypedQuery<Question> query = em.createQuery("select  q from Question q where q.test = :test", Question.class);
        query.setParameter("test", test);
        return query.getResultList();
    }
}
