package cn.et.servlet.lesson01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter ps=resp.getWriter();
		ps.println("ª∂”≠∑√Œ ");
		
		
		//resp.setStatus(301);
		//resp.setHeader("Location","http://www.csdn.net");
		//resp.setIntHeader("Refresh",10);
		resp.setDateHeader("expries", -1);  
		resp.setHeader("Cache-Control", "no-cache");  
		resp.setHeader("Pragma", "no-cache");  
		resp.setHeader("Refresh","10;url=http://www.csdn.net");
		
		//resp.setHeader("refresh", "3;url='http://www.csdn.net'");  

	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
