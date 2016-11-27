package dao;

import entities.Role;
import org.apache.logging.log4j.LogManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by tanya on 2016-11-27.
 */
@Stateless
public class RoleDAO {
    @PersistenceContext(unitName = "quiz")
    EntityManager em;

    public Role getRole(String roleName){
        TypedQuery<Role> query = em.createQuery("select a from Role a where a.roleName = :roleName", Role.class);
        query.setParameter("roleName", roleName);
        return query.getSingleResult();
    }
}
