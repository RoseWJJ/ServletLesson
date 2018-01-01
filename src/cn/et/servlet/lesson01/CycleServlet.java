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
 * java������������� 
 *    ʵ���� ��new��
 *    jvm��Զ��� ���г�ʼ�� <init> ���ù�����
 *    gc ���� finalize �Զ����������  ��ɺ�  
 *    ���ٶ���
 *  
 * servlet��������   
 *     1��tomcat�����Զ� ʵ���� servletʵ��(��Զֻ��һ��ʵ�� ��ʵ��  Ĭ���ڵ�һ�η���ʱ ʵ����   ������load-on-startup��=0�����κ�ֵ ��ʾ ��������ʱʵ����  ���� ��һ������ʱ)
 *       CycleServlet c=new CycleServlet();
 *     2��tomcat�����Զ�����init�������м򵥳�ʼ��
 *       c.init();
 *     3�� service����  (�������ڷ�ʽ  ��  service����  ������������� �������� doGet��doPost)
 *        ���Ƿ���ķ���  ÿ����һ������  �ͻ����һ��
 *       
 *     4��destroy����  ���������ر� ���� ��Ŀ�����¼���ʱ  �Զ� ����servlet  
 *       
 *      
 *       
 * @author Administrator
 *
 */
public class CycleServlet extends HttpServlet {

	public CycleServlet() {
		System.out.println("��ʼ����");   
	}
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		System.out.println("������");
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
	 * һ�����ڽ���һЩ��ʼ������  ��Զֻ�ᱻһ��
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
