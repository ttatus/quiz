package jsf;

import dao.UserDAO;
import entities.User;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by tanya on 2016-11-28.
 */
public class BaseUserBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserDAO userDAO;
    User user;
    FacesContext fc;
}
