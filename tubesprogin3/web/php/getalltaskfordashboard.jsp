
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%@ page import="java.text.*" %> 
<%!
//BELOM SELESE
public ResultSet getalltask(){
     ResultSet rs = null;
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "SELECT * FROM task";
    rs = statement.executeQuery(QueryStr);
   
   
   } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
   }

    return rs;
     }  
%>


<%--
    ResultSet result =getalltask();
   try{
        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
       
        while(result.next()){
            //  Date today = result.getDate("task_deadline");    
            date = (Date)formatter.parse(result.getString("task_deadline"));
            String s = formatter.format(date);
        out.println(s+"<br>");
    }
    }catch(Exception e){

    }
     
--%>