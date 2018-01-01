package cn.et.servlet.lesson02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CkServlet extends HttpServlet {

	int i=0;
	
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

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//无状态性
		//out.println("访问的次数是:"+(++i));
		/**
		//有状态 将变量写入到浏览器 互相不影响
		Cookie[] ckks=request.getCookies();
		int i=1;
		//如果某个浏览器是第一次访问 没有cookie 第一次访问需要将cookie写入初始值1
		if(ckks==null || ckks.length==0){
			Cookie c=new Cookie("i", i+"");
			response.addCookie(c);
		}else{
			//判断cookie中是否存在 i这个cookie 如果存在 +1 后写会
			for(Cookie cie:ckks){
				if(cie.getName().equals("i")){
				  i=Integer.parseInt(cie.getValue())+1;	
				}
			}
			Cookie c=new Cookie("i", i+"");
			response.addCookie(c);
		}
		**/
		//有状态 通过session来实现   任何系统中  用户权限  将用户信息存储session中
		HttpSession session=request.getSession();
		System.out.println(session.getId());
		Object obj=session.getAttribute("i");
		int i=1;
		if(obj==null){
			session.setAttribute("i", i);
		}else{
			String flag=request.getParameter("flag");
			if(flag==null)
				i=(Integer)obj+1;
			else if("minus".equals(flag))
				i=(Integer)obj-1;
			session.setAttribute("i", i);
		}
		
		out.print("您是第"+i+"次访问该系统<a href='"+request.getContextPath()+"/ck?flag=minus'>退出</a>");
		
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
