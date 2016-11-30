package jsf;

import dao.RoleDAO;
import dao.UserDAO;
import entities.Role;
import entities.User;
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
public class RegistrationBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Logger log = LogManager.getLogger(RegistrationBean.class);

    @EJB
    private UserDAO userDAO;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String message;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
