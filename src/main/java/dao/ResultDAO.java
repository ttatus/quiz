//package dao;
//
//import entities.Question;
//import entities.Result;
//import entities.Test;
//import entities.User;
//
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//import java.util.List;
//
///**
// * Created by tanya on 2016-11-29.
// */
//@Stateless
//public class ResultDAO extends BaseDAO<Result> {
//    @PersistenceContext(unitName = "quiz")
//    private EntityManager em;
//
//    ResultDAO() {
//        super.setEntityClass(Result.class);
//    }
//
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public Result create(int mark, User user, Test test){
//        return em.merge(new Result(mark, user, test));
//    }
//
//    public List<Result> findAll() {
//        TypedQuery<Result> query = em.createQuery("select a from Result a", Result.class);
//        return query.getResultList();
//    }
//
//    public Result findResultByUserAndTest(User user, Test test){
//        TypedQuery<Result> query = em.createQuery("select r from Result r where r.user = :user and r.test = :test", Result.class);
//        query.setParameter("user", user);
//        query.setParameter("test", test);
//        return query.getSingleResult();
//    }
//
//    public List<Result> findResultsByTest(Test test) {
//        TypedQuery<Result> query = em.createQuery("select r from Result r where r.test = :test", Result.class);
//        query.setParameter("test", test);
//        return query.getResultList();
//    }
//
//    public List<Result> findResultsByUser(User user) {
//        TypedQuery<Result> query = em.createQuery("select r from Result r where r.user = :user", Result.class);
//        query.setParameter("user", user);
//        return query.getResultList();
//    }
//
//}
