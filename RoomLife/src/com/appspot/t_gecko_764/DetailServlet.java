package com.appspot.t_gecko_764;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String path = req.getRequestURL().toString();
		resp.getWriter().println(path);
		String[] splitPath = path.split("/");
		int length = splitPath.length;
		resp.getWriter().println(splitPath[length-2]);
		resp.getWriter().println(splitPath[length-1]);
		
//		Bill bill = (Bill) req.getAttribute("Bill");
//		System.out.println(bill.getName());
	}
}
