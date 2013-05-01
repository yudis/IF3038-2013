/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONTokener;

/**
 *
 * @author Bona
 */
public class MyDatabase {

    private static MyDatabase mSingleton = new MyDatabase();

    public static MyDatabase getSingleton() {
        if (mSingleton == null) {
            mSingleton = new MyDatabase();
        }
        return mSingleton;
    }

    private String mDBName = "jdbc:mysql://localhost/sharedtodolist";
    private String mDBUser = "root";
    private String mDBPass = "";
    private Connection mConn;
    private Statement mStatement;

    private String urlbase = "http://localhost:8080/STDL/";
    
    public MyDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            mConn = DriverManager.getConnection(mDBName, mDBUser, mDBPass);
            mStatement = mConn.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int queryDB(String _pQuery) {
        int tReturn = -1;
        try {
            String pQuery = _pQuery.replaceAll(" ", "~");
            pQuery = pQuery.replaceAll("%", "~~");
            URL url = new URL(urlbase + "query-nonselect-rest/" + pQuery);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            JSONArray jA = new JSONArray(new JSONTokener(con.getInputStream()));
            if(jA.getJSONObject(0).getInt("result")==0)
                return -1;
            else
                return jA.getJSONObject(0).getInt("result");
        } catch (IOException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public JSONArray selectDBREST(String _pQuery) {
        try {
            String pQuery = _pQuery.replaceAll(" ", "~");
            pQuery = pQuery.replaceAll("%", "~~");
            URL url = new URL(urlbase + "query-select/" + pQuery);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            JSONArray jA = new JSONArray(new JSONTokener(con.getInputStream()));
            return jA;
        } catch (IOException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void main(String[] args){
        System.out.println(MyDatabase.getSingleton().selectDBREST("select * from user where username like '%arief%'").toString());
    }
    
}
