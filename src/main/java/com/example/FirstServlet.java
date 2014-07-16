package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
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
		try
		{
			if (request.getParameter("err") != null) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} else if (request.getParameter("boom") != null) {
				throw new RuntimeException("boom");
			} else if (request.getParameter("log") != null) {
				Logger logger = Logger.getLogger("FirstServlet");
				logger.severe("log error");
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
			}
		}
		catch (Throwable e) 
		{
			e.printStackTrace();
		}

		response.getWriter().println("ok");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
