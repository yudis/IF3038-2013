/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author Krisna Dibyo Atmojo <krisnadibyo@gmail.com>
 */
public class Client {
    private String ClientID;
    private ArrayList<Task> listTask;
    

    public Client(String id) {
        ClientID = id;
        listTask = new ArrayList<Task>();
    }
    public String getClientID() {
        return ClientID;
    }
    
    public void setClientID(String id) {
        ClientID = id;
    }

    public void AddTask(Task e) {
        listTask.add(e);
    }
    public void DisposeTaskList() {
        listTask.clear();
    }

    public void UpdateTask(String idtask,boolean b,String time) {
        for (Task a : listTask) {
            if (a.GetTaskID().equals(idtask)) {
                a.SetStatus(b);
                a.SetTimeStamp(time);
            }
        }
    }

    public ArrayList<Task> getTaskList() {
        return listTask;
    }


}
