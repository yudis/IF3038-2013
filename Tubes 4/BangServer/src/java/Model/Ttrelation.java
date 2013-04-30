/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import JSON.JSONObject;

/**
 *
 * @author dell
 */
public class Ttrelation {

    private String id_tt;
    private String id_task;
    private String id_tag;

    public Ttrelation(String id_tt, String id_task, String id_tag) {
        this.id_tt = id_tt;
        this.id_task = id_task;
        this.id_tag = id_tag;
    }

    public String getId_tt() {
        return id_tt;
    }

    public void setId_tt(String id_tt) {
        this.id_tt = id_tt;
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public String getId_tag() {
        return id_tag;
    }

    public void setId_tag(String id_tag) {
        this.id_tag = id_tag;
    }
    
    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_tt", id_tt);
        jsonObj.put("id_task", id_task);
        jsonObj.put("id_tag", id_tag);
        
        return jsonObj;
    }
}
