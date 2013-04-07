package bean;

public class tagBean {
    String tag_name;
    int task_id;

    public tagBean() {
    }

    public tagBean(String tag_name, int task_id) {
        this.tag_name = tag_name;
        this.task_id = task_id;
    }

    //Getter
    public String getTag_name() {
        return tag_name;
    }

    public int getTask_id() {
        return task_id;
    }
    
    //Setter
    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
    
    
}
