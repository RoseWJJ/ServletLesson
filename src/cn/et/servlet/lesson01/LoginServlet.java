package cn.et.servlet.lesson01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.servlet.lesson01.utils.DBUtils;

public class LoginServlet extends HttpServlet {

	/**
	 * request表示浏览器的请求（浏览器输入地址回车 ，表单点击提交按钮） 请求获取的是浏览器的一些信息（请求头）
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//获取请求的url  getRequestURL
		String url=request.getRequestURL().toString();
		out.println("您请求的url:"+url+"<br/>");
		//获取参数 getParameter("键")
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		//获取请求的方式
		String method=request.getMethod();
		out.println("您请求的类型:"+method+"<br/>");
		//获取请求头的简直对
		String acceptLa=request.getHeader("Accept-Language");
		out.println("浏览器支持的语言和国家:"+acceptLa+"<br/>");
		String userAgent=request.getHeader("User-Agent");
		out.println("浏览器的类型:"+userAgent+"<br/>");
		
		String contextPath=request.getContextPath();
		out.println("上下文路径："+contextPath);
		//String str=null;
		//str.toString();
		
		
		//调用jdbc 查询数据 
		//登录成功 欢迎XXX  select * from myuser where username=? and password=?
		//登录失败  提示 登录失败 
		String sql="select * from myuser where username=? and password=?";
		try {
			Connection conn=DBUtils.getConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userName);
			pst.setString(2, password);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				out.println("<font color=red>欢迎 "+userName+" 登陆系统</font>");
			}
			else{
				out.println("<font color=red>登陆失败</font>");
				response.setDateHeader("expries", -1);  
				response.setHeader("Cache-Control", "no-cache");  
				response.setHeader("Pragma", "no-cache");  
				response.setHeader("Refresh","3;url="+contextPath+"/index.html");
				
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
