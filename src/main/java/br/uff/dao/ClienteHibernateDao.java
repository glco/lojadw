package br.uff.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.inject.Inject;

import br.uff.model.Cliente;
import br.uff.model.Cliente;

public class ClienteHibernateDao implements IGenericDao<Cliente> {

	private SessionFactory sessionFactory = null;
	@Inject
	public ClienteHibernateDao(SessionFactory session) {
		this.sessionFactory = session;
	}
	
	
	@Override
	public List<Cliente> getAll() {
		Session session = sessionFactory.openSession();
		List<Cliente> result = session.createQuery( "from Cliente" ).list();
		session.close();
		return result;
	}

	@Override
	public Cliente getById(int id) {
		Session session = sessionFactory.openSession();
		Cliente item = null;
		try {
			session.getTransaction().begin();
			
			item = (Cliente) session.createQuery("select a from Cliente a where a.id = :id")
					.setParameter("id", id).getSingleResult();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			
		}finally {
			session.close();
		}
		return item;
	}

	@Override
	public void save(Cliente item) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.saveOrUpdate(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Cliente delete(int id) {
		Cliente item = this.getById(id);
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			Logger.getGlobal().log(Level.WARNING, "try delete compra: "+id+"!");
			session.delete(item);
			session.getTransaction().commit();
		}catch(Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Failed to delete compra: "+id+"!", e.getCause());
		}finally {
			session.close();
		}
		return item;
	}

}
