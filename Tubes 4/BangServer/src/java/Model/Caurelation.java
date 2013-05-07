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
public class Caurelation {

    private String id_cau;
    private String id_category;
    private String authorized_user;

    public Caurelation(String id_cau, String id_category, String authorized_user) {
        this.id_cau = id_cau;
        this.id_category = id_category;
        this.authorized_user = authorized_user;
    }

    public String getId_cau() {
        return id_cau;
    }

    public void setId_cau(String id_cau) {
        this.id_cau = id_cau;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getAuthorized_user() {
        return authorized_user;
    }

    public void setAuthorized_user(String authorized_user) {
        this.authorized_user = authorized_user;
    }
    
    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_cau", id_cau);
        jsonObj.put("id_category", id_category);
        jsonObj.put("authorized_user", authorized_user);
        
        return jsonObj;
    }
}
