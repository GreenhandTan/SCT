package it.hacker.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import it.hacker.dao.AdminDao;
import it.hacker.dao.StudentDao;
import it.hacker.dao.TeacherDao;
import it.hacker.entity.Admin;
import it.hacker.entity.Student;
import it.hacker.entity.Teacher;
import it.hacker.utils.MD5;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String type = req.getParameter("type");
		/*System.out.println(userName);
		System.out.println(password);
		System.out.println(type);*/
		HttpSession session = req.getSession();
		if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)||StringUtils.isBlank(type)) {
			req.setAttribute("error", "用户名或密码为空！");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		if(StringUtils.isNotBlank(type)) {
			if("0".equals(type)) {
				//学生
				try {
					Student student = new StudentDao().login(userName,MD5.md5(password));
					if(student!=null) {
						//执行跳转
						session.setAttribute("user", student);
						session.setAttribute("type", type);
						resp.sendRedirect("index.jsp");
					}else {
						//用户名或密码错误
						req.setAttribute("error", "用户名或密码错误！");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if("1".equals(type)) {
				//老师
				try {
					Teacher teacher = new TeacherDao().login(userName,MD5.md5(password));
					if(teacher!=null) {
						//执行跳转
						session.setAttribute("user", teacher);
						session.setAttribute("type", type);
						resp.sendRedirect("index.jsp");
					}else {
						//用户名或密码错误
						req.setAttribute("error", "用户名或密码错误！");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				//管理员
				try {
					Admin entity = new AdminDao().login(userName,MD5.md5(password));//MD5加密一次
					if(entity!=null) {
						//执行跳转
						session.setAttribute("user", entity);
						session.setAttribute("type", type);
						resp.sendRedirect("index.jsp");
					}else {
						//用户名或密码错误
						req.setAttribute("error", "用户名或密码错误！");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
