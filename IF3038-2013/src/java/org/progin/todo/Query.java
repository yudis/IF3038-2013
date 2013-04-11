package org.progin.todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.servlet.ServletException;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author kamilersz
 */
public class Query {

    public static List all(String q, String[] w) {
        List result = new ArrayList<ResultSet>();
        // connecting to database
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510001", "progin", "progin");
            stmt = con.prepareStatement(q);
            if (w != null) {
                for (int i = 0; i < w.length; i++) {
                    stmt.setString(i + 1, w[i]);
                }
            }
            rs = stmt.executeQuery();
            result = convertResultSetToList(rs);
        } catch (SQLException e) {
            throw new ServletException("Servlet Could not display records.", e);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found.", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
            return result;
        }
    }

    public static HashMap<String, Object> one(String q, String[] w) {
        HashMap<String, Object> result = null;
        // connecting to database
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510001", "progin", "progin");
            stmt = con.prepareStatement(q);
            if (w != null) {
                for (int i = 0; i < w.length; i++) {
                    stmt.setString(i + 1, w[i]);
                }
            }
            rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            if (rs.next()) {
                HashMap row = new HashMap(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                result = row;
            } else {
                result = null;
            }
        } catch (SQLException e) {
            throw new ServletException("Servlet Could not display records.", e);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found.", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
            return result;
        }
    }

    public static Integer nid(String q, String[] w) {
        Integer result = 0;
        // connecting to database
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510001", "progin", "progin");
            stmt = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            if (w != null) {
                for (int i = 0; i < w.length; i++) {
                    stmt.setString(i + 1, w[i]);
                }
            }
            result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    }

    public static void n(String q, String[] w) {
        // connecting to database
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510001", "progin", "progin");
            stmt = con.prepareStatement(q);
            if (w != null) {
                for (int i = 0; i < w.length; i++) {
                    stmt.setString(i + 1, w[i]);
                }
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public static List<HashMap<String, Object>> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList(50);
        while (rs.next()) {
            HashMap row = new HashMap(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }

        return list;
    }

    public static void main(String args[]) throws SQLException {
        String tag = "satu,dua,tiga";
        String[] tags = tag.split("\\s+");
        for (int i = 0; i < tags.length; i++ ) {
            System.out.println(tags[i]);
        }
        String[] param = {"2"};
        List<HashMap<String, Object>> r = Query.all("select * from task where task_id = ?", param);
        if (r.isEmpty()) {
            System.out.println("error");
        } else {
            for (Entry<String, Object> e : r.get(0).entrySet()) {
                System.out.println(e.getKey());
                System.out.println(e.getValue());
            }
        }
    }
}
