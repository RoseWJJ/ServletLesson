package cn.et.servlet.lesson03;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * session的生命周期 
 *    session原理是通过 容器 在调用getsession方法时 自动通过cookie写入sessionid的方式来控制
 *    cookie默认的有效期是 浏览器被关闭之前  当前session的有效期是当前这一次浏览器访问
 *    session数据存放在 容器中 容器无法监听 浏览器的关闭  会通过web.xml
 *        <session-config>
	      <session-timeout>1</session-timeout>
	  </session-config> 来决定定时清除  长期未访问的session数据
 * @author Administrator
 *
 */
public class MySessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session=se.getSession();
		System.out.println("session产生"+session.getId());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session=se.getSession();
		System.out.println("session被销毁"+session.getId());
	}

}
