package br.uff.controller.internal;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uff.dao.AdministradorDao;
import br.uff.dao.AdministradorHibernateDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Administrador;

/**
 * Servlet implementation class AdministradorInternal
 */
@WebServlet(name = "administradorCrud", urlPatterns = "/internal/administrador/*")
public class AdministradorInternalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private AdministradorDao administradorDao;
	
    public AdministradorInternalServlet() {
        super();
        this.administradorDao = new AdministradorHibernateDao(SessionFactoryHelper.getSessionFactory());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if( action != null && action.equals("delete")) {
			int adminId = Integer.parseInt(request.getParameter("id"));
			administradorDao.delete(adminId);
		}
		
		
		List<Administrador> administradorList = new ArrayList<Administrador>();
		try {
			administradorList = administradorDao.getAll();
		}catch(Exception E) {
			Logger.getGlobal().log(Level.SEVERE, "Couldnt get adm list!");
		}
		request.setAttribute("administradorList",administradorList);
		request.getRequestDispatcher("administrador.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Logger.getGlobal().log(Level.INFO,"\n==================================================================================\n\t"+action+"\n==================================================================================\n");
		if(action.compareTo("update") == 0) {
			int adminId = Integer.parseInt(request.getParameter("id"));
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			doUpdate(adminId,login,senha);
		}else if(action.compareTo("create") == 0) {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			doCreate(login,senha);
		}
				
//		Administrador adm = administradorDao.getById(adminId);
		doGet(request, response);
	}

	private void doCreate(String login, String senha) {
		Administrador adm = new Administrador();
		adm.setLogin(login);
		if(senha != null && senha != "")
			adm.setSenha(senha);
		administradorDao.save(adm);
	}

	private void doUpdate(int id,String login, String senha) {
		Administrador adm = administradorDao.getById(id);
		adm.setLogin(login);
		adm.setSenha(senha);
		administradorDao.save(adm);
	}
	
}
