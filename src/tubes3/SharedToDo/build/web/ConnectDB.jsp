<%@page import="java.sql.SQLException"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%!
    public static class ConnectDB {

        static String connectionURL = "jdbc:mysql://localhost:3306/";
        static String dbName = "progin";
        static Connection connection = null;
        static Statement statement = null;
        static String user = "progin";
        static String password = "progin";

        // fungsi: buat SELECT
        public static ResultSet mysql_query(String sqlQuery) {
            ResultSet resultSet = null;
            System.out.println("Masuk DB");

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                connection = DriverManager.getConnection(connectionURL + dbName, user, password);
                statement = connection.createStatement();
                //System.out.println(sqlQuery);
                resultSet = statement.executeQuery(sqlQuery);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }

            return resultSet;
        }

        // fungsi: buat UPDATE, INSERT, DELETE
        public static int mysql_query_updatedata(String sqlQuery) {
            int result = 0;
            System.out.println("Masuk DB");

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                connection = DriverManager.getConnection(connectionURL + dbName, user, password);
                statement = connection.createStatement();
                //System.out.println(sqlQuery);
                result = statement.executeUpdate(sqlQuery);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }

            return result;
        }

        public static void closeDB() {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
%>