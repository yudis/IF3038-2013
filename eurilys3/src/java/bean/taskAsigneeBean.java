
package bean;

public class taskAsigneeBean {
    int task_id;
    String username;

    public taskAsigneeBean() {
    }

    public taskAsigneeBean(int task_id, String username) {
        this.task_id = task_id;
        this.username = username;
    }

    public int getTask_id() {
        return task_id;
    }

    public String getUsername() {
        return username;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
