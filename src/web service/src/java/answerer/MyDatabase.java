/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package answerer;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private String mDBName = "jdbc:mysql://progin4-phpmyadmin.ap01.aws.af.cm/sharedtodolist";
    private String mDBUser = "root";
    private String mDBPass = "";
    private Connection mConn;
    private Statement mStatement;

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

    public ResultSet selectDB(String pQuery) {
        ResultSet tReturn = null;
        try {
            tReturn = mStatement.executeQuery(pQuery);
        } catch (SQLException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tReturn;
    }

    public int queryDB(String pQuery) {
        int tReturn = -1;
        try {
            tReturn = mStatement.executeUpdate(pQuery);
        } catch (SQLException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tReturn;
    }
}
