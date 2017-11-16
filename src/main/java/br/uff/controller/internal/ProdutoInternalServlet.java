package br.uff.controller.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
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
 * Servlet implementation class ProdutoInternalServlet
 */
@WebServlet(urlPatterns = "/internal/produto/*" , name = "produtoController")
public class ProdutoInternalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private IGenericDao<Produto> produtoDao;
	private IGenericDao<Categoria> categoriaDao;
    public ProdutoInternalServlet() {
        super();
        this.produtoDao = new ProdutoHibernateDao(SessionFactoryHelper.getSessionFactory());
        this.categoriaDao = new CategoriaHibernateDao(SessionFactoryHelper.getSessionFactory());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if( action != null && action.equals("delete")) {
			int produtoId = Integer.parseInt(request.getParameter("id"));
			produtoDao.delete(produtoId);
		}
		Logger.getGlobal().log(Level.INFO, "\n\n   =PRODUTO=  \n\n");
		
		List<Produto> produtoList = new ArrayList<Produto>();
		List<Categoria> categoriaList = new ArrayList<Categoria>();
		try {
			produtoList = produtoDao.getAll();
		}catch(Exception E) {
			Logger.getGlobal().log(Level.SEVERE, "Couldnt get prod list!");
		}
		categoriaList = categoriaDao.getAll();
		request.setAttribute("produtoList",produtoList);
		request.setAttribute("categoriaList",categoriaList);
		request.getRequestDispatcher("produto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Logger.getGlobal().log(Level.INFO,"\n==================================================================================\n\t"+action+"\n==================================================================================\n");
		if(action.compareTo("update") == 0) {
			doUpdate(request);
		}else if(action.compareTo("create") == 0) {
			doCreate(request);
		}
		doGet(request, response);
	}

	private void doCreate(HttpServletRequest request) {
		Produto prod = new Produto();
		String nome = request.getParameter("nome");
		float valor = Float.parseFloat(request.getParameter("valor"));
		String descricao = request.getParameter("descricao");
		
		int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
		Categoria cat = categoriaDao.getById(categoriaId);
		
		prod.setDescricao(descricao);
		prod.setNome(nome);
		prod.setIdCategoria(cat);
		prod.setValor(valor);
		
		produtoDao.save(prod);
	}

	private void doUpdate(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Produto prod = produtoDao.getById(id);
		String nome = request.getParameter("nome");
		String valor = request.getParameter("valor");
		String descricao = request.getParameter("descricao");
		int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
		Categoria cat = categoriaDao.getById(categoriaId);
		
		
		if(descricao != null)
			prod.setDescricao(descricao);
		if(nome != null)
			prod.setNome(nome);
		if(valor != null)
			prod.setValor(Float.parseFloat(valor));
		prod.setIdCategoria(cat);
		
		produtoDao.save(prod);
	}

}
