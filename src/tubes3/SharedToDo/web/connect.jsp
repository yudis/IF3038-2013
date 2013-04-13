<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%!
    public ResultSet mysql_query(String query) throws Exception{
        String connectionURL = "jdbc:mysql://localhost:3306/";
        String db = "progin";
        String user = "progin";
        String password = "progin";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL + db, user, password);
        statement = connection.createStatement();
        
        System.out.println(query);
        result = statement.executeQuery(query);
        
        return result;
    }
%>