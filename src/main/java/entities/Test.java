package entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tanya on 2016-11-28.
 */
@Data
@NoArgsConstructor
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id", nullable = false)
    private List<Question> questions;
//    @OneToMany(mappedBy = "test")
//    private Set<Result> results;

    public Test(String testName, String category) {
        this.testName = testName;
        this.category = category;
    }

//    public List<Result> getResults() {
//        return results;
//    }
//
//    public void setResults(List<Result> results) {
//        this.results = results;
//    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", testName='" + testName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public boolean equals(Test anotherTest) {
        return id == anotherTest.getId();
    }
}
