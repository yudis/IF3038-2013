package Servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.apache.jasper.tagplugins.jstl.core.Out;

import java.io.IOException;
import java.io.PrintWriter;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

@SuppressWarnings("serial")
public class AmbilIsiDatabase extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		
		DatastoreService iseng = DatastoreServiceFactory.getDatastoreService();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery("select name from "+User.class.getName());
		
		List<String> result = (List<String>)q.execute();
		
		for (String s : result) {
			out.println(s);
		}
		q.closeAll();
		pm.close();
	}
}
