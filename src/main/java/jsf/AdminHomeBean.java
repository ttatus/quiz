package jsf;

import dao.UserDAO;
import entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by tanya on 2016-11-28.
 */

@ManagedBean
@ViewScoped
public class AdminHomeBean implements Serializable {
    private static final long serialVersionUID = 1L;
}
