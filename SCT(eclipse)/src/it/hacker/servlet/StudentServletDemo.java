/*package it.hacker.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.hacker.dao.StudentDao;
import it.hacker.entity.Student;
import it.hacker.utils.MD5;
@WebServlet("/studentDemo")
public class StudentServletDemo extends HttpServlet{
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		int pageSize = 15;
		//当pageNo参数为空的时候，默认pageNo为1；
		if(pageNo == 0 || pageNo == null) {
			pageNo = 1;
		}
		//总记录条数
		Long  totalCount = 0L;
		try {
			totalCount = new StudentDao().count(null);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//共几页
		Long totalPage = totalCount/pageSize;
		if(totalPage == 0 | totalCount%pageSize != 0) {
			totalPage++;
		}
		//下一页
		Integer nextPage = 0;
		if(pageNo<totalPage) {
			nextPage = pageNo+1;
		}else {
			nextPage = pageNo;
		}
		//上一页
		Integer prePage = 0;
		if(pageNo>1) {
			prePage = pageNo-1;
		}else {
			prePage = pageNo;
		}
			try {
				List<Student> list = new StudentDao().list(null,pageNo,pageSize);
				request.setAttribute("list", list);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("totalCount", totalCount);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("nextPage", nextPage);
				request.setAttribute("prePage", prePage);
				request.setAttribute("pageNo", pageNo);
				try {
					request.getRequestDispatcher("/page/student/list.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	public Integer getIntParamter(HttpServletRequest request,String name) {
		if(request.getParameter(name) != null) {
			Integer IntParamter = Integer.parseInt(request.getParameter(name));
			return IntParamter;
		}else {
			return null;
		}
	}
}*/
