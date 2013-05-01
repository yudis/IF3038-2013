/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package answerer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arief
 */
public class Checker {

    public static boolean isAssignee(String username, String namaTask) {
        try {
            boolean found = false;
            String tQuery = "select * from tasktoasignee where namaTask = \"" + namaTask + "\"";
            ResultSet tResult = MyDatabase.getSingleton().selectDB(tQuery);
            while (tResult.next() && found == false) {
                if (tResult.getString("asigneeName").equals(username)) {
                    found = true;
                }
            }
            return found;
        } catch (SQLException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean isCreator(String username, String namaTask) {
        try {
            boolean found = false;
            String tQuery = "select * from task where namaTask = \"" + namaTask + "\"";
            ResultSet tResult = MyDatabase.getSingleton().selectDB(tQuery);
            while (tResult.next() && found == false) {
                if (tResult.getString("creatorTaskName").equals(username)) {
                    found = true;
                }
            }
            return found;
        } catch (SQLException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static String getCategoryCreator(String namaKategori) {
        String output = "";
        try {
            String tQuery = "select * from kategori where namaKategori=\"" + namaKategori + "\"";
            ResultSet tResult = MyDatabase.getSingleton().selectDB(tQuery);
            while (tResult.next()) {
                output = tResult.getString("creatorKategoriName");
            }
            return output;
        } catch (SQLException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
            return output;
        }
    }

    public static boolean isContributor(String username, String namaKategori) {
        boolean outp = false;
        try {
            String pQuery = "select * from usertocategory where namaKategori=\"" + namaKategori + "\"";
            ResultSet tResult = MyDatabase.getSingleton().selectDB(pQuery);
            while (tResult.next() && outp == false) {
                if (tResult.getString("username").equals(username)) {
                    outp = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outp;
    }

    public static String getAvatar(String username) {

        String outp = "";
        try {
            String pQuery = "select * from user where username=\"" + username + "\"";
            ResultSet tResult = MyDatabase.getSingleton().selectDB(pQuery);
            while (tResult.next()) {
                outp = tResult.getString("avatar");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outp;
    }

    public static String getEmail(String username){
        String outp = "";
        try {
            String pQuery = "select * from user where username=\"" + username + "\"";
            ResultSet tResult = MyDatabase.getSingleton().selectDB(pQuery);
            while (tResult.next()) {
                outp = tResult.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outp;
    }
    
    public static void main(String[] args) {
        System.out.println(isContributor("arief", "categ1"));
    }
}
