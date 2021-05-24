package poly.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@NamedQuery(name = "User.findAll", query = "select u from User u")
public class User implements Serializable {

    @Id
    private String userId;
    private boolean admin;
    private String fullname;
    private String email;
    private String password;

    public User() {
    }

    public User(String userId, boolean admin, String fullname, String email, String password) {
        this.userId = userId;
        this.admin = admin;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
