package br.uff.controller;

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

import br.uff.dao.CategoriaHibernateDao;
import br.uff.dao.IGenericDao;
import br.uff.dao.ProdutoHibernateDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Categoria;
import br.uff.model.Produto;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(name = "IndexServlet" , urlPatterns = {""})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private ProdutoHibernateDao produtoDao;
	private IGenericDao<Categoria> categoriaDao;
	
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.produtoDao = new ProdutoHibernateDao(SessionFactoryHelper.getSessionFactory());
        this.categoriaDao = new CategoriaHibernateDao(SessionFactoryHelper.getSessionFactory());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger.getGlobal().log(Level.INFO, "GET: Index");
		List<Produto> produtoList = new ArrayList<Produto>();
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		Logger.getGlobal().log(Level.INFO, "GET: Index " + id + action);
		if(action != null && action.compareToIgnoreCase("loadProduct") == 0) {
			try {
				int categoriaId = Integer.parseInt(request.getParameter("id"));
				produtoList = produtoDao.getByCategoryId(categoriaId);
			}catch(Exception E) {
				Logger.getGlobal().log(Level.SEVERE, "Couldnt get prod list!");
			}
		}
		List<Categoria> categoriaList = new ArrayList<Categoria>();
		try {
			categoriaList = categoriaDao.getAll();
		}catch(Exception E) {
			Logger.getGlobal().log(Level.SEVERE, "Couldnt get cat list!");
		}
		request.setAttribute("categoriaList",categoriaList);
		request.setAttribute("produtoList",produtoList);
		request.getRequestDispatcher("index.jsp").forward(request, response);	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
