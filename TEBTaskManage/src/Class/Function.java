/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import Servlet.*;

import org.apache.jasper.tagplugins.jstl.core.Out;

import java.io.IOException;
import java.io.PrintWriter;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

/**
 *
 * @author ASUS
 */
public class Function {

    public Function() {
    }
    
    public HashMap<String, String> GetUser(String username){
        try {
            HashMap<String, String> user = new HashMap<String, String>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
            Query q = pm.newQuery("select from "+User.class.getName()+ " where username =='"+username+"'");
			List<User> result = (List<User>)q.execute();
			if (!result.isEmpty()) {
				for (User s : result) {
					user.put("username", s.username);
					user.put("password", s.password);
					user.put("fullname", s.fullname);
					user.put("birthday", s.birthday);
					user.put("email", s.email);
					user.put("join", s.join);
					user.put("aboutme", s.aboutme);
					user.put("avatar", s.avatar);
				}
			}
            return user;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public HashMap<String, String> GetTask(String taskId){
        try {
            HashMap<String, String> task = new HashMap<String, String>();
           PersistenceManager pm = PMF.get().getPersistenceManager();
            Query q = pm.newQuery("select from "+Task.class.getName()+ " where taskid =='"+taskId+"'");
			List<Task> result = (List<Task>)q.execute();
			if (!result.isEmpty()) {
				for (Task s : result) {
					task.put("taskid", String.valueOf(s.taskid));
					task.put("taskname", s.taskname);
					task.put("username", s.username);
					task.put("createddate", s.createdate);
					task.put("deadline", s.deadline);
					task.put("status", s.status);
					task.put("categoryid", String.valueOf(s.categoryid));
				}
			}
            return task;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public HashMap<String, String> GetTag(String tagId){
        try {
            HashMap<String, String> tag = new HashMap<String, String>();
            PersistenceManager pm = PMF.get().getPersistenceManager();
            Query q = pm.newQuery("select from "+Tag.class.getName()+ " where taskid =='"+tagId+"'");
			List<Tag> result = (List<Tag>)q.execute();
			if (!result.isEmpty()) {
				for (Tag s : result) {
					tag.put("tagid", String.valueOf(s.tagid));
					tag.put("tagname", s.tagname);
				}
			}
            return tag;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public int GetTypeFile(String fileName){
        int beginIndex = fileName.length() - 3;
        String extension = fileName.substring(beginIndex).toLowerCase();
        System.out.println(fileName);
        System.out.println(extension);
        if(extension.equals("jpg") || extension.equals("png")){
            return 0;
        }else if(extension.equals("mp4")){
            return 1;
        }else{
            return 2;
        }
    }
    
     public String GetNComment(String taskId){
        try {
            PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery("select from "+Comment.class.getName()+" where taskid =='"+taskId+"'");
			List<Comment> result = (List<Comment>)q.execute();
			int n = result.size();
			q.closeAll();
			pm.close();
            return n+"";
        } catch (Exception exc) {
            System.out.println(exc.toString());
            return "Error : "+exc.toString();
        }
    }
    
    public String getCategoryName(String categoryid) 
    {
        String toOut ="";
        try{
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery("select categoryname from "+Category.class.getName()+" where categoryid =='"+categoryid+"'");
			List<String> result = (List<String>)q.execute();
            if (!result.isEmpty()) {
				toOut = result.get(0);
			}
			q.closeAll();
			pm.close();
        }
		catch(Exception e){
        }
        return toOut;
    }
     
    public String getTaskId(String taskname,String categoryid){
        String toOut ="";
        try{
            PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery("select taskid from "+Task.class.getName()+" where taskname =='"+taskname+"' and categoryid =='"+categoryid+"'");
			List<String> result = (List<String>)q.execute();
            if (!result.isEmpty()) {
				toOut = result.get(0);
			}
			q.closeAll();
			pm.close();
        }catch(Exception e){
        }
        return toOut;
    }
    
    public boolean isAssignee(String useractive,String taskid){
        boolean rs = false;
        try{
            PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery("select from "+Assignee.class.getName()+" where username =='"+useractive+"' and taskid =="+taskid+"'");
			List<Assignee> result = (List<Assignee>)q.execute();
            if (!result.isEmpty()) {
				rs = true;
			}
			q.closeAll();
			pm.close();
        }catch(Exception e){
            
        }
        return rs;
    }
    
    public String getTagname(String tagid){
        String toOut ="";
        try{
            PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery("select tagname from "+Tag.class.getName()+" where tagid='"+tagid+"'");
			List<String> result = (List<String>)q.execute();
            if (!result.isEmpty()) {
				toOut = result.get(0);
			}
			q.closeAll();
			pm.close();
        }catch(Exception e){
        }
        return toOut;
    }
    
    public boolean isResponsibility(String categoryid, String useractive){
        boolean rs = false;
        try{
            PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery("select from "+Responsibility.class.getName()+" where categoryid =='"+categoryid+"' and username='"+useractive+"'");
			List<Responsibility> result = (List<Responsibility>)q.execute();
            if (!result.isEmpty()) {
				rs = true;
			}
			q.closeAll();
			pm.close();
        }catch(Exception e){
        }
        return rs;
    }
    
    public String getNextCommentId(){
		int maxId = 0;
		try {
            PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery("select commentid from "+Comment.class.getName());
			List<String> result = (List<String>)q.execute();
            if (!result.isEmpty()) {
				for (String s : result) {
					if (maxId < Integer.parseInt(s)) {
						maxId = Integer.parseInt(s);
					}
				}
			}
			q.closeAll();
			pm.close();
			maxId++;
            return maxId+"";
        } catch (Exception exc) {
            System.out.println(exc.toString());
            return ""+0;
        }
    }

     
    public String GetTagId(String tagname){
		int maxId = 0;
		try {
            PersistenceManager pm = PMF.get().getPersistenceManager();
			String tagid = "";
            if (IsTagExist(tagname)){
                Query q = pm.newQuery("select tagid from "+Tag.class.getName()+" where tagname =='"+tagname+"'");
				List<String> result = (List<String>)q.execute();
				if (!result.isEmpty()) {
					tagid = result.get(0);
				}
				q.closeAll();
				pm.close();
                return tagid;
            }else{ 
                Query q = pm.newQuery("select tagid from "+Tag.class.getName());
				List<String> result = (List<String>)q.execute();
				if (!result.isEmpty()) {
					for (String s : result) {
						if (maxId < Integer.parseInt(s)) {
							maxId = Integer.parseInt(s);
						}
					}
				}
				q.closeAll();
				maxId++;
				
				Tag tag = new Tag(maxId, tagname);
				try {
					pm.makePersistent(tag);
				} catch (Exception e) {
					
				} finally {
					pm.close();
				}

                return GetTagId(tagname);
            }
        } catch (Exception ex) {
			return "Error : "+ex.toString();
        }
	}
        
	public boolean IsTagExist(String tagname){
            boolean bool = false;
			try {
                PersistenceManager pm = PMF.get().getPersistenceManager();
				Query q = pm.newQuery("select tagid from "+Tag.class.getName()+" where tagname =='"+tagname+"'");
				List<String> result = (List<String>)q.execute();
				if (!result.isEmpty()) {
					bool = true;
				}
				q.closeAll();
				pm.close();
            } catch (Exception ex) {
                return false;
            }
			return bool;
	}

    public String GetNextCategoryId() throws Exception{
        String toOut = "";
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery("select categoryid from "+Category.class.getName());
			List<String> result = (List<String>)q.execute();
			int maxId = 0;
            if (!result.isEmpty()) {
				for (String s : result) {
					if (maxId < Integer.parseInt(s)) {
						maxId = Integer.parseInt(s) + 1;
					}
				}
			}
			q.closeAll();
			pm.close();
			toOut = maxId+"";
		} catch (Exception ex) {
		
		}
        return toOut;
    }
	
    public String GetNextTaskId() throws Exception{
		String toOut = "";
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery("select taskid from "+Task.class.getName());
			List<String> result = (List<String>)q.execute();
			int maxId = 0;
            if (!result.isEmpty()) {
				for (String s : result) {
					if (maxId < Integer.parseInt(s)) {
						maxId = Integer.parseInt(s) + 1;
					}
				}
			}
			q.closeAll();
			pm.close();
			toOut = maxId+"";
		} catch (Exception ex) {
		
		}
        return toOut;
    }
}