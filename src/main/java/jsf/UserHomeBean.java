package jsf;

import dao.UserDAO;
import entities.User;
import org.openfaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by tanya on 2016-11-27.
 */
@ManagedBean
@SessionScoped
public class UserHomeBean implements Serializable {
    @EJB
    private UserDAO userDAO;
    User user;
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

//    public void logout() throws IOException {
//        fc.getExternalContext().invalidateSession();
//        fc.getExternalContext().redirect("index.xhtml");
//    }
}
