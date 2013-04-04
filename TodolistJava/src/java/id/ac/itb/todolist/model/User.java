/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import id.ac.itb.todolist.util.BaseModel;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class User extends BaseModel {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private java.sql.Date tglLahir;
    private String avatar;

    public User() {
    }

    public User(String username, String email, String password, String fullName, Date tglLahir, String avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.tglLahir = tglLahir;
        this.avatar = avatar;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setPassword(String plainPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainPassword.getBytes());
            this.password = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setHashedPassword(String hashedPassword) {
        this.password = hashedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public java.sql.Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(java.sql.Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public JsonElement toJsonElement() {
        JsonObject jObject = new JsonObject();
        
        jObject.addProperty("username", username);
        jObject.addProperty("email", email);
        jObject.addProperty("password", password);
        jObject.addProperty("fullName", fullName);
        jObject.addProperty("tglLahir", tglLahir.toString());
        jObject.addProperty("avatar", avatar);
        
        return jObject;
    }
}
