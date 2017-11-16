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
import br.uff.dao.CompraHibernateDao;
import br.uff.dao.IGenericDao;
import br.uff.dao.ProdutoHibernateDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Cliente;
import br.uff.model.Compra;
import br.uff.model.Produto;

/**
 * Servlet implementation class CompraInternalServlet
 */
@WebServlet(name="compraCrud" ,urlPatterns="/internal/compra/*")
public class CompraInternalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	IGenericDao<Compra> compraDao;
	IGenericDao<Produto> produtoDao;
	IGenericDao<Cliente> clienteDao;
	
    public CompraInternalServlet() {
        super();
        this.compraDao = new CompraHibernateDao(SessionFactoryHelper.getSessionFactory());
        this.produtoDao = new ProdutoHibernateDao(SessionFactoryHelper.getSessionFactory());
        this.clienteDao = new ClienteHibernateDao(SessionFactoryHelper.getSessionFactory());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if( action != null && action.equals("delete")) {
			int compraId = Integer.parseInt(request.getParameter("id"));
			compraDao.delete(compraId);
		}
		
		List<Produto> produtoList = new ArrayList<Produto>();
		List<Cliente> clienteList = new ArrayList<Cliente>();
		List<Compra> compraList = new ArrayList<Compra>();
		
		try {
			compraList = compraDao.getAll();
			clienteList = clienteDao.getAll();
			produtoList = produtoDao.getAll();
		}catch(Exception E) {
			Logger.getGlobal().log(Level.SEVERE, "Couldnt get cat list!");
		}
		
		request.setAttribute("clienteList",clienteList);
		request.setAttribute("produtoList",produtoList);
		request.setAttribute("compraList",compraList);
		request.getRequestDispatcher("compra.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
