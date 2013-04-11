/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.progin.todo;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author kamilersz
 */
public class Function {
    public static HashMap<String,Object> findById(String table, String id) {
        String [] param = {id};
        return Query.one("select * from "+table+" where "+table+"_id = ?",param);
    }
    public static String getUserName(String user_id) {
        HashMap<String,Object> row = findById("user",user_id);
        return (String) row.get("name");
    }
    public static String getUserBirthdate(String user_id) {
        HashMap<String,Object> row = findById("user",user_id);
        return row.get("birthdate").toString();
    }
    public static String getUserEmail(String user_id) {
        HashMap<String,Object> row = findById("user",user_id);
        return (String) row.get("email");
    }
    public static String getUserUserName(String user_id) {
        HashMap<String,Object> row = findById("user",user_id);
        return (String) row.get("username");
    }
    public static String getTagName(String tag_id) {
        HashMap<String,Object> row = findById("tag",tag_id);
        return (String) row.get("name");
    }

    public static void addAssignee(String name, String task_id, String category_id) {
        String[] param = {name};
        HashMap<String,Object> user_id = Query.one("select user_id from user where name = ?", param);
        if (user_id != null) {
            String[] param2 = {task_id,category_id,user_id.get("user_id").toString()};
            Query.n("INSERT into assign (task_id,category_id,user_id) values (?,?,?)",param2);
        }
    }

    public static void addTag(String task_id, String name) {
        String[] param = {name};
        Integer tag_id;
        HashMap<String,Object> tag = Query.one("select * from tag where name = ?", param);
        if (tag == null) {
            tag_id = Query.nid("insert into tag (name) values (?)",param);
        } else {
            tag_id = Integer.parseInt(tag.get("tag_id").toString());
        }
        String[] param2 = {tag_id.toString(),task_id};
        HashMap<String,Object> exist = Query.one("select * from tags where tag_id = ? and task_id = ?", param2);
        if (exist == null) {
            String[] param3 = {tag_id.toString(),task_id};
            Query.n("insert into tags (tag_id,task_id) values (?,?)",param3);
        }
    }
    
    public static Boolean isAssignee(String task_id, String user_id) {
        /*String[] param = {name};
        HashMap<String,Object> user_id = Query.one("select user_id from user where name = ?", param);
        if (user_id != null) {
            HashMap<String,Object> assign;
            if (task_id == null) {
                String[] param2 = {task_id,user_id.get("user_id").toString()};
                assign = Query.one("select * from assign where task_id = ? and user_id = ?",param2);
            } else {
                String[] param2 = {category_id,user_id.get("user_id").toString()};
                assign = Query.one("select * from assign where category_id = ? and user_id = ?",param2);
            }
            return (Integer) assign.get("id");
        } else {
            return null;
        }*/
        return true;
    }
    
    public static Integer findAssignee(String name, String task_id, String category_id) {
        String[] param = {name};
        HashMap<String,Object> user_id = Query.one("select user_id from user where name = ?", param);
        if (user_id != null) {
            HashMap<String,Object> assign;
            if (task_id == null) {
                String[] param2 = {task_id,user_id.get("user_id").toString()};
                assign = Query.one("select * from assign where task_id = ? and user_id = ?",param2);
            } else {
                String[] param2 = {category_id,user_id.get("user_id").toString()};
                assign = Query.one("select * from assign where category_id = ? and user_id = ?",param2);
            }
            return (Integer) assign.get("id");
        } else {
            return null;
        }
    }
    
    public static void delAssignee(String name, String task_id, String category_id) {
        Integer id = findAssignee(name, task_id, category_id);
        if (id != null) {
            String[] param = {id.toString()};
            Query.n("delete from assign where id = ?",param);
        }
    }
    
    public static void delCategory(String category_id) {
        String[] param = {category_id};
        List tasks = Query.all("select task_id from task where category_id = ?",param);
        
        for (Iterator it = tasks.iterator(); it.hasNext();) {
            HashMap<String, Object> r = (HashMap<String, Object>) it.next();
            delTask(r.get("task_id").toString());
        }
        
        Query.n("DELETE from assign where category_id = ?",param);
        Query.n("DELETE from category where category_id = ?",param);
    }

    public static void delTask(String task_id) {
        String[] param = {task_id};
        
        Query.n("DELETE from tags where task_id = ?",param);
        Query.n("DELETE from comment where task_id = ?",param);
        Query.n("DELETE from assign where task_id = ?",param);
        Query.n("DELETE from attachment where task_id = ?",param);
        Query.n("DELETE from task where task_id = ?",param);
    }

}
