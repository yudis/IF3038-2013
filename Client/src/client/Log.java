package client;

import java.util.ArrayList;
import java.util.List;

public class Log {
    private String taskname;
    private List<String> assignee;
    private String category;
    private String status;
    private List<String> tags;
    
    Log(String a, List<String>b, String c, String d, List<String> e){
        taskname=a;
        assignee = new ArrayList<String>(b);
        category=c;
        status=d;
        tags = new ArrayList<String>(e);
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

    public List<String> getTags() {
        return tags;
    }

    
    
}
