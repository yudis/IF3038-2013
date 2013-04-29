package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.User;

import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;
import org.json.JSONTokener;

public class UserDao extends DataAccessObject {

    public int addUser(User user) {
        id.ac.itb.todolist.soap.client.UserSoap_Service service = new id.ac.itb.todolist.soap.client.UserSoap_Service();
        id.ac.itb.todolist.soap.client.UserSoap port = service.getUserSoapPort();
        return port.addUser(user.toJsonObject().toString());
    }

    public User getUserLogin(String userId, String passwd) {
        // GET
        // rest/user/[userId]
        User user = null;

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/user/" + URLEncoder.encode(userId, "UTF-8"));
            htc.setRequestMethod("GET");

            User tmpUser = new User();
            tmpUser.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwd.getBytes());
            if (new BigInteger(1, md.digest()).toString(16).contentEquals(tmpUser.getPassword())) {
                user = tmpUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public boolean isAvailableUsername(String username) {
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/user/" + URLEncoder.encode(username, "UTF-8"));
            htc.setRequestMethod("GET");

            User tmpUser = new User();
            tmpUser.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));
            
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public boolean isAvailableEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<String> getUsers() {
        // GET
        // rest/user/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int update(User user) {
        // POST
        // rest/user/[felixt]
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public User getUser(String username) {
        // GET
        // rest/user/[felixt]
        User user = null;

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/user/" + URLEncoder.encode(username, "UTF-8"));
            htc.setRequestMethod("GET");

            User tmpUser = new User();
            tmpUser.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));
            
            user = tmpUser;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public Collection<User> getUserSearch(String keyword, int start, int limit) throws IOException {
        // GET
        // rest/user/w/0/3
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
