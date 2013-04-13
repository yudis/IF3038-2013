
<%@ page import="java.sql.*"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.ResultSetMetaData"%>
<%@ page import="java.io.*" %> 
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="net.sf.json.JSONException"%>
<%! 
JSONArray rstojson(ResultSet rs)throws SQLException {
   JSONArray json = new JSONArray();
   
   ResultSetMetaData rsmd = rs.getMetaData();
   int numColumns = rsmd.getColumnCount();
   while(rs.next()){
       
       JSONObject obj = new JSONObject();
       
       for(int i=1; i<numColumns+1;i++){
           String column_name = rsmd.getColumnName(i);
           
             if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
                 obj.put(column_name,rs.getArray(column_name)); 
             }else
             if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
                 obj.put(column_name,rs.getInt(column_name));
             }else
             if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
                 obj.put(column_name,rs.getString(column_name)); 
             }else
             if(rsmd.getColumnType(i)==java.sql.Types.DATE){
                 obj.put(column_name,rs.getDate(column_name)); 
             }else{
                 obj.put(column_name, rs.getString(i));
             }     
       }
       json.add(obj);
      
    }
   
   return json;
}
%>