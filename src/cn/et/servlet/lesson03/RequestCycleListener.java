package cn.et.servlet.lesson03;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
/**
 * 监听 request对象的产生和销毁
 * request的生命周期  浏览器发起请求  产生request对象  响应后  request被销毁
 * @author Administrator
 *
 */
public class RequestCycleListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent sre) {
	   System.out.println("请求被销毁");
	}

	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request=(HttpServletRequest)sre.getServletRequest();
		System.out.println("请求产生了"+request.getRequestURL());
	}

}
