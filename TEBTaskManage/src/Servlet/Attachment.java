package Servlet;

import java.util.*;
import com.google.appengine.api.datastore.*;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Attachment {
	@Persistent
	public String filename;
	
	@Persistent
	public int taskid;
	
	public Attachment(String filename, int taskid) {
		this.filename = filename;
		this.taskid = taskid;

	}
}
