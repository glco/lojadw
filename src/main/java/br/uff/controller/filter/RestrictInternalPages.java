package br.uff.controller.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class RestrictInternalPages
 */
@WebFilter(urlPatterns = "/internal/*")
public class RestrictInternalPages implements Filter {

    /**
     * Default constructor. 
     */
    public RestrictInternalPages() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession httpSession = ((HttpServletRequest) request).getSession(false);
		try {
			boolean isUserLoggedIn = (boolean)httpSession.getAttribute("UserLoggedIn");
			if(isUserLoggedIn == false)
				redirectToLogin((HttpServletResponse)response);
			String userName = (String)httpSession.getAttribute("UserName");
			Logger.getGlobal().log(Level.INFO,"UserLoggedIn:"+isUserLoggedIn+" "+userName);
		}catch(NullPointerException  | IOException e) {
			redirectToLogin((HttpServletResponse)response);
		}
		
		chain.doFilter(request, response);
	}

	public void redirectToLogin(HttpServletResponse servletResponse) throws IOException {
		Logger.getGlobal().log(Level.WARNING,"User is not logged in, redirecting to Home");
		servletResponse.sendRedirect("../index.jsp");
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
