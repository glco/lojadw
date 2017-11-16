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

import br.uff.dao.CategoriaHibernateDao;
import br.uff.dao.IGenericDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Administrador;
import br.uff.model.Categoria;

/**
 * Servlet implementation class CategoriaInternalServlet
 */
@WebServlet(name="categoriaCrud" , urlPatterns="/internal/categoria/*")
public class CategoriaInternalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private IGenericDao<Categoria> categoriaDao;
	
    public CategoriaInternalServlet() {
        super();
        this.categoriaDao = new CategoriaHibernateDao(SessionFactoryHelper.getSessionFactory());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if( action != null && action.equals("delete")) {
			int categoriaId = Integer.parseInt(request.getParameter("id"));
			categoriaDao.delete(categoriaId);
		}
		
		
		List<Categoria> categoriaList = new ArrayList<Categoria>();
		try {
			categoriaList = categoriaDao.getAll();
		}catch(Exception E) {
			Logger.getGlobal().log(Level.SEVERE, "Couldnt get cat list!");
		}
		request.setAttribute("categoriaList",categoriaList);
		request.getRequestDispatcher("categoria.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Logger.getGlobal().log(Level.INFO,"\n==================================================================================\n\t"+action+"\n==================================================================================\n");
		if(action.compareTo("update") == 0) {
			int categoriaId = Integer.parseInt(request.getParameter("id"));
			String descricao = request.getParameter("descricao");
			doUpdate(categoriaId,descricao);
		}else if(action.compareTo("create") == 0) {
			String descricao = request.getParameter("descricao");
			doCreate(descricao);
		}
		doGet(request, response);
			doGet(request, response);
	}

	private void doCreate(String descricao) {
		Categoria cat = new Categoria();
		cat.setDescricao(descricao);
		this.categoriaDao.save(cat);
	}

	private void doUpdate(int categoriaId, String descricao) {
		Categoria cat = this.categoriaDao.getById(categoriaId);
		cat.setDescricao(descricao);
		this.categoriaDao.save(cat);
	}

}
