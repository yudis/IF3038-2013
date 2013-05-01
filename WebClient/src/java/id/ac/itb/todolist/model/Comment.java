package id.ac.itb.todolist.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONModel;
import org.json.JSONObject;

public class Comment extends JSONModel {

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

    @Override
    public JSONObject toJsonObject() {
        JSONObject jObject = new JSONObject();

        jObject.put("id", id);
        jObject.put("idTugas", idTugas);
        jObject.put("user", user.toJsonObject());
        jObject.put("time", new SimpleDateFormat("dd/MM hh:mm").format(time));
        jObject.put("content", content);

        return jObject;
    }
    
    @Override
    public void fromJsonObject(JSONObject jObject) {
        
        this.id = jObject.getInt("id");
        this.idTugas = jObject.getInt("idTugas");
        JSONObject jsonUser = jObject.getJSONObject("user");
        if (jsonUser != null) {
            this.user = new User();
            this.user.fromJsonObject(jsonUser);
        }
        this.time = java.sql.Timestamp.valueOf(jObject.getString("time"));
        this.content = jObject.getString("content");
    }
}
