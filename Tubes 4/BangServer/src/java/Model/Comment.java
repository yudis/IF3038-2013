/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import JSON.JSONObject;

/**
 *
 * @author dell
 */
public class Comment {

    private String id_comment;
    private String id_task;
    private String username;
    private String timestamp;
    private String content;

    public Comment(String id_comment, String id_task, String username, String timestamp, String content) {
        this.id_comment = id_comment;
        this.id_task = id_task;
        this.username = username;
        this.timestamp = timestamp;
        this.content = content;
    }

    public String getId_comment() {
        return id_comment;
    }

    public void setId_comment(String id_comment) {
        this.id_comment = id_comment;
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_comment", id_comment);
        jsonObj.put("id_task", id_task);
        jsonObj.put("username", username);
        jsonObj.put("timestamp", timestamp);
        jsonObj.put("content", content);
        
        return jsonObj;
    }
}
