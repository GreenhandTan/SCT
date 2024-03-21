package it.hacker.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import it.hacker.dao.CourseDao;
import it.hacker.entity.Course;
import it.hacker.utils.PageInfo;
@WebServlet("/course")
public class CourseServlet extends HttpServlet{
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
	//删除课程
	private void delete(HttpServletRequest request,HttpServletResponse response){
		Integer cId = getIntParamter(request,"cId");
			try {
				new CourseDao().delete(cId);
				response.sendRedirect("course?method=list&pageNo=1");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	//修改课程信息
	private void editsubmit(HttpServletRequest request,HttpServletResponse response) {
		Integer cId = getIntParamter(request,"cId");
		String tName = request.getParameter("tName");
		String cName = request.getParameter("cName");
		Course course = new Course();
		course.setcId(cId);
		course.settName(tName);
		course.setcName(cName);
		try {
			new CourseDao().update(course);
			response.sendRedirect("course?method=list&pageNo=1");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//用cId查询课程
	private void findById(HttpServletRequest request,HttpServletResponse response) {
		Integer cId = getIntParamter(request,"cId");
			Course course;
			try {
				course = new CourseDao().findById(cId);
				request.setAttribute("course",course);
				request.getRequestDispatcher("page/course/update.jsp").forward(request, response);
			} catch (NumberFormatException | SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
	}
	//增加课程
	private void add(HttpServletRequest request,HttpServletResponse response) {
		String tName = request.getParameter("tName");
		String cName = request.getParameter("cName");
		Course course = new Course();
		course.settName(tName);
		course.setcName(cName);
		try {
			new CourseDao().add(course);
			response.sendRedirect("course?method=list&pageNo=1");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//查询当前库里所有课程
	private void list(HttpServletRequest request,HttpServletResponse response){
		//当前页码
		Integer pageNo = getIntParamter(request,"pageNo");
		//组合查询老师参数
		String tName = request.getParameter("tName");
		String cName = request.getParameter("cName");
		
		Course course = new Course();
		if(StringUtils.isBlank(tName) && StringUtils.isBlank(cName)) {
			course = null;
		}else {
			course.settName(tName);
			course.setcName(cName);
		}
		//总记录条数
		PageInfo<Course> pageInfo = new PageInfo<>(pageNo);
		try {
			pageInfo = new CourseDao().list(course,pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageNo", pageNo);
			request.getRequestDispatcher("/page/course/list.jsp").forward(request, response);
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
