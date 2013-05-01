package Servlet;

import java.util.*;
import com.google.appengine.api.datastore.*;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Task_Tag {
	@Persistent
	public int taskid;
	
	@Persistent
	public int tagid;
	
	public Task_Tag(int taskid, int tagid) {
		this.taskid = taskid;
		this.tagid = tagid;

	}
}
