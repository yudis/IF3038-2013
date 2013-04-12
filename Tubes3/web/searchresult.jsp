<%-- 
    Document   : searchresult
    Created on : Apr 11, 2013, 11:58:22 PM
    Author     : user
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>      
<%@ include file="header.jsp" %>

<html>

    <title> Next | Search Result </title>    

    
    <body>
<%
    String cari = request.getParameter("search");
    String filter = request.getParameter("searchFilter");
    
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection conLogin = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510003","root","");
   
    if(filter.equals("semua")){
        
        Statement stLogin= conLogin.createStatement(); 
        ResultSet rsLogin=stLogin.executeQuery("select * from pengguna where username='"+cari+"'");     

        while(rsLogin.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("user:");

            out.print("<div>");
            out.print(" <a href=\"profile.jsp\">");
            out.print(rsLogin.getString("username"));
            out.print(" </a>");
            out.println("</div>");
          
            out.print("<div>");
            out.print(rsLogin.getString("tanggal_lahir"));
            out.println("</div>");

            out.print("<div>");
            out.print(rsLogin.getString("nama_lengkap"));
            out.println("</div>");

            out.print("<div>");
            out.print("<img width=\"60px\" height=\"60px\" src=\"");
            out.print(rsLogin.getString("avatar"));
            out.print("\">" );
            out.println("</div>");

            out.println("</div>");
        }//end user 

        Statement stLogin2= conLogin.createStatement(); 
        ResultSet rsLogin2=stLogin2.executeQuery("select * from kategori where nama_kategori='"+cari+"'");     

        while(rsLogin2.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("kategori:");

            out.print("<div>");
            out.print(rsLogin2.getString("nama_kategori"));
            out.println("</div>");

            out.print("<div>");
            out.print(rsLogin2.getString("username"));
            out.println("</div>");                

            out.println("</div>");
        } //end kategori

        Statement stLogin3= conLogin.createStatement(); 
        ResultSet rsLogin3=stLogin3.executeQuery("select * from tugas natural join kategori where nama_tugas='"+cari+"'");     

        while(rsLogin3.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("tugas:");

            out.print("<div>");
            out.print(" <a href=\"rinciantugas.jsp?id_tugas=");
            out.print(rsLogin3.getString("id_tugas"));
            out.print("\">");
            out.println("</div>");

            out.print("<div>");
            out.print(rsLogin3.getString("nama_tugas"));
            out.println("</div>");

            out.println("</a>");

            out.print("<div>");
            out.print(rsLogin3.getString("deadline"));
            out.println("</div>");

            out.print("<div> tag: ");        
            out.print(rsLogin3.getString("tag"));        
            out.println("</div>");

            out.print("<div> status: ");        
            out.print(rsLogin3.getString("status"));        
            out.println("</div>");

            out.println("<input type=\"checkbox\"> ");

            out.println("</div>");
        }//end tugas


        Statement stLogin4= conLogin.createStatement(); 
        ResultSet rsLogin4=stLogin4.executeQuery("SELECT * FROM tugas NATURAL JOIN kategori WHERE tag='"+cari+"'");     

        while(rsLogin4.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("tag:");

            out.print("<div>");
            out.print(rsLogin4.getString("tag"));
            out.println("</div>");

            out.print("<div>");
            out.print(" <a href=\"rinciantugas.jsp?id_tugas=");
            out.print(rsLogin4.getString("id_tugas"));
            out.print("\">");
            out.println("</div>");

            out.print("<div>");
            out.print(rsLogin4.getString("nama_tugas"));
            out.println("</div>");

            out.println("</a>");

            out.print("<div>");
            out.print(rsLogin4.getString("nama_kategori"));
            out.println("</div>");         

            out.println("</div>");
        } //end tag
    }//end semua
    
    else if(filter.equals("username")){
        Statement stLogin= conLogin.createStatement(); 
        ResultSet rsLogin=stLogin.executeQuery("select * from pengguna where username='"+cari+"'");     

        while(rsLogin.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("user:");

            out.print("<div>");
            out.print(" <a href=\"profile.jsp\">");
            out.print(rsLogin.getString("username"));
            out.print(" </a>");
            out.println("</div>");

            out.print("<div>");
            out.print(rsLogin.getString("tanggal_lahir"));
            out.println("</div>");

            out.print("<div>");
            out.print(rsLogin.getString("nama_lengkap"));
            out.println("</div>");

            out.print("<div>");
            out.print("<img width=\"60px\" height=\"60px\" src=\"");
            out.print(rsLogin.getString("avatar"));
            out.print("\">" );
            out.println("</div>");

            out.println("</div>");
        }//end user 
    }//end username
    
    
    else if(filter.equals("email")){
        Statement stLogin= conLogin.createStatement(); 
        ResultSet rsLogin=stLogin.executeQuery("select * from pengguna where email='"+cari+"'");     

        while(rsLogin.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("email:");

            out.print("<div>");
            out.print(rsLogin.getString("email"));
            out.println("</div>");
            
            out.print("<div>");
            out.print(" <a href=\"profile.jsp\">");
            out.print(rsLogin.getString("username"));
            out.print(" </a>");
            out.println("</div>");          

            out.print("<div>");
            out.print(rsLogin.getString("nama_lengkap"));
            out.println("</div>");

            out.print("<div>");
            out.print("<img width=\"60px\" height=\"60px\" src=\"");
            out.print(rsLogin.getString("avatar"));
            out.print("\">" );
            out.println("</div>");

            out.println("</div>");
        }//end user  
        
    }//end email
    
    
    else if(filter.equals("namalengkap")){
        Statement stLogin= conLogin.createStatement(); 
        ResultSet rsLogin=stLogin.executeQuery("select * from pengguna where nama_lengkap='"+cari+"'");     

        while(rsLogin.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("nama lengkap:");

            out.print("<div>");
            out.print(rsLogin.getString("nama_lengkap"));
            out.println("</div>");            
            
            out.print("<div>");
            out.print(" <a href=\"profile.jsp\">");
            out.print(rsLogin.getString("username"));
            out.print(" </a>");
            out.println("</div>");                   

            out.print("<div>");
            out.print("<img width=\"60px\" height=\"60px\" src=\"");
            out.print(rsLogin.getString("avatar"));
            out.print("\">" );
            out.println("</div>");

            out.println("</div>");
        }//end user          
    }//end namalengkap
    
    else if(filter.equals("birthdate")){
        Statement stLogin= conLogin.createStatement(); 
        ResultSet rsLogin=stLogin.executeQuery("select * from pengguna where tanggal_lahir='"+cari+"'");     

        while(rsLogin.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("tanggal lahir:");

            out.print("<div>");
            out.print(rsLogin.getString("tanggal_lahir"));
            out.println("</div>");
            
            out.print("<div>");
            out.print(" <a href=\"profile.jsp\">");
            out.print(rsLogin.getString("username"));
            out.print(" </a>");
            out.println("</div>");                   
            
            out.print("<div>");
            out.print(rsLogin.getString("nama_lengkap"));
            out.println("</div>");                                    

            out.print("<div>");
            out.print("<img width=\"60px\" height=\"60px\" src=\"");
            out.print(rsLogin.getString("avatar"));
            out.print("\">" );
            out.println("</div>");

            out.println("</div>");
        }//end user          
    }//end birthday
    
    else if(filter.equals("kategori")) { 
        Statement stLogin2= conLogin.createStatement(); 
        ResultSet rsLogin2=stLogin2.executeQuery("select * from kategori where nama_kategori='"+cari+"'");     

        while(rsLogin2.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("kategori:");

            out.print("<div>");
            out.print(rsLogin2.getString("nama_kategori"));
            out.println("</div>");

            out.print("<div>");
            out.print(rsLogin2.getString("username"));
            out.println("</div>");                

            out.println("</div>");
        } //end kategori
    } //end kategori
    
    
    else if(filter.equals("tugas")) { 
        Statement stLogin3= conLogin.createStatement(); 
        ResultSet rsLogin3=stLogin3.executeQuery("select * from tugas natural join kategori where nama_tugas='"+cari+"'");     

        while(rsLogin3.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("tugas:");

            out.print("<div>");
            out.print(" <a href=\"rinciantugas.jsp?id_tugas=");
            out.print(rsLogin3.getString("id_tugas"));
            out.print("\">");
            out.println("</div>");

            out.print("<div>");
            out.print(rsLogin3.getString("nama_tugas"));
            out.println("</div>");

            out.println("</a>");

            out.print("<div>");
            out.print(rsLogin3.getString("deadline"));
            out.println("</div>");

            out.print("<div> tag: ");        
            out.print(rsLogin3.getString("tag"));        
            out.println("</div>");

            out.print("<div> status: ");        
            out.print(rsLogin3.getString("status"));        
            out.println("</div>");

            out.println("<input type=\"checkbox\"> ");

            out.println("</div>");
        }//end tugas
    } //end tugas
    
    
    else if(filter.equals("tag")){
        Statement stLogin4= conLogin.createStatement(); 
        ResultSet rsLogin4=stLogin4.executeQuery("SELECT * FROM tugas NATURAL JOIN kategori WHERE tag='"+cari+"'");     

        while(rsLogin4.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("tag:");

            out.print("<div>");
            out.print(rsLogin4.getString("tag"));
            out.println("</div>");

            out.print("<div>");
            out.print(" <a href=\"rinciantugas.jsp?id_tugas=");
            out.print(rsLogin4.getString("id_tugas"));
            out.print("\">");
            out.println("</div>");

            out.print("<div>");
            out.print(rsLogin4.getString("nama_tugas"));
            out.println("</div>");

            out.println("</a>");

            out.print("<div>");
            out.print(rsLogin4.getString("nama_kategori"));
            out.println("</div>");         

            out.println("</div>");
        } //end tag        
    }//end tag
    
    else if(filter.equals("komentar")){
        Statement stLogin4= conLogin.createStatement(); 
        ResultSet rsLogin4=stLogin4.executeQuery("SELECT * FROM komentar WHERE isi='"+cari+"'");     

        while(rsLogin4.next()) { 
            out.println("<div class=\"listtaskJo\">");
            out.println("komentar:");

            out.print("<div>");
            out.print(rsLogin4.getString("isi"));
            out.println("</div>");
            

            out.print("<div>");
            out.print(rsLogin4.getString("username"));
            out.println("</div>");

            out.println("</a>");

            out.print("<div>");
            out.print(rsLogin4.getString("id_tugas"));
            out.println("</div>");         

            out.println("</div>");
        } //end komentar        
        
    }//end komentar
    
%>


<!----------------------------------------- body ----------------------------------------->                 

        
    <div class="main">
		       
       		<div id="divTugas">
            
            
       
            
            </div> <!-- end divTugas-->
       
    </div> <!-- end div main-->


       
       
<!----------------------------------------- footer ----------------------------------------->                      
	
        <div class="footer">
            Copyright © Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>
        
    </body>
</html>