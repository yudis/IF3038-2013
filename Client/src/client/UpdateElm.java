/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Compaq
 */
public class UpdateElm {
    private String task_id;
    private String task_status;
    
    public UpdateElm(){
        this.task_id = null;
        this.task_status = null;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }
    
    
}
