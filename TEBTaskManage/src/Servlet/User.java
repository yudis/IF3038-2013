package Servlet;

import java.util.*;
import com.google.appengine.api.datastore.*;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	public String username;
	
	@Persistent
	public String password;
	
	@Persistent
	public String fullname;
	
	@Persistent
	public String birthday;
	
	@Persistent
	public String email;
	
	@Persistent
	public String join;
	
	@Persistent
	public String aboutme;
	
	@Persistent
	public String avatar;
	
	public User(String username, String password, String fullname, String birthday, String email, String join, String aboutme, String avatar) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.birthday = birthday;
		this.email = email;
		this.join = join;
		this.aboutme = aboutme;
		this.avatar = avatar;
	}
}
