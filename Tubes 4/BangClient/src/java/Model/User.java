/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.json.JSONObject;

/**
 *
 * @author Nicholas Rio
 */
public class User {

    private String username;
    private String fullname;
    private String email;
    private String avatar;
    private String dob;
    private String password;

    public User(String username, String fullname, String email, String avatar, String dob, String password) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.avatar = avatar;
        this.dob = dob;
        this.password = password;
    }
    
    public User (JSONObject jsonObj) {
        this.username = jsonObj.getString("username");
        this.fullname = jsonObj.getString("fullname");
        this.email = jsonObj.getString("email");
        this.avatar = jsonObj.getString("avatar");
        this.dob = jsonObj.getString("dob");
        this.password = jsonObj.getString("password");
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}