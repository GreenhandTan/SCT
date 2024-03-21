package it.hacker.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//过滤器
@WebFilter(urlPatterns= {"/*"},initParams= {
		@WebInitParam(name="exclude",value="/login.jsp,/login,/SCT/,/NoPrivilige.jsp,.css,.png,.jpg,.js")
})
public class PermissionFitler implements Filter{
	
	public static String excludeString;

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		Object user = httpServletRequest.getSession().getAttribute("user");
		String uri = httpServletRequest.getRequestURI();
		if(isExist(uri) || uri.equals(httpServletRequest.getContextPath()+"/")) {
			chain.doFilter(httpServletRequest, httpServletResponse);
		}else {
			if(user != null) {
				chain.doFilter(httpServletRequest, httpServletResponse);
			}else {
				httpServletResponse.sendRedirect("NoPrivilige.jsp");
			}
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		excludeString = filterConfig.getInitParameter("exclude");
	}
	
	public static boolean isExist(String uri) {
		//最后url的结尾与exclude匹配
		String[] arr = excludeString.split(",");
		for(String string:arr) {
			if(uri.endsWith(string)) {
				return true;
			}
		}
		return false;
	}

}
