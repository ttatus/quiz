package jsf;

import dao.UserDAO;
import entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tanya on 2016-11-28.
 */

@ManagedBean
@ViewScoped
public class UsersListBean implements Serializable {

    @EJB
    private UserDAO userDAO;
    List<User> users;

    @PostConstruct
    public void init() {
        users = userDAO.findAll();
    }

    public void delete(int user_id) throws IOException {
        userDAO.delete(userDAO.find(user_id));
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    // TODO change status to one of moderator, admin, user
    public void changeStatus(int user_id, String status) {}

    public List<User> getUsers() {
        return users;
    }
}
