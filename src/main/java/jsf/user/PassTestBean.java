package jsf.user;

import dao.ResultDAO;
import dao.TestDAO;
import dao.UserDAO;
import entities.Question;
import entities.Test;
import entities.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanya on 2016-11-29.
 */
@ManagedBean
@ViewScoped
public class PassTestBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    TestDAO testDAO;

    @EJB
    ResultDAO resultDAO;

    @EJB
    UserDAO userDAO;

    @Getter @Setter Test test;
    @Getter @Setter List<Question> questions;
    @Getter @Setter List<String> answers;
    @Getter @Setter List<String> marks;

    @Getter int result;

    @Getter boolean buttonDisabled = false;

    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        int id = Integer.parseInt(((Map<String, String>) ec.getRequestParameterMap()).get("test_id"));
        test = testDAO.find(id);

        questions = test.getQuestions();

        answers = new ArrayList<String>();
        marks = new ArrayList<String>();
        for(int i = 0; i < questions.size(); i++) {
            answers.add("");
            marks.add("");
        }
    }

    public void check() {
        int points = 0;
        for(int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            if (answers.get(i).toLowerCase().equals(q.getAnswer().toLowerCase())) {
                marks.set(i, "+");
                points += q.getPoints();
            }
            else {
                marks.set(i, "-");
            }
        }

        result = points;
        resultDAO.create(result, getCurrentUser(), test);

        buttonDisabled = true;
    }

    private User getCurrentUser() {
        String email = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return userDAO.findUserByEmail(email);
    }
}
