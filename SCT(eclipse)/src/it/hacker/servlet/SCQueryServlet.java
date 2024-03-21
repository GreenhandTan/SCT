package it.hacker.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.hacker.dao.ScDao;
import it.hacker.entity.Student;
import it.hacker.entity.Teacher;
@WebServlet("/scquery")
public class SCQueryServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		doPost(req,resp);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if("query_range".equals(method)) {
			this.query_range(request, response);
		}else if("query_jgl".equals(method)){
			this.query_jgl(request, response);
		}else if("query_teacher".equals(method)){
			this.query_teacher(request, response);
		}else if("query_student".equals(method)){
			this.query_student(request, response);
		}
	}
	//区间查询
	public void query_range(HttpServletRequest request,HttpServletResponse response) {
		try {
			List<Map<String, Object>> list = new ScDao().query_range();
			request.setAttribute("list", list);
			request.getRequestDispatcher("page/sc/query_range.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	//及格率查询
	public void query_jgl(HttpServletRequest request,HttpServletResponse response) {
		try {
			List<Map<String, Object>> list = new ScDao().query_jgl();
			request.setAttribute("list", list);
			request.getRequestDispatcher("page/sc/query_jgl.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	//老师查询
	public void query_teacher(HttpServletRequest request,HttpServletResponse response) {
		try {
			Teacher teacher = (Teacher)request.getSession().getAttribute("user");
			List<Map<String, Object>> list1 = new ScDao().query_rangeBytName(teacher.gettName());
			request.setAttribute("list1", list1);
			List<Map<String, Object>> list2 = new ScDao().query_jglBytName(teacher.gettName());
			request.setAttribute("list2", list2);
			request.getRequestDispatcher("page/sc/query_teacher.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	//学生查询
	public void query_student(HttpServletRequest request,HttpServletResponse response) {
		try {
			Student student = (Student)request.getSession().getAttribute("user");
			List<Map<String, Object>> list = new ScDao().query_jglBystuNo(student.getStuNo());
			request.setAttribute("list", list);
			request.getRequestDispatcher("page/sc/query_student.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
