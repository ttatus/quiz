package entities;

import javax.persistence.*;

/**
 * Created by tanya on 2016-11-28.
 */
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question", nullable = false, unique = true)
    private String question;
    @Column(name = "answer", nullable = false)
    private String answer;
    @Column(name = "points", nullable = false)
    private int points;

    public Question(String question, String answer, int points) {
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", points=" + points +
                '}';
    }
}
