package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;


public class UserDao extends DataAccessObject {

    public int addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (username, email, password, full_name, tgl_lahir, avatar) VALUES  (?, ?, ?, ?, ?, ?);");
            // Parameters start with 1
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFullName());
            preparedStatement.setDate(5, user.getTglLahir());
            preparedStatement.setString(6, user.getAvatar());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public User getUserLogin(String userId, String passwd) {
        User user = null;

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM users WHERE username=? AND password=MD5(?)");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, passwd);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setHashedPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setTglLahir(rs.getDate("tgl_lahir"));
                user.setAvatar(rs.getString("avatar"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean isAvailableUsername(String username) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM users WHERE username=?");
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean isAvailableEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM users WHERE email=?");
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    public ArrayList<String> getUsers() {
        ArrayList<String> result = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT username FROM users ;");

            ResultSet rs = preparedStatement.executeQuery();
            
            result = new ArrayList<String>();
            while (rs.next()) {
                result.add(rs.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int Update(User user){
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE users SET `password`=?, `full_name`=?, `tgl_lahir`=?, `avatar`=? WHERE `username`=?;");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getFullName());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String tglL = df.format(user.getTglLahir());
            preparedStatement.setString(3, tglL);
            preparedStatement.setString(4, user.getAvatar());
            preparedStatement.setString(5, user.getUsername());
            
            return preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public User getUser(String userId) {
        User user = null;

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM users WHERE username=?");
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setHashedPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setTglLahir(rs.getDate("tgl_lahir"));
                user.setAvatar(rs.getString("avatar"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }    

    public Collection<User> getUserSearch(String Id, int start, int n) throws IOException {
        User user = null;
        ArrayList<User> result = new ArrayList<User>();
        String qry = "SELECT * FROM users WHERE username LIKE '%" + Id + "%' LIMIT " + start + ", " + n + ";";
        try {
            
            PreparedStatement preparedStatement = connection.
                    prepareStatement(qry);   
            
            ResultSet rs = preparedStatement.executeQuery();       
            while (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setHashedPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setTglLahir(rs.getDate("tgl_lahir"));
                user.setAvatar(rs.getString("avatar"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            
        return result;
    }        
}
