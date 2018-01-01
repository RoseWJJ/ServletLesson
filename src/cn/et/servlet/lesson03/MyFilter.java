package cn.et.servlet.lesson03;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 拦截器的实现类  实现Filter  拦截器 只能在容器启动时创建实例
 * @author Administrator
 *
 */
public class MyFilter implements Filter {

	/**
	 * 生命周期的销毁方法
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}
	/**
	 * 配置了拦截某些资源  这些资源都被进入doFilter
	 * 方法决定了资源是否能够通过该过滤器
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//调用了该方法 过滤器通过验证 不调用 打回
		
		
		//如果访问了file 就通过 访问了private 打回
		//chain.doFilter(request, response);
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		
		String url=req.getRequestURL().toString();
		if(!url.contains("private")){
			chain.doFilter(request, response);
		}

	}
	/**
	 * 生命周期的初始化方法
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter init ");
	}

}
