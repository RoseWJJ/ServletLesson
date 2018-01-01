package cn.et.servlet.lesson01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 全局对象 每个项目都有自己的全局对象
		ServletContext sc=this.getServletContext();
		
		sc.log("开始执行doGet");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		//1 servlet之间共享数据  同一个项目
		sc.setAttribute("sex", "男");
		//2 获取web.xml中配置的参数
		String driverClass=sc.getInitParameter("driverClass");
		out.print("获取参数："+driverClass+"<br/>");
		//3 读取web资源
		InputStream is=sc.getResourceAsStream("/my.txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "GBK"));
		out.print("获取资源内容："+br.readLine()+"<br/>");
		br.close();
		is.close();
		
		//打印日志
		sc.log("执行doGet完毕");
		
		//使用config对象获取 servlet自身配置参数
		String hello=config.getInitParameter("hello");
		out.print("获取自身配置参数："+hello+"<br/>");
		
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
	private ServletConfig config;
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config=config;
		super.init(config);
	}

}
