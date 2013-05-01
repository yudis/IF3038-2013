package Servlet;

import java.util.*;
import com.google.appengine.api.datastore.*;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Responsibility {
	@Persistent
	public String username;
	
	@Persistent
	public int categoryid;
	
	public Responsibility(String username, int categoryid) {
		this.username = username;
		this.categoryid = categoryid;

	}
}
