package servlet;

//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import org.json.*;


//import com.google.gson.*;

import com.mysql.jdbc.MySQLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServletHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;

        PrintWriter out = resp.getWriter();
        
        //Login
        if (req.getParameter("type").equalsIgnoreCase("login")) {
            String username = req.getParameter("login_username");
            String password = req.getParameter("login_password");
            
            URL url = new URL("http://localhost:8084/eurilys4-service/user/login_check?login_username="+username+"&login_password="+password);
            //URL url = new URL("http://eurilys.ap01.aws.af.cm/user/login_check?login_username="+username+"&login_password="+password);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept", "application/json");
            if (httpConn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
            String output;
            String outputObject = "";
            while ((output = br.readLine()) != null) {
                outputObject += output;
            } 
            httpConn.disconnect();
            if ("failed".equals(outputObject)) {
                //Login gagal
                resp.sendRedirect("index.jsp?login_status=failed");
                
            } else {
                //Login berhasil
                HttpSession session = req.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("fullname", outputObject);
                resp.sendRedirect("src/dashboard.jsp");
            }
        } 
        
        //Sign Up
        else if (req.getParameter("type").equalsIgnoreCase("signup")) {
            String username = req.getParameter("signup_username");
            String password = req.getParameter("signup_password");
            //String password_confirm = req.getParameter("signup_confirm_password");
            String fullname = req.getParameter("signup_long_name");
            String email = req.getParameter("signup_email");
            String birthdate = req.getParameter("signup_birth_date");
            //Blob avatar = req.getParameter("signup_avatar_upload");

            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver?");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086", "root", "");
                Statement st = conn.createStatement();
                st.executeUpdate("INSERT INTO user VALUES ('" + username + "','" + password + "','" + fullname + "','" + birthdate + "','NULL','" + email + "')");

                //berhasil sign up
                if (conn != null) {
                    // Set Session 
                    HttpSession session = req.getSession(true);
                    session.setAttribute("username", username);
                    session.setAttribute("fullname", fullname);
                    // Redirect
                    resp.sendRedirect("src/dashboard.jsp");
                } else {
                    //gagal sign up
                    req.setAttribute("result", "failed_register");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        } 
        
        //Add Category
        else if (req.getParameter("type").equalsIgnoreCase("add_category")) {
            Connection conn_category = null;
            ResultSet rs_category = null;
            String categoryID = "";
            String categoryName = req.getParameter("add_category_name");
            String categoryAssigne = req.getParameter("add_category_asignee_name");
            HttpSession session = req.getSession(true);
            String categoryCreator = (String) session.getAttribute("username");
            try {
                // Make connection to database
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver?");
                }
                conn_category = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086", "root", "");

                Statement st = conn_category.createStatement();
                st.executeUpdate("INSERT INTO category (cat_name, cat_creator) VALUES ('" + categoryName + "','" + categoryCreator + "')");

                PreparedStatement stmt_category = conn_category.prepareStatement("SELECT cat_id FROM category WHERE cat_name=?");
                stmt_category.setString(1, categoryName);
                rs_category = stmt_category.executeQuery();
                rs_category.beforeFirst();
                while (rs_category.next()) {
                    categoryID = rs_category.getString("cat_id");
                }
                String[] assigneArray = categoryAssigne.split(",");
                for (int i = 0; i < assigneArray.length; i++) {
                    st.executeUpdate("INSERT INTO cat_asignee (cat_id, username) VALUES ('" + categoryID + "','" + assigneArray[i] + "')");
                }
                //Redirect
                resp.sendRedirect("src/dashboard.jsp");
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
            } finally {
                try {
                    conn_category.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        } 
        
        //Delete Category
        else if (req.getParameter("type").equalsIgnoreCase("delete_category")) {
            Connection conn_category = null;
            ResultSet rs_category = null;
            String categoryID = req.getParameter("delete_category_id");
            String categoryName = req.getParameter("delete_category_name");
            HttpSession session = req.getSession(true);
            List<String> deleteTaskId = new ArrayList<String>();

            /* The following will be executed if confirmed */
            try {
                // Make connection to database
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver - ServletHandler.java delete_category");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? - ServletHandler.java delete_category");
                }
                conn_category = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086", "root", "");

                PreparedStatement stmt_category = conn_category.prepareStatement("SELECT task_id FROM task WHERE cat_name=?");
                stmt_category.setString(1, categoryName);
                rs_category = stmt_category.executeQuery();

                rs_category.beforeFirst();
                //Ada Task pada category tersebut
                while (rs_category.next()) {
                    deleteTaskId.add(rs_category.getString("task_id"));

                    //Delete Task Assignee
                    stmt_category = conn_category.prepareStatement("DELETE FROM task_asignee WHERE task_id=?");
                    stmt_category.setString(1, rs_category.getString("task_id"));
                    stmt_category.executeUpdate();
                }

                //Delete Task
                if (deleteTaskId.size() > 0) {
                    for (int i = 0; i < deleteTaskId.size(); i++) {
                        stmt_category = conn_category.prepareStatement("DELETE FROM task WHERE task_id=?");
                        stmt_category.setString(1, deleteTaskId.get(i));
                        stmt_category.executeUpdate();
                    }
                }

                //Delete Category Assignee
                stmt_category = conn_category.prepareStatement("DELETE FROM cat_asignee WHERE cat_id=?");
                stmt_category.setString(1, categoryID);
                stmt_category.executeUpdate();

                //Delete Category
                stmt_category = conn_category.prepareStatement("DELETE FROM category WHERE cat_id=?");
                stmt_category.setString(1, categoryID);
                stmt_category.executeUpdate();

                //Redirect
                resp.sendRedirect("src/dashboard.jsp");
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
            } finally {
                try {
                    conn_category.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        } 
        
        //add comment
        else if (req.getParameter("type").equalsIgnoreCase("add_comment")) {
            String comment = req.getParameter("CommentBox");
            String taskID = req.getParameter("comment_task_id");
            HttpSession session = req.getSession(true);
            String username = (String) session.getAttribute("username");

            System.out.println("comment : " + comment);
            System.out.println("taskID :" + taskID);
            System.out.println("username " + username);

            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver - add_comment ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? - add_comment");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086", "root", "");
                Statement st = conn.createStatement();
                st.executeUpdate("INSERT INTO `comment`(`comment_content`,`task_id`,`comment_creator`) VALUES ('" + comment + "','" + taskID + "','" + username + "')");
                resp.sendRedirect("src/task_detail.jsp?task_id=" + taskID);
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console - add_comment");
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection - add_comment");
                }
            }
        } 
        
        //edit task
        else if (req.getParameter("type").equalsIgnoreCase("edit_task")) {
            String taskID = req.getParameter("edit_task_id");
            String deadline = req.getParameter("edit_task_deadline");
            String assigneeList = req.getParameter("edit_task_assignee");
            String tagList = req.getParameter("edit_task_tag");

            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver - edit task ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? - edit task");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086", "root", "");
                Statement st = conn.createStatement();

                //Update task_asignee
                String[] assigneArray = assigneeList.split(",");
                for (int i = 0; i < assigneArray.length; i++) {
                    st.executeUpdate("INSERT INTO task_asignee (task_id, username) VALUES ('" + taskID + "','" + assigneArray[i] + "')");
                }

                //Update tag
                String[] tagArray = tagList.split(",");
                for (int i = 0; i < tagArray.length; i++) {
                    st.executeUpdate("INSERT INTO tag (tag_name, task_id) VALUES ('" + tagArray[i] + "','" + taskID + "')");
                }

                //Update task
                st.executeUpdate("UPDATE task SET task_deadline='" + deadline + "' WHERE task_id='" + taskID + "'");

                resp.sendRedirect("src/task_detail.jsp?task_id=" + taskID);

            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console - edit task");
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection - edit task");
                }
            }
        } 
        
        //edit profile
        else if (req.getParameter("type").equalsIgnoreCase("edit_profile")) {
            // baseURL/user/update_profile?username=&password=&fullname=&birthdate=&avatar=
            
            String user_name = req.getParameter("edit_username");
            String password = req.getParameter("password");
            String fullname = req.getParameter("fullname");
            //String fileName = req.getParameter("avatar");
            String birthdate = req.getParameter("birthdate");

            fullname = URLEncoder.encode(fullname, "UTF-8");
            
            URL url = new URL("http://localhost:8084/eurilys4-service/user/update_profile?username="+user_name+"&password="+password+"&fullname="+fullname+"&birthdate="+birthdate+"&avatar=");
            //URL url = new URL("http://eurilys.ap01.aws.af.cm/user/update_profile?username="+user_name+"&password="+password+"&fullname="+fullname+"&birthdate="+birthdate+"&avatar=");

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept", "application/json");
            if (httpConn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
            String output;
            String outputObject = "";
            while ((output = br.readLine()) != null) {
                outputObject += output;
            } 
            httpConn.disconnect();
            if ("1".equals(outputObject)) {
                //berhasil
                resp.sendRedirect("src/profile.jsp?profileupdate=ok");
            } else {
                //failed
                resp.sendRedirect("src/profile.jsp?profileupdate=failed");
            }
            
        } 
        
        else if (req.getParameter("type").equalsIgnoreCase("add_task")) {
            out.write("Servlet - Extracting parameter");
            String task_name = req.getParameter("task_name_input");
            //attachment
            String task_deadline = req.getParameter("deadline_input");
            String assigneeList = req.getParameter("assignee_input");
            String tagList = req.getParameter("tag_input");
            String catName = req.getParameter("addtask_cat_name");
            HttpSession session = req.getSession(true);
            String taskCreator = (String) session.getAttribute("username");
            
            String server = "http://localhost:8084/eurilys4-service/SOAPtest";
            //String service = "http://eurilys.ap01.aws.af.cm/SOAPService";
            
            //try {
                try {
                //DEFINE CONNECTION.
                out.write("Servlet - Define Connection");
                HttpURLConnection   connection = (HttpURLConnection) ( new URL(server).openConnection() );
                                    connection.setDoOutput       (true);
                                    connection.setDoInput        (true);
                                    connection.setRequestMethod  ("POST");
                                    connection.setRequestProperty("SOAPAction", server);
                //CREATE REQUEST.
                out.write("Servlet - Create Request");                    
                String  xml = "<?xml version='1.0'?>"+ 
                        "<SOAP-ENV:Envelope>"+
                            "<SOAP-ENV:Body>"+
                                "<tname>"+task_name+"</tname>"+
                                "<tdeadline>"+task_deadline+"</tdeadline>"+
                                "<tassignee>"+assigneeList+"</tassignee>"+
                                "<ttag>"+tagList+"</ttag>"+
                                "<tcategory>"+catName+"</tcategory>"+
                                "<tcreator>"+taskCreator+"</tcreator>"+
                            "</SOAP-ENV:Body>"+
                        "</SOAP-ENV:Envelope>";
                out.write(xml);
                
                //SEND REQUEST.
                out.write("Servlet - Send Request");
                System.out.println(xml);
                OutputStream        _out  = connection.getOutputStream();
                OutputStreamWriter  wout = new OutputStreamWriter(_out, "UTF-8");
                                    wout.write(xml);
                                    wout.flush();
                                    _out.close();
                
                //READ RESPONSE.
                out.write("Servlet - Read Response");
                InputStream in = connection.getInputStream();
                int c;
                String response = "";
                while ((c = in.read()) != -1) 
                { 
                    response += (char) c; 
                    System.out.println(c);
                }
                System.out.println(response);
                out.write(response);
                
                //EXTRACT RESULT.
                out.write("Servlet - Extract Result");
                int startTag  = response.indexOf("<result>");
                int endTag    = response.indexOf("</result>");
                String parameter = response.substring(startTag,endTag).replaceAll("<result>","");     
                parameter = parameter.trim();
                out.write(parameter);
                
                //DISPLAY RESULT.
                System.out.println("Result="+parameter);
                if (parameter.equals("")){
                    resp.sendRedirect("src/add_task.jsp");
                } else {
                    resp.sendRedirect("src/task_detail.jsp?task_id=" + parameter);
                }

                //CLOSE ALL.
                in        .close();
                out       .close();
                connection.disconnect();
                }
                catch (IOException e) { System.out.println(e.toString()); }                     
            
            
                /*try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver - Add Task ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? - Add Task");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086", "root", "");

                Statement st = conn.createStatement();
                st.executeUpdate("INSERT INTO task(task_name, task_status, task_deadline, cat_name, task_creator) VALUES ('" + task_name + "','0','" + task_deadline + "','" + catName + "','" + taskCreator + "')");

                PreparedStatement stmt = conn.prepareStatement("SELECT task_id FROM task WHERE task_name=? AND cat_name=?");
                stmt.setString(1, task_name);
                stmt.setString(2, catName);
                ResultSet rs = stmt.executeQuery();
                rs.beforeFirst();
                String taskID = "";
                while (rs.next()) {
                    taskID = rs.getString("task_id");
                }

                //Insert Task Attachment
                Part filePart = req.getPart("attachment_file1"); // Retrieves <input type="file" name="file">
                String filename = "";
                filename = getFilename(filePart);
                String dir = "uploads/" + filename;
                byte buf[] = new byte[1024 * 4];
                if (!filename.isEmpty()) {
                    Statement st2 = conn.createStatement();
                    st2.executeUpdate("INSERT INTO `attachment` (`att_content`, `att_task_id`) VALUES ('" + filename + "','" + taskID + "')");
                    FileOutputStream output = new FileOutputStream(getServletContext().getRealPath("/") + "uploads/" + filename);
                    try {
                        InputStream input = filePart.getInputStream();
                        try {
                            while (true) {
                                int count = input.read(buf);
                                if (count == -1) {
                                    break;
                                }
                                output.write(buf, 0, count);
                            }
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                }

                //Insert Task Assignee
                String[] assigneArray = assigneeList.split(",");
                for (int i = 0; i < assigneArray.length; i++) {
                    st.executeUpdate("INSERT INTO task_asignee (task_id, username) VALUES ('" + taskID + "','" + assigneArray[i] + "')");
                }

                //Insert Task Tag
                String[] tagArray = tagList.split(",");
                for (int i = 0; i < tagArray.length; i++) {
                    st.executeUpdate("INSERT INTO tag(tag_name, task_id) VALUES ('" + tagArray[i] + "','" + taskID + "')");
                }
                resp.sendRedirect("src/task_detail.jsp?task_id=" + taskID);
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console - Add Task");
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection - Add Task");
                }
            }*/
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;

        //Finish Task
        if (req.getParameter("type").equalsIgnoreCase("finish_task")) {
            String taskID = req.getParameter("task_id");
            PrintWriter out = resp.getWriter();

            URL url = new URL("http://localhost:8084/eurilys4-service/task/finish_task?task_id="+taskID);
            //URL url = new URL("http://eurilys.ap01.aws.af.cm/task/finish_task?task_id="+taskID);

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept", "application/json");
            if (httpConn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
            String output;
            String outputObject = "";
            while ((output = br.readLine()) != null) {
                outputObject += output;
            } 
            httpConn.disconnect();
            out.println(outputObject);
        } 
        
        //Delete Task
        else if (req.getParameter("type").equalsIgnoreCase("delete_task")) {
            String taskID = req.getParameter("task_id");
            PrintWriter out = resp.getWriter();

            URL url = new URL("http://localhost:8084/eurilys4-service/task/delete_task?task_id="+taskID);
            //URL url = new URL("http://eurilys.ap01.aws.af.cm/task/delete_task?task_id="+taskID);

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept", "application/json");
            if (httpConn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
            String output;
            String outputObject = "";
            while ((output = br.readLine()) != null) {
                outputObject += output;
            } 
            httpConn.disconnect();
            out.println(outputObject);
        } 
        
        //Delete Comment
        else if (req.getParameter("type").equalsIgnoreCase("delete_comment")) {
            String commentID = req.getParameter("comment_id");
            URL url = new URL("http://localhost:8084/eurilys4-service/task/delete_comment?comment_id="+commentID);
            //URL url = new URL("http://eurilys.ap01.aws.af.cm/task/delete_comment?comment_id="+commentID);

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept", "application/json");
            if (httpConn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
            String output;
            String outputObject = "";
            while ((output = br.readLine()) != null) {
                outputObject += output;
            } 
            httpConn.disconnect();
            PrintWriter out = resp.getWriter();
            out.println(outputObject);
        } 
        
        //Delete Category
        else if (req.getParameter("type").equalsIgnoreCase("delete_category")) {
            String categoryID = req.getParameter("category_id");
            URL url = new URL("http://localhost:8084/eurilys4-service/category/delete?category_id="+categoryID);
            //URL url = new URL("http://eurilys.ap01.aws.af.cm/category/delete?category_id="+categoryID);

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept", "application/json");
            if (httpConn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
            String output;
            String outputObject = "";
            while ((output = br.readLine()) != null) {
                outputObject += output;
            } 
            httpConn.disconnect();
            if ("1".equals(outputObject)) {
                //berhasil
                resp.sendRedirect("src/dashboard.jsp");
            } else {
                resp.sendRedirect("src/dashboard.jsp?delete_category=failed");
            }
            
        } 
        
        //Category Task
        else if (req.getParameter("type").equalsIgnoreCase("category_task")) {
            PrintWriter out = resp.getWriter();
            String categoryName = req.getParameter("category_name");
            categoryName = URLEncoder.encode(categoryName);
            
            URL url = new URL("http://localhost:8084/eurilys4-service/task/get_category_task?category_name="+categoryName);
            //URL url = new URL("http://eurilys.ap01.aws.af.cm/task/get_category_task?category_name="+categoryName);

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept", "application/json");
            if (httpConn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
            String output;
            String outputObject = "";
            while ((output = br.readLine()) != null) {
                outputObject += output;
            } 
            httpConn.disconnect();
            
            out.println(outputObject);
        } 
        
        //Search Result
        else if (req.getParameter("type").equalsIgnoreCase("search_result")) {
            PrintWriter out = resp.getWriter();
            String keyword = req.getParameter("keyword");
            String filter = req.getParameter("filter");
            
            URL url = new URL("http://localhost:8084/eurilys4-service/search/result?keyword="+keyword+"&filter="+filter);
            //URL url = new URL("http://eurilys.ap01.aws.af.cm/search/result?keyword="+keyword+"&filter="+filter);

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept", "application/json");
            if (httpConn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
            String output;
            String outputObject = "";
            while ((output = br.readLine()) != null) {
                outputObject += output;
            } 
            httpConn.disconnect();
            
            out.println(outputObject);
        } 
        
        //Edit Task - Delete Assignee
        else if (req.getParameter("type").equalsIgnoreCase("edittask_deleteAssignee")) {
            String taskID = req.getParameter("task_id");
            String userID = req.getParameter("user_id");
            try {
                // Make connection to database
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver -- ServletHandler.java - edit task : delete assignee  ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? -- ServletHandler.java - edit task : delete assignee ");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086", "root", "");

                PreparedStatement st;
                st = conn.prepareStatement("DELETE FROM task_asignee WHERE task_id=? AND username=?");
                st.setString(1, taskID);
                st.setString(2, userID);
                st.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console - edit task : delete assignee ");
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection - edit task : delete assignee ");
                }
            }
        } 
        
        //Edit Task - Delete Tag
        else if (req.getParameter("type").equalsIgnoreCase("edittask_deleteTag")) {
            String taskID = req.getParameter("task_id");
            String tagName = req.getParameter("tag_name");
            try {
                // Make connection to database
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver -- ServletHandler.java - edit task : delete tag ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? -- ServletHandler.java - edit task : delete tag ");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086", "root", "");

                PreparedStatement st;
                st = conn.prepareStatement("DELETE FROM tag WHERE task_id=? AND tag_name=?");
                st.setString(1, taskID);
                st.setString(2, tagName);
                st.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console - edit task : delete tag ");
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection - edit task : delete tag");
                }
            }
        }

    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
