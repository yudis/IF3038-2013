package id.ac.itb.todolist.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Comment {

    private int id;
    private int idTugas;
    private User user;
    private Timestamp time;
    private String content;

    public Comment() {
    }

    public Comment(int id, int idTugas, User user, Timestamp time, String content) {
        this.id = id;
        this.idTugas = idTugas;
        this.user = user;
        this.time = time;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTugas() {
        return idTugas;
    }

    public void setIdTugas(int idTugas) {
        this.idTugas = idTugas;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
