package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by tanya on 2016-11-29.
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name= "mark")
    private int mark;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    public Result(int mark, User user, Test test) {
        this.mark = mark;
        this.user = user;
        this.test = test;
    }
}


