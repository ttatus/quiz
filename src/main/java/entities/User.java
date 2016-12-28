package entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by tanya on 2016-11-27.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(name = "role"))
    private List<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Result> results;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getRoleName() {
        return getRoles().get(0).getRoleName();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
