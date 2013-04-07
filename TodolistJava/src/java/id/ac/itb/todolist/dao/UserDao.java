/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}