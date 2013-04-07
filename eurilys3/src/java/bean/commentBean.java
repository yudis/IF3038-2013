package bean;

import java.security.Timestamp;

public class commentBean {
    int comment_id;
    Timestamp comment_timestamp;
    String comment_content;
    int comment_task_id;
    String comment_creator;

    public commentBean() {
    }

    public commentBean(int comment_id, Timestamp comment_timestamp, String comment_content, int comment_task_id, String comment_creator) {
        this.comment_id = comment_id;
        this.comment_timestamp = comment_timestamp;
        this.comment_content = comment_content;
        this.comment_task_id = comment_task_id;
        this.comment_creator = comment_creator;
    }
    
    //Getter
    public int getComment_id() {
        return comment_id;
    }

    public Timestamp getComment_timestamp() {
        return comment_timestamp;
    }

    public String getComment_content() {
        return comment_content;
    }

    public int getComment_task_id() {
        return comment_task_id;
    }

    public String getComment_creator() {
        return comment_creator;
    }
    
    //Setter
    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public void setComment_timestamp(Timestamp comment_timestamp) {
        this.comment_timestamp = comment_timestamp;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public void setComment_task_id(int comment_task_id) {
        this.comment_task_id = comment_task_id;
    }

    public void setComment_creator(String comment_creator) {
        this.comment_creator = comment_creator;
    }
    
}
