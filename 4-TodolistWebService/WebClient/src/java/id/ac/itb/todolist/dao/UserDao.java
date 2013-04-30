package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.User;
import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;

import org.json.JSONObject;
import org.json.JSONTokener;

public class UserDao extends DataAccessObject {

    public int addUser(User user) {
        id.ac.itb.todolist.soap.user.UserSoap_Service service = new id.ac.itb.todolist.soap.user.UserSoap_Service();
        id.ac.itb.todolist.soap.user.UserSoap port = service.getUserSoapPort();
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

    public boolean isAvailableUsername(String username) {
        // GET
        // rest/user/[userId]
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
        // GET
        // rest/user/email/[email]

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/user/email/" + URLEncoder.encode(email, "UTF-8"));
            htc.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Boolean.parseBoolean(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    public ArrayList<String> getUsers() {
        // GET
        // rest/user/
        ArrayList<String> result = new ArrayList<String>();

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/user/");
            htc.setRequestMethod("GET");

            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                result.add(jArray.getString(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public int update(User user) {
        // POST
        // rest/user/[felixt]
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/user/" + URLEncoder.encode(user.getUsername(), "UTF-8"));
            htc.setRequestMethod("POST");
            
            String urlParameters = "user=" + URLEncoder.encode(user.toJsonObject().toString(), "UTF-8");

            htc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            htc.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            htc.setRequestProperty("Content-Language", "en-US");

            htc.setUseCaches(false);
            htc.setDoInput(true);
            htc.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    htc.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
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
        ArrayList<User> result = new ArrayList<User>();
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/user/" + URLEncoder.encode(keyword, "UTF-8") + "/" + start + "/" + limit);
            htc.setRequestMethod("GET");

            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                User user = new User();
                user.fromJsonObject(jArray.getJSONObject(i));
                result.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
