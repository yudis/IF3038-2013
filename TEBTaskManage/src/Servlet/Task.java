package Servlet;

import java.util.*;
import com.google.appengine.api.datastore.*;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Task {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	public int taskid;
	
	@Persistent
	public String taskname;
	
	@Persistent
	public String username;
	
	@Persistent
	public String createdate;
	
	@Persistent
	public String deadline;
	
	@Persistent
	public String status;
	
	@Persistent
	public int categoryid;
	
	public Task(int taskid, String taskname, String username, String createdate, String deadline, String status, int categoryid) {
		this.taskid = taskid;
		this.taskname = taskname;
		this.username = username;
		this.createdate = createdate;
		this.deadline = deadline;
		this.status = status;
		this.categoryid = categoryid;
	}
}
