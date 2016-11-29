package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tanya on 2016-11-28.
 */
@Entity
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "test_name", nullable = false, unique = true)
    private String testName;
    @Column(name = "category", nullable = false)
    private String category;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id", nullable = false)
    private List<Question> questions;

    public Test() {
    }

    public Test(String testName, String category) {
        this.testName = testName;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", testName='" + testName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
