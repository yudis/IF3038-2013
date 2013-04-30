/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


public class UserDao extends DataAccessObject {
    public User getUserLogin(String userId, String passwd) {
        User user = null;

        try {
            
            HttpURLConnection htc = getConnection("rest/user/detil/" + URLEncoder.encode(userId, "UTF-8"));
            htc.setRequestMethod("GET");

            User tmpU = new User();
            tmpU.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));
            
            System.out.println(tmpU.toJsonObject().toString(4));
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwd.getBytes());
            String MD5Pass = new BigInteger(1, md.digest()).toString(16);
            System.out.println(MD5Pass);
            if (MD5Pass.equals(tmpU.getPassword())) {
                user = tmpU;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public boolean isAvailableUsername(String username) {
        try {
            HttpURLConnection htc = getConnection("rest/user/detil/" + URLEncoder.encode(username, "UTF-8"));
            htc.setRequestMethod("GET");

            User tmpU = new User();
            tmpU.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));
            
            return false; // tmpU tidak null dan mendapatkan hasil sehingga username tidak dapat digunakan
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true; // tmpU null dan keluar dari try catch
    }

    public boolean isAvailableEmail(String email) {
        // GET
        // rest/user/email/[email]
        try {
            HttpURLConnection htc = getConnection("rest/user/email/" + URLEncoder.encode(email, "UTF-8"));
            htc.setRequestMethod("GET");

            User tmpU = new User();
            tmpU.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));
            
            return false; // tmpU tidak null dan mendapatkan hasil sehingga email tidak dapat digunakan
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true; // tmpU null dan keluar dari try catch
    }
    
    public ArrayList<String> getUsers() {
    // GET
    // rest/user/
        ArrayList<String> result = null;
        try {
            HttpURLConnection htc = getConnection("rest/user/");
            htc.setRequestMethod("GET");

            result = new ArrayList<String>();
            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                result.add(jArray.getString(i));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int Update(User user){
    // POST
    // rest/user/update/username
        try {
            HttpURLConnection htc = getConnection("rest/user/update/" + URLEncoder.encode(user.getUsername(), "UTF-8"));
            htc.setRequestMethod("POST");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public User getUser(String userId) {
    // GET
    // rest/user/detil/username    
        User user = null;

        try {
            HttpURLConnection htc = getConnection("rest/user/detil/" + URLEncoder.encode(userId, "UTF-8"));
            htc.setRequestMethod("GET");

            User tmpU = new User();
            tmpU.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));
            user = tmpU;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }    

    public Collection<User> getUserSearch(String Id, int start, int n) throws IOException {
    // GET
    // rest/user/search/w/0/3
        ArrayList<User> result = new ArrayList<User>();
        try {
            HttpURLConnection htc = getConnection("/rest/user/search/" + URLEncoder.encode(Id, "UTF-8") + "/" + start + "/" + n);
            htc.setRequestMethod("GET");

            result=new ArrayList<User>();
            JSONArray ja = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0; i < ja.length(); i++) {
                User user = new User();
                user.fromJsonObject(ja.getJSONObject(i));
                result.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        return result;
    }       

    public int addUser(User user) {
        UserSoap.UserSoap_Service service = new UserSoap.UserSoap_Service();
        UserSoap.UserSoap port = service.getUserSoapPort();
        return port.addUser(user.toJsonObject().toString());
    }
    
}
