/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

import Model.User;
import Utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class UserOp {

    private Connection connection;

    public UserOp() {
        connection = DBUtil.getConnection();
    }

    public boolean UserAuth(String username, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM user WHERE username = '" + username + "' AND password ='" + password + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                return true;
            } else {
                rs.close();
                return false;
            }
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    public boolean CheckUserExist(String username) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM user WHERE username = '" + username + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                return true;
            } else {
                rs.close();
                return false;
            }
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    public boolean CheckEmailExist(String email) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM user WHERE email = '" + email + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                return true;
            } else {
                rs.close();
                return false;
            }
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    public void InsertUser(User U) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO user (username,fullname,avatar,dob,email,password)"
                    + "VALUES ('" + U.getUsername() + "','" + U.getFullname() + "','uploaded/" + U.getAvatar() + "','"
                    + U.getDob() + "','" + U.getEmail() + "','" + U.getPassword() + "')");
            ps.execute();
            //ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public String InsertUser(String username, String fullname, String avatar, String dob, String email, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO user (username,fullname,avatar,dob,email,password)"
                    + "VALUES ('" + username + "','" + fullname + "','uploaded/" + avatar + "','"
                    + dob + "','" + email + "','" + password + "')");
            System.out.println("INSERT INTO user (username,fullname,avatar,dob,email,password)"
                    + "VALUES ('" + username + "','" + fullname + "','" + avatar + "','"
                    + dob + "','" + email + "','" + password + "')");
            ps.execute();
            //ResultSet rs = ps.executeQuery();
            return "1";
        } catch (SQLException e) {
            e.getMessage();
            return "0";
        }
    }

    //Timo, 10 April 2013
    public void UpdateUser(User U) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE user "
                    + "SET fullname='" + U.getFullname() + "', "
                    + "avatar='uploaded/" + U.getAvatar() + "', "
                    + "dob='" + U.getDob() + "', "
                    + "password='" + U.getPassword() + "' "
                    + "WHERE username = '" + U.getUsername() + "'");
            ps.execute();
            //ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    //Timo, 10 April 2013
    public void UpdateUser(String username, String fullname, String avatar, String dob, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE user "
                    + "SET fullname='" + fullname + "', "
                    + "avatar='uploaded/" + avatar + "', "
                    + "dob='" + dob + "', "
                    + "password='" + password + "' "
                    + "WHERE username = '" + username + "'");
            ps.execute();
            //ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public User SelectUserInfoByUsername(String username) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM user WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            User temp;
            if (rs.next()) {
                temp = new User(rs.getString("username"), rs.getString("fullname"), rs.getString("email"), rs.getString("avatar"), rs.getString("dob"), rs.getString("password"));
                rs.close();
                return temp;
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }

    public ArrayList<User> FetchUserForSearch(String searchParam) {
        ArrayList<User> result = new ArrayList<User>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM user WHERE username LIKE '%" + searchParam + "%' OR fullname LIKE '%" + searchParam + "%'");
            ResultSet rs = ps.executeQuery();
            
            User temp;
            while (rs.next()) {
                temp = new User(rs.getString("username"), rs.getString("fullname"), rs.getString("email"), rs.getString("avatar"), rs.getString("dob"), rs.getString("password"));
                result.add(temp);
            }

            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }

    public ArrayList<String> ListAllUsernames() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT username FROM user");

            while (rs.next()) {
                result.add(rs.getString(1));
            }

            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }
}
