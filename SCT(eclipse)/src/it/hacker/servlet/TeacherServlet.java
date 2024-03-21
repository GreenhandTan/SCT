package it.hacker.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import it.hacker.dao.TeacherDao;
import it.hacker.entity.Teacher;
import it.hacker.utils.MD5;
import it.hacker.utils.PageInfo;
@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		doPost(req,resp);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if("list".equals(method)) {
			this.list(request, response);
		}else if("add".equals(method)) {
			this.add(request, response);
		}else if("edit".equals(method)) {
			this.findById(request, response);
		}else if("editsubmit".equals(method)) {
			this.editsubmit(request, response);
		}else if("delete".equals(method)) {
			this.delete(request, response);
		}
	}
	private void delete(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("userName");
			try {
				new TeacherDao().delete(userName);
				response.sendRedirect("teacher?method=list&pageNo=1");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	//修改老师信息
	private void editsubmit(HttpServletRequest request,HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String tName = request.getParameter("tName");
		String pwd = request.getParameter("pwd");
		Teacher teacher = new Teacher();
		teacher.setUserName(userName);
		teacher.settName(tName);
		teacher.setPwd(MD5.md5(pwd));
		try {
			new TeacherDao().update(teacher);
			response.sendRedirect("teacher?method=list&pageNo=1");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//用tId查询老师
	private void findById(HttpServletRequest request,HttpServletResponse response) {
		String userName = request.getParameter("userName");
			Teacher teacher;
			try {
				teacher = new TeacherDao().findById(userName);
				request.setAttribute("teacher",teacher);
				request.getRequestDispatcher("page/teacher/update.jsp").forward(request, response);
			} catch (NumberFormatException | SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
	}
	//增加老师
	private void add(HttpServletRequest request,HttpServletResponse response) {
		String tName = request.getParameter("tName");
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		Teacher teacher = new Teacher();
		teacher.settName(tName);
		teacher.setUserName(userName);
		teacher.setPwd(MD5.md5(pwd));
		try {
			new TeacherDao().add(teacher);
			response.sendRedirect("teacher?method=list&pageNo=1");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//查询当前库里所有学生
	private void list(HttpServletRequest request,HttpServletResponse response){
		//当前页码
		Integer pageNo = getIntParamter(request,"pageNo");
		//组合查询老师参数
		String tName = request.getParameter("tName");
		String userName = request.getParameter("userName");
		
		Teacher teacher = new Teacher();
		if(StringUtils.isBlank(tName) && StringUtils.isBlank(userName)) {
			teacher = null;
		}else {
			teacher.settName(tName);
			teacher.setUserName(userName);
		}
		//总记录条数
		PageInfo<Teacher> pageInfo = new PageInfo<>(pageNo);
		try {
			pageInfo = new TeacherDao().list(teacher,pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageNo", pageNo);
			request.getRequestDispatcher("/page/teacher/list.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e1) {
			e1.printStackTrace();
		}
	}
	public Integer getIntParamter(HttpServletRequest request,String name) {
		if(StringUtils.isNoneBlank(request.getParameter(name))) {
			Integer IntParamter = Integer.parseInt(request.getParameter(name));
			return IntParamter;
		}else {
			return null;
		}
	}
}
