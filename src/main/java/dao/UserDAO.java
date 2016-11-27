package dao;

import entities.Role;
import entities.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanya on 2016-11-27.
 */
@Stateless
public class UserDAO {
    @PersistenceContext(unitName = "quiz")
    protected EntityManager em;

    public UserDAO() {}

    public User find(int id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("SELECT a from User a", User.class);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public User create(String firstName, String lastName, String email, String password) {
        User user = new User(firstName, lastName, email, password);
        List<Role> roles = new ArrayList<Role>();
        roles.add(getRole("user"));
        user.setRoles(roles);
        em.persist(user);
        em.flush();
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(User u) {
        em.merge(u);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(User user) {
        em.remove(em.merge(user));
    }

    public User findUserByEmail(String email) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public Role getRole(String roleName){
        TypedQuery<Role> query = em.createQuery("select a from Role a where a.roleName = :roleName", Role.class);
        query.setParameter("roleName", roleName);
        return query.getSingleResult();
    }
}
