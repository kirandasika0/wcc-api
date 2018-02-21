package com.wcc.admin;
/*
    Admin data model.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 255)
    private String username;

    @Length(max = 1024)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private ArrayList<String> perms;

    /*
        prefix "is" is removed when serialized to JSON.
        Ex. isSuperUser is serialized as superUser
     */
    private boolean isSuperUser = false;

    public boolean isSuperUser() {
        return isSuperUser;
    }

    public void setSuperUser(boolean superUser) {
        isSuperUser = superUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getPerms() {
        return perms;
    }

    public void setPerms(ArrayList<String> perms) {
        this.perms = perms;
    }

    public void addPerm(String newPerm) {
        this.perms.add(newPerm);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", perms=" + perms +
                '}';
    }

    /*
        Check if username and password match for an admin.
        @param payloadAdmin adminProvided from client side for validation
        @param dbAdmin an admin instance from the database
     */
    public boolean loginAdmin(Admin payloadAdmin) {
        if (payloadAdmin.getUsername().equals(this.getUsername())) {
            return payloadAdmin.getPassword().equals(this.getPassword());
        }
        return false;
    }

}
