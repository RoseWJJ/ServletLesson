package cn.et.servlet.lesson01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.servlet.lesson01.utils.DBUtils;
/**
 * java对象的生命周期 
 *    实例化 （new）
 *    jvm会对对象 进行初始化 <init> 调用构造器
 *    gc 调用 finalize 对对象进行清理  完成后  
 *    销毁对象
 *  
 * servlet生命周期   
 *     1》tomcat容器自动 实例化 servlet实例(永远只有一个实例 单实例  默认在第一次访问时 实例化   配置了load-on-startup》=0以上任何值 表示 启动服务时实例化  否则 第一次请求时)
 *       CycleServlet c=new CycleServlet();
 *     2》tomcat容器自动调用init方法进行简单初始化
 *       c.init();
 *     3》 service方法  (请求的入口方式  是  service方法  根据请求的类型 决定调用 doGet和doPost)
 *        就是服务的方法  每发起一次请求  就会调用一次
 *       
 *     4》destroy方法  当容器被关闭 或者 项目被重新加载时  自动 销毁servlet  
 *       
 *      
 *       
 * @author Administrator
 *
 */
public class CycleServlet extends HttpServlet {

	public CycleServlet() {
		System.out.println("开始构造");   
	}
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		System.out.println("被销毁");
	}
    
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("service");
		super.service(req, resp);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("get or post");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
	Connection conn=null;
	/**
	 * 一般用于进行一些初始化工作  永远只会被一次
	 */
	public void init() throws ServletException {
		System.out.println("init");
		try {
			conn=DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void init(ServletConfig config){
		System.out.println("initconfig");
		try {
			super.init(config);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
