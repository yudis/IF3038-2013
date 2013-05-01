package Servlet;

import java.util.*;
import com.google.appengine.api.datastore.*;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Assignee {
	@Persistent
	public String username;
	
	@Persistent
	public int taskid;
	
	public Assignee(String username, int taskid) {
		this.username = username;
		this.taskid = taskid;

	}
}
