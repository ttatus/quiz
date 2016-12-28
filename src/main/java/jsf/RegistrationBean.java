package jsf;

import dao.RoleDAO;
import dao.UserDAO;
import entities.Role;
import entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanya on 2016-11-27.
 */
@ManagedBean
@ViewScoped
@Log4j2
public class RegistrationBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserDAO userDAO;

    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String email;
    @Getter @Setter private String password;

    @Getter @Setter private String message;

    public String createUser() {
        try {
            userDAO.create(firstName, lastName, email, password);
            message = "You have been registered successfully!";
            log.info("Registration OK. Email: " + email);
            return "index.html";
        } catch (EJBException e) {
            log.error("Registration Failed", e.getCause());
            message = "Some error occurred :( Try again.";
        }
        return "";
    }
}
