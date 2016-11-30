package jsf.admin;

import dao.QuestionDAO;
import dao.TestDAO;
import entities.Question;
import jsf.RegistrationBean;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanya on 2016-11-29.
 */
@ManagedBean
@ViewScoped
@Log4j2
public class AddTestBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private TestDAO testDAO;
    @EJB
    private QuestionDAO questionDAO;

    @Getter @Setter private String testName;
    @Getter @Setter private String testCategory;
    @Getter @Setter private String question;
    @Getter @Setter private String answer;
    @Getter @Setter private int points;
    @Getter @Setter private List<Question> questions;

    @PostConstruct
    public void init() {
        questions = new ArrayList<Question>();
    }

    public String createTest() {
        testDAO.create(testName, testCategory, questions);
        return "tests";
    }

    public void addQuestion() {
        questions.add(new Question(question, answer, points));
        cleanQuestionFields();
    }

    public void cleanQuestionFields() {
        question = "";
        answer = "";
        points = 0;
    }
}
