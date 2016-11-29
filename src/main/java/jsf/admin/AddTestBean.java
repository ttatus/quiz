package jsf.admin;

import dao.QuestionDAO;
import dao.TestDAO;
import entities.Question;

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
public class AddTestBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private TestDAO testDAO;
    @EJB
    private QuestionDAO questionDAO;

    private String testName;
    private String testCategory;
    private String question;
    private String answer;
    private int points;
    private List<Question> questions;

    @PostConstruct
    public void init() {
        questions = new ArrayList<Question>();
    }

    public String createTest() {
//        for (int i=0; i<questions.size(); i++) {
//            questions.set(i, questionDAO.create(questions.get(i)));
//        }

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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestCategory() {
        return testCategory;
    }

    public void setTestCategory(String testCategory) {
        this.testCategory = testCategory;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
