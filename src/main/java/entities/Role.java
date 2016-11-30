package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tanya on 2016-11-27.
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    private String roleName;

    public Role() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}