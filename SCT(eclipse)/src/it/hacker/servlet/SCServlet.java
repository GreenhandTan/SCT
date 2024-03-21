package it.hacker.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.hacker.dao.CourseDao;
import it.hacker.dao.ScDao;
import it.hacker.entity.Course;
import it.hacker.entity.SC;
import it.hacker.entity.Student;
import it.hacker.entity.Teacher;
import it.hacker.utils.PageInfo;
@WebServlet("/sc")
public class SCServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		doPost(req,resp);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if("select".equals(method)) {
			this.select(request, response);
		}else if("submit".equals(method)) {
			this.submit(request, response);
		}else if("tc".equals(method)) {
			this.teacher_course(request, response);
		}else if("cs".equals(method)) {
			this.course_student(request, response);
		}else if("score_submit".equals(method)) {
			this.score_submit(request, response);
		}
	}
	//选课跳转
	private void select(HttpServletRequest request,HttpServletResponse response) {
		PageInfo<Course> info = new PageInfo<>(1);
		try {
			info = new CourseDao().list(new Course(), info);
			//获取登录学生信息
			Student student = (Student)request.getSession().getAttribute("user");
			//获取已选课ID
			List<SC> list = new ScDao().listByStuNo(student.getStuNo());
			request.setAttribute("courses", info.getList());
			request.setAttribute("scs", list);
			request.getRequestDispatcher("page/sc/select.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	//选课保存提交操作
	private void submit(HttpServletRequest request,HttpServletResponse response) {
		String[] cIds = request.getParameterValues("cId");
		ArrayList<Integer> cIdArray = new ArrayList<Integer>();
		for(String cid:cIds) {
			cIdArray.add(Integer.parseInt(cid));
		}
		Student student = (Student)request.getSession().getAttribute("user");
		String stuId = student.getStuNo();//取学号
		try {
			int[] arr = new ScDao().add(cIdArray,Integer.parseInt(stuId));
			if(arr.length == 0) {
				response.sendRedirect("sc?method=select&msg=Error");
			}else {
				response.sendRedirect("sc?method=select&msg=Success");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	//老师评分的学生查询操作
	private void course_student(HttpServletRequest request,HttpServletResponse response) {
		//获取cId
		Integer cId = getIntParamter(request,"cId");
		//获取学生
		try {
			List<Student> list = new ScDao().listStudentByStuNo(cId);
			request.setAttribute("list", list);
			request.setAttribute("cId", cId);
			request.getRequestDispatcher("page/sc/course_student.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//老师评分操作
		private void teacher_course(HttpServletRequest request,HttpServletResponse response) {
			//获取老师Session中的对象
			Teacher teacher = (Teacher)request.getSession().getAttribute("user");
			Integer pageNo = getIntParamter(request,"pageNo");
			Course course = new Course();
			course.settName(teacher.gettName());
			PageInfo<Course> pageInfo = new PageInfo<>(pageNo);
			//查询所教课程列表
			try {
				pageInfo = new CourseDao().list(course, pageInfo);
				request.setAttribute("pageInfo", pageInfo);
				request.getRequestDispatcher("page/sc/teacher_course.jsp").forward(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		//评分提交
		private void score_submit(HttpServletRequest request,HttpServletResponse response) {
			//获取cId
			Integer cId = getIntParamter(request,"cId");
			String[] stuNoArr = request.getParameterValues("stuNo");
			String[] scoreArr = request.getParameterValues("score");
			try {
				new ScDao().update(stuNoArr, scoreArr, cId);
				response.sendRedirect("sc?method=cs&cId="+cId+"&msg=Success");
			} catch (SQLException | IOException e) {
				e.printStackTrace();
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
