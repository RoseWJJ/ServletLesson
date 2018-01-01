package cn.et.servlet.lesson03;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
/**
 * ServletContext生命周期
 *   伴随应用的发布（产生）和关闭  （销毁）
 *   
 * 数据存储逻辑(setAttribute 和 getAttribute)
 *   所有的servlet都需要共享  数据需要保留时间很长  写入  ServletContext
 *   数据使有状态的 不同的用户拥有不同的数据   写入 HttpSession
 *   传递的参数  或者 请求转发跳转数据传递   写入HttpServletRequest 应用最多
 * @author Administrator
 *
 */
public class MyServletContext implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("上下文对象被销毁");
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("上下文对象 产生");
	}

	
}
