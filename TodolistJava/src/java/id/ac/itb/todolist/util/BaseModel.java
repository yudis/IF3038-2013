/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 *
 * @author Edward Samuel
 */
public class BaseModel {
    
    public JsonElement toJsonElement() {
        Gson gson = new Gson();
        return gson.toJsonTree(this);
    }
}
