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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uff.dao.CategoriaHibernateDao;
import br.uff.dao.IGenericDao;
import br.uff.dao.ProdutoHibernateDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Categoria;
import br.uff.model.Produto;
import br.uff.model.ShoppingCart;

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
		List<Produto> produtoList = new ArrayList<Produto>();
		String catId = request.getParameter("catId");
		if(catId != null && Integer.parseInt(catId) != 0) {
			try {
				int categoriaId = Integer.parseInt(catId);
				produtoList = produtoDao.getByCategoryId(categoriaId);
			}catch(Exception e) {
				Logger.getGlobal().log(Level.SEVERE, "Couldnt get prod list! \n ",e);
			}
		}else {
			produtoList = produtoDao.getAll();
		}
		List<Categoria> categoriaList = new ArrayList<Categoria>();
		try {
			categoriaList = categoriaDao.getAll();
		}catch(Exception E) {
			Logger.getGlobal().log(Level.SEVERE, "Couldnt get cat list!");
		}
		request.setAttribute("categoriaList",categoriaList);
		request.setAttribute("produtoList",produtoList);
		Logger.getGlobal().log(Level.INFO, "List size: "+produtoList.size());
		request.getRequestDispatcher("index.jsp").forward(request, response);	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger.getGlobal().log(Level.INFO, "POST");
		try {
			int prodId = Integer.parseInt(request.getParameter("produtoId"));
			Logger.getGlobal().log(Level.INFO, "PRD ID:"+prodId);
			Produto p = produtoDao.getById(prodId);
			Logger.getGlobal().log(Level.INFO, "PRD ID:"+p.getDescricao());
			addProductToTheShoppingCart(request.getSession(),p);
		}catch(Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Não foi possivel adicionar o produto ao carrinho. \n"+e.getMessage(),e.getCause());
		}
		doGet(request,response);
	}

	private void addProductToTheShoppingCart(HttpSession session, Produto p) {
		ShoppingCart sc = (ShoppingCart) session.getAttribute("shoppingCart");
		if(sc == null)
			sc = new ShoppingCart();
		sc.addProduto(p);
		session.setAttribute("shoppingCart", sc);
	}

}
