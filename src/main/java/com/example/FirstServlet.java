package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Parent;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(FirstServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirstServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("boom2") != null) {
			throw new RuntimeException("boom2 - up to tomcat");
		} 
		try
		{
			logger.debug("welcome " + request);
			if (request.getParameter("err") != null) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} else if (request.getParameter("boom") != null) {
				throw new RuntimeException("boom");
			} else if (request.getParameter("log") != null) {
				logger.error("log error");
			} else if (request.getParameter("loge") != null) {
				try
				{
					logger.info("about to loge");
					
					throw new Exception("loge");
				}
				catch (Exception e11)
				{
					logger.error("loge error", e11);
				}
			} else if (request.getParameter("remote") != null) {
				// I'm the one you should use
				URL url = new URL(request.getParameter("remote"));
				
				if (url.getProtocol().equals("http"))
				{
					System.out.println("just a stub to make sure oracle is found");
				}
				
		        URLConnection yc = url.openConnection();
		        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		        String inputLine;
		        while ((inputLine = in.readLine()) != null) 
		            System.out.println(inputLine);
		        in.close();
			} else if (request.getParameter("inner") != null) {
				new InnerClasses().do1();
			}
		}
		catch (Throwable e) 
		{
			e.printStackTrace();
		}

		response.getWriter().println("ok");
	}

//	protected void hibernate() {
//		SessionFactory sessionFactory = new Configuration()
//		  .addAnnotatedClass(Entity.class)
//		  .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
//		  .setProperty("hibernate.connection.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
//		  .setProperty("hibernate.current_session_context_class", "thread")
//		  .setProperty("hibernate.show_sql", "true")
//		  .setProperty("hibernate.hbm2ddl.auto", "create")
//		  .buildSessionFactory();
//		  
//		  Session session = sessionFactory.getCurrentSession();
//		  session.beginTransaction();
//		  
//		  List<?> results = session.createCriteria(Parent.class)
//				     .add( Example.create(parent).ignoreCase() )
//				     .createCriteria("child")
//				         .add( Example.create( parent.getChild() ) )
//				     .list();
//		  
//		  session.getTransaction().commit();
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
