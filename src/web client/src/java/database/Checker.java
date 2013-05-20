/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author Arief
 */
public class Checker {

    public static boolean isAssignee(String username, String namaTask) {
        boolean found = false;
        String tQuery = "select * from tasktoasignee where namaTask = \"" + namaTask + "\"";
        JSONArray tResult = MyDatabase.getSingleton().selectDBREST(tQuery);
        int i = 0;
        while (!tResult.isNull(i) && found == false) {
            if (tResult.getJSONObject(i).getString("asigneeName").equals(username)) {
                found = true;
            }
            i++;
        }
        return found;
    }

    public static boolean isCreator(String username, String namaTask) {
        boolean found = false;
        String tQuery = "select * from task where namaTask = \"" + namaTask + "\"";
        JSONArray tResult = MyDatabase.getSingleton().selectDBREST(tQuery);
        int i = 0;
        while (!tResult.isNull(i) && found == false) {
            if (tResult.getJSONObject(i).getString("creatorTaskName").equals(username)) {
                found = true;
            }
            i++;
        }
        return found;
    }

    public static String getCategoryCreator(String namaKategori) {
        String output = "";
        String tQuery = "select * from kategori where namaKategori=\"" + namaKategori + "\"";
        JSONArray tResult = MyDatabase.getSingleton().selectDBREST(tQuery);
        int i = 0;
        while (!tResult.isNull(i)) {
            output = tResult.getJSONObject(i).getString("creatorKategoriName");
            i++;
        }
        return output;
    }

    public static boolean isContributor(String username, String namaKategori) {
        boolean outp = false;
        String pQuery = "select * from usertocategory where namaKategori=\"" + namaKategori + "\"";
        JSONArray tResult = MyDatabase.getSingleton().selectDBREST(pQuery);
        int i = 0;
        while (!tResult.isNull(i) && outp == false) {
            if (tResult.getJSONObject(i).getString("username").equals(username)) {
                outp = true;
            }
            i++;
        }
        return outp;
    }

    public static String getAvatar(String username) {

        String outp = "";
        String pQuery = "select * from user where username=\"" + username + "\"";
        JSONArray tResult = MyDatabase.getSingleton().selectDBREST(pQuery);
        int i = 0;
        while (!tResult.isNull(i)) {
            outp = tResult.getJSONObject(i).getString("avatar");
            i++;
        }
        return outp;
    }

    public static String getEmail(String username) {
        String outp = "";
        String pQuery = "select * from user where username=\"" + username + "\"";
        JSONArray tResult = MyDatabase.getSingleton().selectDBREST(pQuery);
        int i = 0;
        while (!tResult.isNull(i)) {
            outp = tResult.getJSONObject(i).getString("email");
            i++;
        }
        return outp;
    }

    public static void main(String[] args) {
        System.out.println(isContributor("arief", "categ1"));
    }
}
