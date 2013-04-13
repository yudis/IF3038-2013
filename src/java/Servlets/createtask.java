/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import ConnectDB.ConnectDB;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@MultipartConfig(location = "/attachment", fileSizeThreshold = 1024 * 1024 *50, maxFileSize = 1024 * 1024 * 50 ,maxRequestSize = 1024 * 1024 * 50 * 5)
/**
 *
 * @author PANDU
 */
public class createtask extends HttpServlet {
    
    public String[] allowedExts;
    public String namaFile;
    public String folder;
    public String path;
    public String idtugas;
    public String tes2;
    
    
    String taskname;
    String attachment;
    String deadline;
    String assignee;
    String tag;
    String kategori;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //processRequest(request, response);
            
//            setAssignee(request.getParameter("assignee"));
//            setAttachment(request.getParameter("attachment"));
//            setDeadline(request.getParameter("deadline"));
//            setTag(request.getParameter("tag"));
//            setTaskname(request.getParameter("taskname"));
            
            setAssignee2(request);
            setDeadline2(request);
            setTag2(request);
            setTaskname2(request);
            setKategori2(request);
            
            String x = Create() ;
            setAttachment2(request, response, x);
            response.sendRedirect("lihattask.jsp?tujuan=lihat&id="+x);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(createtask.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(createtask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private String getFilename(Part part) {
         for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, ClassNotFoundException, SQLException {
       
            response.setContentType("text/html;charset=UTF-8");
            Statement s = null;
            ResultSet rs =null;
            Connection con = null;
            String uploadTo = getServletContext().getRealPath("/") + "images/";
            Part p;
//        try { 
//            
//        } catch (IOException ex) {
//            Logger.getLogger(createtask.class.getName()).log(Level.SEVERE, null, ex);
//        }  finally {            
//            //out.close();
//            if (con != null) {
//                try {
//                    con.close();
//                    con = null;
//                } catch (SQLException ex) {
//                    Logger.getLogger(createtask.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            if (s != null) {
//                try {
//                    s.close();
//                    s = null;
//                } catch (SQLException ex) {
//                    Logger.getLogger(createtask.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            if (rs != null) {
//                try {
//                    rs.close();
//                    rs = null;
//                } catch (SQLException ex) {
//                    Logger.getLogger(createtask.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
    }
    /**/
    public createtask () {
        allowedExts = new String[]{"jpg", "jpeg", "gif", "png", "avi", "mp4", "flv", "3gp", "wmv"};
        namaFile = "";
        folder = "attachment/";
        path = "";
    }
    
    public void setTaskname( String value ) { taskname = value; }
    public void setAttachment( String value ) { attachment = value; }
    public void setDeadline( String value ) { deadline = value; }
    public void setAssignee( String value ) { assignee = value; }
    public void setTag( String value ) { tag = value; }
    
    public void setTaskname2(HttpServletRequest request) throws IOException, ServletException { 
        Part p;
        p = request.getPart("taskname");
        Scanner scan = new Scanner(p.getInputStream());
        if (scan.hasNext()) {
            taskname = scan.nextLine();
        } else {
            taskname = "null";
        }
        scan.close();
    }
    public void setDeadline2(HttpServletRequest request) throws IOException, ServletException {
        Part p;
        p = request.getPart("deadline");
        Scanner scan = new Scanner(p.getInputStream());
        if (scan.hasNext()) {
            deadline = scan.nextLine();
        } else {
            deadline = "null";
        }
        scan.close();
    }
    public void setAssignee2(HttpServletRequest request) throws IOException, ServletException { 
        Part p;
        p = request.getPart("assignee");
        Scanner scan = new Scanner(p.getInputStream());
        if (scan.hasNext()) {
            assignee = scan.nextLine();
        } else {
            assignee = "null";
        }
        scan.close();
    }
    public void setTag2(HttpServletRequest request) throws IOException, ServletException { 
        Part p;
        p = request.getPart("tag");
        Scanner scan = new Scanner(p.getInputStream());
        if (scan.hasNext()) {
            tag = scan.nextLine();
        } else {
            tag = "null";
        }
        scan.close();
    }
    public void setAttachment2(HttpServletRequest request, HttpServletResponse response, String UID) throws IOException, ServletException, ClassNotFoundException, SQLException { 
        response.setContentType("text/html;charset=UTF-8");
        Scanner scan;
        //Part p;
        Part[] LP = new Part[0];
        LP = request.getParts().toArray(LP);
        
        // Buat Path penyimpanan
        path = folder + taskname + "-" + (int)(Math.random()*1000000);
                
        //Ambil file name
        for (Part p : LP) {
            if (getFilename(p)!=null) {
                InputStream is = p.getInputStream();
                String namafile = getFilename(p);

                // Update DB Attachment
                String sql = "INSERT INTO attachment (nama, path, tugas_idtugas) ";
                sql += "VALUES ('"+namafile+"', '"+path+"', '"+UID+"')";
                ConnectDB.jalankanQuery(sql);

                // Upload Attachment
                String outputfile = this.getServletContext().getRealPath(path+namafile);  // get path on the server
                FileOutputStream os = new FileOutputStream (outputfile);
                // write bytes taken from uploaded file to target file
                int ch = is.read();
                while (ch != -1) {
                        os.write(ch);
                        ch = is.read();
                }
                os.close();
            }
        }
    }
    public void setKategori2(HttpServletRequest request) throws IOException, ServletException {
        Part p;
        p = request.getPart("kategori");
        Scanner scan = new Scanner(p.getInputStream());
        if (scan.hasNext()) {
            kategori = scan.nextLine();
        } else {
            kategori = "null";
        }
        scan.close();
    }
    
    public String getTaskname() { return taskname; }
    public String getAttachment() { return attachment; }
    public String getDeadline() { return deadline; }
    public String getAssignee() { return assignee; }
    public String getTag() { return tag; }
    
    public String Create () throws IOException, ServletException, SQLException  {
        
        String sql = "";
        String[][] hasil;
        
        // TAMBAH Tugas
        sql = "INSERT INTO tugas (nama, deadline, status_selesai, kategori_idkategori) ";
        sql += "VALUES ('"+taskname+"', '"+deadline+"', '0', '"+kategori+"')";
        ConnectDB.jalankanQuery(sql);
        // --> kategori
        
        // Ambil ID tugas buat ForKey Attachment
        sql = "SELECT idtugas FROM tugas ORDER BY idtugas DESC LIMIT 1";
        hasil = ConnectDB.getHasilQuery(sql);
        idtugas = hasil[0][0];
        
        
        // TAMBAH Tag
        TambahTag();
        
        // TAMBAH Assignee
        TambahAssignee();        
        
        return idtugas;
    }
    
    public void TambahAssignee () throws IOException, ServletException, SQLException {
        String[] t = assignee.split(",");
        for (String i : t) {
            if (i!="") {
                String sql = "SELECT idaccounts FROM accounts WHERE username = '"+i+"'";
                String[][] hasil = ConnectDB.getHasilQuery(sql);
                String[] fetch = hasil[0];
                sql = "INSERT ";
                sql += "INTO accounts_has_tugas (accounts_idaccounts, tugas_idtugas, pembuat)";
                sql += "VALUES ('"+fetch[0]+"','"+idtugas+"','0');";
                ConnectDB.jalankanQuery(sql);
            }
        }
    }
    private void TambahTag () throws IOException, ServletException, SQLException {
        String[] t = tag.split(",");
        for (String i : t) {
            if (i!="") {
                String sql = "SELECT idtag FROM tag WHERE nama = '"+i+"'";
                String[][] hasil = ConnectDB.getHasilQuery(sql);
                String[] fetch;
                if (hasil.length==0) { 
                    sql = "INSERT ";
                    sql += "INTO tag (nama)";
                    sql += "VALUES ('"+i+"');";
                    ConnectDB.jalankanQuery(sql);
                    sql = "SELECT idtag FROM tag WHERE nama = '"+i+"' ";
                    hasil = ConnectDB.getHasilQuery(sql);
                }
                fetch = hasil[0];
                sql = "INSERT ";
                sql += "INTO tugas_has_tag (tag_idtag, tugas_idtugas)";
                sql += "VALUES ('"+fetch[0]+"','"+idtugas+"');";
                ConnectDB.jalankanQuery(sql);
            }
        }        
    }
    
    public boolean tes() throws IOException, ServletException, SQLException {
        String[] t = assignee.split(",");
        String sql = "SELECT idtag FROM tag WHERE nama = '"+t[0]+"'";
        String[][] hasil = ConnectDB.getHasilQuery(sql);
        //String[] fetch = hasil[0];
        
        return (hasil.length==0);
    }
}
