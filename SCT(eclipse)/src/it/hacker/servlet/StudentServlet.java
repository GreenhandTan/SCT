package it.hacker.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.hacker.dao.StudentDao;
import it.hacker.entity.Student;
import it.hacker.utils.MD5;
import it.hacker.utils.PageInfo;
@WebServlet("/student")
public class StudentServlet extends HttpServlet{
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
		String stuNo = request.getParameter("stuNo");
			try {
				new StudentDao().delete(Integer.parseInt(stuNo));
				response.sendRedirect("student?method=list&pageNo=1");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	//修改学生信息
	private void editsubmit(HttpServletRequest request,HttpServletResponse response) {
		String stuNo = request.getParameter("stuNo");
		String stuName = request.getParameter("stuName");
		String stuPwd = request.getParameter("stuPwd");
		Student student = new Student();
		student.setStuNo(stuNo);
		student.setStuName(stuName);
		student.setStuPwd(MD5.md5(stuPwd));
		try {
			//String pageNo = request.getParameter("pageNo");//11111111
			new StudentDao().update(student);
			response.sendRedirect("student?method=list&pageNo=1");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//用学号查询学生
	private void findById(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			Student student = new StudentDao().findById(Integer.parseInt(id));
			request.setAttribute("student",student);
			request.getRequestDispatcher("page/student/update.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//增加学生
	private void add(HttpServletRequest request,HttpServletResponse response) {
		String stuNo = request.getParameter("stuNo");
		String stuName = request.getParameter("stuName");
		String stuPwd = request.getParameter("stuPwd");
		Student student = new Student();
		student.setStuNo(stuNo);
		student.setStuName(stuName);
		student.setStuPwd(MD5.md5(stuPwd));
		try {
			new StudentDao().add(student);
			response.sendRedirect("student?method=list&pageNo=1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//查询当前库里所有学生
	private void list(HttpServletRequest request,HttpServletResponse response){
		//当前页码
		Integer pageNo = getIntParamter(request,"pageNo");
		//组合查询学生参数
		String stuName = request.getParameter("stuName");
		String stuNo = request.getParameter("stuNo");
		
		Student student = new Student();
		if(StringUtils.isBlank(stuName) && StringUtils.isBlank(stuNo)) {
			student = null;
		}else {
			student.setStuName(stuName);
			student.setStuNo(stuNo);
		}
		//总记录条数
		PageInfo<Student> pageInfo = new PageInfo<>(pageNo);
		try {
			pageInfo = new StudentDao().list(student,pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageNo", pageNo);
			request.getRequestDispatcher("/page/student/list.jsp").forward(request, response);
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
