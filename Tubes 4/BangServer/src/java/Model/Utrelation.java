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
public class Utrelation {

    private String id_ut;
    private String id_task;
    private String username;

    public Utrelation(String id_ut, String id_task, String username) {
        this.id_ut = id_ut;
        this.id_task = id_task;
        this.username = username;
    }

    public String getId_ut() {
        return id_ut;
    }

    public void setId_ut(String id_ut) {
        this.id_ut = id_ut;
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
    
    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_ut", id_ut);
        jsonObj.put("id_task", id_task);
        jsonObj.put("username", username);
        
        return jsonObj;
    }
}
