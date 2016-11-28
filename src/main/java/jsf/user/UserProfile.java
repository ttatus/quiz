package jsf.user;

import dao.UserDAO;
import entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by tanya on 2016-11-27.
 */

@javax.faces.bean.ManagedBean
@ViewScoped
public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    UserDAO userDAO;

    User user;
    String message = "";
    FacesContext fc;

    @PostConstruct
    public void init() {
        fc = FacesContext.getCurrentInstance();
        String email = fc.getExternalContext().getUserPrincipal().getName();
        user = userDAO.findUserByEmail(email);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void saveChanges() {
        try {
            userDAO.update(user);
            message = "Changes saved successfuly!";
        } catch (EJBException e) {
            message = "Changes didn`t saved. Try again.";
        }
    }

//    public void delete() {
//        userDAO.delete(userDAO.find(user_id));
//    }
}
