package bean;
import java.util.Date;
public class taskBean {
    int task_id;
    String task_name;
    int task_status; //0 not done. 1 done.
    Date task_deadline;
    String category_name;
    String task_creator;

    public taskBean() {
    }

    public taskBean(int task_id, String task_name, int task_status, Date task_deadline, String category_name, String task_creator) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_status = task_status;
        this.task_deadline = task_deadline;
        this.category_name = category_name;
        this.task_creator = task_creator;
    }
    
    //Getter
    public int getTask_id() {
        return task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public int getTask_status() {
        return task_status;
    }

    public Date getTask_deadline() {
        return task_deadline;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getTask_creator() {
        return task_creator;
    }
    
    //Setter

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setTask_status(int task_status) {
        this.task_status = task_status;
    }

    public void setTask_deadline(Date task_deadline) {
        this.task_deadline = task_deadline;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setTask_creator(String task_creator) {
        this.task_creator = task_creator;
    }
}
