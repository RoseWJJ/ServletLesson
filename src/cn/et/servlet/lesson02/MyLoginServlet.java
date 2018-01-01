package cn.et.servlet.lesson02;

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
/**
 * 请求转发和重定向区别
 *   1.调用方式不同 转发（request.getRequestDispatcher("/lesson02/detail.html").forward(request, response);）
 *               重定向（response.sendRedirect(contextPath+"/lesson02/detail.html");）
 *   2.请求次数不同  转发 1次    重定向2次 
 *   3.跳转方式不同 转发：服务器内部跳转 浏览不知     重定向 告知浏览器 302 设置location 发生的二次跳转
 *   4.地址不同  转发 地址不变    重定向 变化为第二次地址
 *   5.参数不同 转发 参数保留  重定向  参数丢失 
 *   6.跨域访问 转发 只能在同一个项目中的url跳转  重定向  可以跨域
 * @author Administrator
 *
 */
public class MyLoginServlet extends HttpServlet {

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
				//跳转到detail.html
				//请求转发
				//request.getRequestDispatcher("/lesson02/detail.html").forward(request, response);
				//重定向 
				response.sendRedirect(contextPath+"/lesson02/detail.html");
				rs.close();
				pst.close();
				conn.close();
				return;
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
