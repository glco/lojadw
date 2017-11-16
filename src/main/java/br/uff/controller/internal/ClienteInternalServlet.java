package br.uff.controller.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uff.dao.ClienteHibernateDao;
import br.uff.dao.IGenericDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Cliente;

/**
 * Servlet implementation class ClienteInternalServlet
 */
@WebServlet("/internal/cliente/*")
public class ClienteInternalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
private IGenericDao<Cliente> clienteDao;
	
    public ClienteInternalServlet() {
        super();
        this.clienteDao = new ClienteHibernateDao(SessionFactoryHelper.getSessionFactory());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if( action != null && action.equals("delete")) {
			int clienteinId = Integer.parseInt(request.getParameter("id"));
			clienteDao.delete(clienteinId);
		}
		
		
		List<Cliente> clienteList = new ArrayList<Cliente>();
		try {
			clienteList = clienteDao.getAll();
		}catch(Exception E) {
			Logger.getGlobal().log(Level.SEVERE, "Couldnt get cliente list!");
		}
		request.setAttribute("clienteList",clienteList);
		request.getRequestDispatcher("cliente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Logger.getGlobal().log(Level.INFO,"\n==================================================================================\n\t"+action+"\n==================================================================================\n");
		if(action.compareTo("update") == 0) {
			int clienteinId = Integer.parseInt(request.getParameter("id"));
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			doUpdate(clienteinId,login,senha);
		}else if(action.compareTo("create") == 0) {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			doCreate(login,senha);
		}
		doGet(request, response);
	}

	private void doCreate(String login, String senha) {
		Cliente cliente = new Cliente();
		
		clienteDao.save(cliente);
	}

	private void doUpdate(int id,String login, String senha) {
		Cliente cliente = clienteDao.getById(id);

	}
	
}
