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
public class Task {

    private String id_task;
    private String name;
    private String deadline;
    private String status;
    private String id_category;
    private String creator;

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Task(String id_task, String name, String deadline, String status, String id_category, String creator) {
        this.id_task = id_task;
        this.name = name;
        this.deadline = deadline;
        this.status = status;
        this.id_category = id_category;
        this.creator = creator;
    }
    
    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_task", id_task);
        jsonObj.put("name", name);
        jsonObj.put("deadline", deadline);
        jsonObj.put("status", status);
        jsonObj.put("id_category", id_category);
        jsonObj.put("creator", creator);
        
        return jsonObj;
    }
}
