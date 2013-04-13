package model;

public class UserBean {
    private String username;
    private String password;
    private String namalengkap;
    private java.sql.Date dob;
    private String email;
    private String avatar;
    private boolean valid;
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getNamalengkap(){
        return namalengkap;
    }
        
    public java.sql.Date getDate(){
        return dob;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getAvatar(){
        return avatar;
    }
    
    public void setUsername(String _username){
        username = _username;
    }
    
    public void setPassword(String _password){
        password = _password;
    }
    
    public void setNamalengkap(String _namalengkap){
        namalengkap = _namalengkap;
    }
        
    public void setDate(java.sql.Date _dob){
        dob = _dob;
    }
    
    public void setEmail(String _email){
        email = _email;
    }
    
    public void setAvatar(String _avatar){
        avatar = _avatar;
    }
    
    public boolean isValid(){
        return valid;
    }
    
    public void setValid(boolean _valid){
        valid = _valid;
    }
}
