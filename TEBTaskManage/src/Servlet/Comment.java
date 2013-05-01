package Servlet;

import java.util.*;
import com.google.appengine.api.datastore.*;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Comment {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	public int commentid;
	
	@Persistent
	public String createdate;
	
	@Persistent
	public String message;
	
	@Persistent
	public String username;
	
	@Persistent
	public int taskid;
	
	public Comment(int commentid, String createdate, String message, String username, int taskid) {
		this.commentid = commentid;
		this.createdate = createdate;
		this.message = message;
		this.username = username;
		this.taskid = taskid;
	}
}
