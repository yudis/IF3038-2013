package Servlet;

import java.util.*;
import com.google.appengine.api.datastore.*;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Tag {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	public int tagid;
	
	@Persistent
	public String tagname;
	
	public Tag(int tagid, String tagname) {
		this.tagid = tagid;
		this.tagname = tagname;

	}
}
