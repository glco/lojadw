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
import br.uff.model.Produto;
import br.uff.model.ShoppingCart;

/**
 * Servlet implementation class CarrinhoDeCompras
 */
@WebServlet(name = "carrinhoServlet", urlPatterns = "/carrinho")
public class CarrinhoDeCompras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrinhoDeCompras() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger.getGlobal().log(Level.INFO, "Carrinho");
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
		List<Produto> produtoList = (cart == null)?  null : new ArrayList<Produto>(cart.getProdutos().values());
		request.setAttribute("produtoList",produtoList);
		request.getRequestDispatcher("public/carrinho.jsp").forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
