package cn.et.servlet.lesson03;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class HttpSessionDataChange implements HttpSessionAttributeListener {
	/**
	 * session.setAttribute("sex","boy");
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		String key=se.getName();
		String value=se.getValue().toString();
		System.out.println(key+"="+value);
	}
	/**
	 * session.removeAttribute("sex","boy");
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}
	/**
	 * 比如session中 存在 sex=boy
	 * session..setAttribute("sex","girl");
	 */
	public void attributeReplaced(HttpSessionBindingEvent se) {
		String key=se.getName();
		String value=se.getValue().toString();//获取旧值
		String val=se.getSession().getAttribute(key).toString();//获取新值
		System.out.println("replace=>"+key+"="+value+"="+val);
	}

}
