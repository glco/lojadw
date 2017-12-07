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
@WebServlet(name = "clienteCrud" ,urlPatterns = "/internal/cliente/*")
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
			doUpdate(clienteinId,buildCliente(request));
		}else if(action.compareTo("create") == 0) {
			doCreate(buildCliente(request));
		}
		doGet(request, response);
	}

	private Cliente buildCliente(HttpServletRequest request) {
		Cliente c = new Cliente();
		c.setNome(request.getParameter("nome"));
		c.setEmail(request.getParameter("email"));
		c.setCpf(request.getParameter("cpf"));
		c.setEstado(request.getParameter("estado"));
		c.setBairro(request.getParameter("bairro"));
		c.setCidade(request.getParameter("cidade"));
		c.setEndereco(request.getParameter("endereco"));
		c.setCep(request.getParameter("cep"));
		c.setReferencia(request.getParameter("referencia"));
		c.setTelefoneFixo(request.getParameter("telefone"));
		c.setCelular(request.getParameter("celular"));
		c.setCartao(request.getParameter("cartao"));
		c.setBandeira(request.getParameter("bandeira"));
		return c;
	}

	private void doCreate(Cliente c) {
		clienteDao.save(c);
	}

	private void doUpdate(int id,Cliente modified) {
		Cliente cliente = clienteDao.getById(id);
		clienteDao.save(cliente);
	}
	private Cliente copyCliente(Cliente original,Cliente modified) {
		original.setNome(modified.getNome());
		original.setEmail(modified.getEmail());
		original.setCpf(modified.getCpf());
		original.setEstado(modified.getEstado());
		original.setBairro(modified.getBairro());
		original.setCidade(modified.getCidade());
		original.setEndereco(modified.getEndereco());
		original.setCep(modified.getCep());
		original.setReferencia(modified.getReferencia());
		original.setTelefoneFixo(modified.getTelefoneFixo());
		original.setCelular(modified.getCelular());
		original.setCartao(modified.getCartao());
		original.setBandeira(modified.getBandeira());
		return original;
	}
	
	
}
