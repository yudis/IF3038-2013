package bean;

import java.util.Date;

public class userBean {
    String username;
    String password;
    String fullname;
    Date birthdate;
    String avatar; //masih salah
    String email;
    
    //Constructor
    public userBean() {}

    public userBean(String username, String password, String fullname, Date birthdate, String avatar, String email) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.avatar = avatar;
        this.email = email;
    }
    
    //Setter
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    //Getter
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }
    
    
}
