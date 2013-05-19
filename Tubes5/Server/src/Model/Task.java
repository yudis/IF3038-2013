/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author dell
 */
public class Task {

    private String id_task;
    private String name;
    private String deadline;
    private String status;
    private String creator;
    private String task_category;
    private JSONArray tag_list;

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

    public String getTask_category() {
        return task_category;
    }

    public void setTask_category(String task_category) {
        this.task_category = task_category;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public JSONArray getTag_list(){
    	return tag_list;
    }
    
    public void setTag_list(JSONArray tag_list){
    	this.tag_list = tag_list;
    }

    public Task(String id_task, String name, String deadline, String status, String task_category, String creator, JSONArray tag_list) {
        this.id_task = id_task;
        this.name = name;
        this.deadline = deadline;
        this.status = status;
        this.task_category = task_category;
        this.creator = creator;
        this.tag_list = tag_list;
    }
    
    public Task(JSONObject jsonObj) {
        this.id_task = jsonObj.getString("task_id");
        this.name = jsonObj.getString("task_name");
        this.deadline = jsonObj.getString("task_deadline");
        this.status = jsonObj.getString("task_status");
        this.task_category = jsonObj.getString("task_category");
        this.creator = jsonObj.getString("task_creator");
        this.tag_list = jsonObj.getJSONArray("tag_list");
    }
    
    public String TaskToString(){
    	JSONObject taskObject = new JSONObject();
    	taskObject.put("task_id", id_task);
        taskObject.put("task_name", name);
        taskObject.put("task_status", status);
        taskObject.put("task_deadline", deadline);
        taskObject.put("task_category", task_category);
        taskObject.put("task_creator", creator);
        taskObject.put("tag_list", tag_list);
        
        return taskObject.toString();
    }
}
