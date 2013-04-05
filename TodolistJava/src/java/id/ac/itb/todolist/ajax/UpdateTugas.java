package id.ac.itb.todolist.ajax;

import id.ac.itb.todolist.dao.CommentDao;
import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.json.JSONArray;
import id.ac.itb.todolist.json.JSONObject;
import id.ac.itb.todolist.model.Comment;
import id.ac.itb.todolist.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateTugas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        JSONObject jObject = new JSONObject();
        try {
            if (request.getParameter("removec") != null && request.getParameter("id_komentar") != null) {
                CommentDao commentDao = new CommentDao();
                int idComment = Integer.parseInt(request.getParameter("id_komentar"));

                Comment comment = commentDao.getCommentById(idComment);
                if (((User) session.getAttribute("user")).getUsername().equals(comment.getUser().getUsername())) {
                    commentDao.deleteComment(idComment);
                    TugasDao tugasDao = new TugasDao();
                    tugasDao.updateTimestamp(comment.getIdTugas());

                    jObject.put("responseStatus", 200);
                } else {
                    jObject.put("responseStatus", 403);
                    jObject.put("message", "Forbidden");
                }
            } else if (request.getParameter("addc") != null && request.getParameter("id_tugas") != null && request.getParameter("content") != null) {
                CommentDao commentDao = new CommentDao();
                Comment comment = new Comment();

                int idTugas = Integer.parseInt(request.getParameter("id_tugas"));
                String content = request.getParameter("content");

                comment.setIdTugas(idTugas);
                comment.setUser((User) session.getAttribute("user"));
                comment.setContent(content);
                commentDao.addComment(comment);

                TugasDao tugasDao = new TugasDao();
                tugasDao.updateTimestamp(idTugas);

                jObject.put("responseStatus", 200);
            } else if (request.getParameter("chstatus") != null && request.getParameter("id_tugas") != null && request.getParameter("status") != null) {
                TugasDao tugasDao = new TugasDao();
                
                int idTugas = Integer.parseInt(request.getParameter("id_tugas"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                
                tugasDao.setStatus(idTugas, status);

                jObject.put("responseStatus", 200);
            } else if (request.getParameter("updatedeadline") != null && request.getParameter("id_tugas") != null && request.getParameter("deadline") != null) {
                TugasDao tugasDao = new TugasDao();
                
                int idTugas = Integer.parseInt(request.getParameter("id_tugas"));
                java.sql.Date deadline = java.sql.Date.valueOf(request.getParameter("deadline"));
                
                tugasDao.setDeadline(idTugas, deadline);

                jObject.put("responseStatus", 200);
            } else if (request.getParameter("removea") != null && request.getParameter("id_tugas") != null && request.getParameter("username") != null) {
                TugasDao tugasDao = new TugasDao();
                
                int idTugas = Integer.parseInt(request.getParameter("id_tugas"));
                String username = request.getParameter("username");
                
                tugasDao.removeAssignee(idTugas, username);

                if (username.equals(((User) session.getAttribute("user")).getUsername())) {
                    jObject.put("killself", 1);
                } else {
                    jObject.put("killself", 0);
                }

                jObject.put("responseStatus", 200);
            } else if (request.getParameter("adda") != null && request.getParameter("id_tugas") != null && request.getParameter("username") != null) {
                TugasDao tugasDao = new TugasDao();
                
                int idTugas = Integer.parseInt(request.getParameter("id_tugas"));
                String username = request.getParameter("username");
                
                tugasDao.addAssignee(idTugas, username);
                tugasDao.updateTimestamp(idTugas);

                jObject.put("responseStatus", 200);
            } else if (request.getParameter("suggesta") != null && request.getParameter("id_tugas") != null && request.getParameter("start") != null) {
                TugasDao tugasDao = new TugasDao();
                
                int idTugas = Integer.parseInt(request.getParameter("id_tugas"));
                String startWith = request.getParameter("start") + "%";
                
                jObject.put("suggestedAssignees", new JSONArray(tugasDao.getSuggestionAssignees(idTugas, startWith, 10)));
                jObject.put("responseStatus", 200);
            }
//            
//            } else if (request.getParameter("addt") != null && request.getParameter("id_tugas") != null && request.getParameter("tag") != null) {
//                $tugas = new Tugas();
//                $tugas - > addTag($_GET["id_tugas"], $_GET["tag");
//                $tugas - > updateTimestamp($_GET["id_tugas");
//                jObject = Array();
//                jObject.put("responseStatus", 200);
//            } else if (request.getParameter("suggestt") != null && request.getParameter("id_tugas") != null && request.getParameter("start") != null) {
//                $tugas = new Tugas();
//                jObject = Array();
//                jObject.put("responseStatus", 200);
//                jObject.put("suggestedTags", $tugas - > getSuggestionTags($_GET["id_tugas"], $_GET["start"] . 
//              '%', 10));
//            } else if (request.getParameter("removet") != null && request.getParameter("id_tugas") != null && request.getParameter("tag") != null) {
//                $tugas = new Tugas();
//                $tugas - > removeTag($_GET["id_tugas"], $_GET["tag");
//                $tugas - > updateTimestamp($_GET["id_tugas");
//                jObject = Array();
//                jObject.put("responseStatus", 200);
//            } else if (request.getParameter("removetugas") != null && request.getParameter("id_tugas") != null) {
//                $tugas = new Tugas();
//                $tugas - > deleteTask($_GET["id_tugas");
//                jObject = Array();
//                jObject.put("responseStatus", 200);
//            } else {
//                jObject.put("responseStatus", 400);
//                jObject.put("message", "Bad request");
//            }
        } catch (Exception e) {
            jObject.put("responseStatus", 400);
            jObject.put("message", e.getMessage());
        }

        out.print(jObject);
    }
}
