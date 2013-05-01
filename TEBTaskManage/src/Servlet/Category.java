package Servlet;

import java.util.*;
import com.google.appengine.api.datastore.*;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Category {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	public int categoryid;	
	
	@Persistent
	public String categoryname;
	
	@Persistent
	public String username;
	
	@Persistent
	public Date createdate;
	
	public Category(int categoryid, String categoryname, String username, Date createdate) {
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.username = username;
		this.createdate = createdate;
	}
}
