/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package progin5;

/**
 *
 * @author Krisna Dibyo Atmojo <krisnadibyo@gmail.com>
 */
public class Task {
    private String TaskID;
    private String TaskName;
    private String Deadline;
    private String Assignee;
    private boolean Status;
    private String Tag;
    private String Kategori;
    private String TimeStamp;

    public Task(String taskid,String taskname,String deadline, boolean stat,String tag,String kategori,String timestamp,String assignee) {
        TaskID = taskid;
        TaskName = taskname;
        Deadline = deadline;
        Assignee = assignee;
        Status = stat;
        Tag = tag;
        Kategori = kategori;
        TimeStamp = timestamp;
    }

    public String GetTaskID() {
        return TaskID;
    }
    public String GetTaskName() {
        return TaskName;
    }
    public String GetDeadline() {
        return Deadline;
    }
    public String GetAssignee() {
        return Assignee;
    }
    public boolean GetStatus() {
        return Status;
    }
    public String GetTag() {
        return Tag;
    }
    public String GetKategori() {
        return Kategori;
    }
    public String GetTimeStamp() {
        return TimeStamp;
    }


    public void SetStatus(boolean setStat) {
        Status = setStat;
    }

    public void SetTimeStamp(String time) {
        TimeStamp = time;
    }


}
