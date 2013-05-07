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
public class Tag {

    private String id_tag;
    private String name;

    public Tag(String id_tag, String name) {
        this.id_tag = id_tag;
        this.name = name;
    }

    public String getId_tag() {
        return id_tag;
    }

    public void setId_tag(String id_tag) {
        this.id_tag = id_tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_tag", id_tag);
        jsonObj.put("name", name);
        
        return jsonObj;
    }
}
