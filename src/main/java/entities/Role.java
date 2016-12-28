package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tanya on 2016-11-27.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    private String roleName;

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
