package client;

import java.util.ArrayList;
import java.util.List;

public class Log {
    private String id_task;
    private String taskname;
    private String deadline;
    private List<String> assignee;
    private List<String> tag;
    private String status;
    private String category;
    
    Log(){
        id_task = null;
        taskname=null;
        deadline = null;
        assignee = new ArrayList<String>();
        tag = new ArrayList<String>();
        status=null;
        category=null;
    }
    
    Log(String a, String b, String c, List<String>d, List<String>e, String f, String g){
        id_task = a;
        taskname=b;
        deadline=c;
        assignee = new ArrayList<String>(d);
        tag = new ArrayList<String>(e);
        status=f;
        category=g;
    }

    public String getId_task() {
        return id_task;
    }

    public String getTaskname() {
        return taskname;
    }

    public List<String> getAssignee() {
        return assignee;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public void setAssignee(List<String> assignee) {
        this.assignee = assignee;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
    
    
    
}
