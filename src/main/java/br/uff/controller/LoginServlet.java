package br.uff.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.uff.dao.AdministradorDao;
import br.uff.dao.AdministradorHibernateDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Administrador;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "/LoginServlet",urlPatterns = "/doLogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private AdministradorDao administradorDao;
    public LoginServlet() {
        super();
        this.administradorDao = new AdministradorHibernateDao(SessionFactoryHelper.getSessionFactory());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userLogin = request.getParameter("login");
		String userPassword = request.getParameter("senha");
		Administrador admin = administradorDao.getByLogin(userLogin,userPassword);
		if(admin == null)
			response.sendRedirect("Index.jsp");
		else{
			HttpSession session = request.getSession();
			session.setAttribute("UserLoggedIn", true);
			session.setAttribute("UserName", userLogin);
			
//			RequestDispatcher rd = request.getRequestDispatcher("internal/index.jsp");
//			rd.forward(request, response);
			
			response.sendRedirect("internal/index.jsp");
		}
		
	}

}
