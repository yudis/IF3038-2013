package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.User;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;

import org.json.JSONObject;
import org.json.JSONTokener;

public class UserDao extends DataAccessObject {

    public User getUserLogin(String userId, String passwd) {
        // GET
        // rest/user/[userId]
        User user = null;

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/user/" + URLEncoder.encode(userId, "UTF-8"));
            htc.setRequestMethod("GET");

            User tmpUser = new User();
            tmpUser.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));

            System.out.println(tmpUser.toJsonObject().toString(4));

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwd.getBytes());
            String hashPassword = new BigInteger(1, md.digest()).toString(16);
            System.out.println(hashPassword);
            if (hashPassword.equals(tmpUser.getPassword())) {
                user = tmpUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }
}
