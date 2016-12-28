package jsf.user;

import dao.ResultDAO;
import dao.TestDAO;
import dao.UserDAO;
import entities.Result;
import entities.Test;
import entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by tanya on 2016-11-30.
 */
@ManagedBean
@ViewScoped
@Log4j2
public class ResultsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    UserDAO userDAO;

    @Getter @Setter User user;
    FacesContext fc;

    @PostConstruct
    public void init() {
        fc = FacesContext.getCurrentInstance();
        String email = fc.getExternalContext().getUserPrincipal().getName();
        user = userDAO.findUserByEmail(email);
    }

    public List<Test> getPassedTests() {
        List<Test> tests = new ArrayList<Test>();

        for(Result r: getResults()) {
            tests.add(r.getTest());
        }

        return tests;
    }

    public boolean isTestPassed(Test test) {
        boolean flag = false;

        for(Result r: getResults()) {
            if (r.getTest().equals(test)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public Set<Result> getResults() {
        return user.getResults();
    }

    public String getTestMark(Test test) {
        String mark = "";

        for (Result r : getResults()) {
            if (r.getTest().equals(test)) {
                mark = Integer.toString(r.getMark());
                break;
            }
        }

        return mark;
    }
}
