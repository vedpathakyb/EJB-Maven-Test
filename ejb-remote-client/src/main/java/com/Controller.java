package com;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;


/*import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;*/

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Controller() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Properties p = new Properties();
		p.put("remote.connections","node1");
		//p.put("remote.connection.node.port","8080");  // the default remoting port, replace if necessary
		//p.put("remote.connection.node.host","192.168.1.2");  // the host, replace if necessary
		//p.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED","false"); // the server defaults to SSL_ENABLED=false
		//p.put("remote.connection.node.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS","false");
		//p.put("remote.connection.node.username","admin");
		//p.put("remote.connection.node.password","admin@123");
		//EJBClientConfiguration cc=new PropertiesBasedEJBClientConfiguration(p);
		//ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector(cc);
		//EJBClientContext.setSelector(selector);
		Properties contextProperties=new Properties();
		contextProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		try
		{
		Context context = new InitialContext(contextProperties);
		    System.out.println("Got Initial Context:" + context);   
		    IRemote remote=(IRemote)context.lookup("ejb:/EJB-Test-1.0-SNAPSHOT/App!com.IRemote");
		    System.out.println("Got Remote Interface:" +remote);   
		    System.out.println(remote.add(10,10));  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}