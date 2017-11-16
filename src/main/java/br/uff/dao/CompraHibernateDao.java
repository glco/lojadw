package br.uff.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.uff.model.Compra;
import br.uff.model.Compra;

public class CompraHibernateDao implements IGenericDao<Compra> {

	
	private SessionFactory sessionFactory = null;
	
	 public CompraHibernateDao(SessionFactory session) {
		this.sessionFactory = session;
	}
	
	@Override
	public List<Compra> getAll() {
		Session session = sessionFactory.openSession();
		List<Compra> result = session.createQuery( "from Compra" ).list();
		session.close();
		return result;
	}

	@Override
	public Compra getById(int id) {
		Session session = sessionFactory.openSession();
		Compra item = null;
		try {
			session.getTransaction().begin();
			
			item = (Compra) session.createQuery("select a from Compra a where a.id = :id")
					.setParameter("id", id).getSingleResult();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			
		}finally {
			session.close();
		}
		return item;
	}

	@Override
	public void save(Compra item) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.saveOrUpdate(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Compra delete(int id) {
		Compra item = this.getById(id);
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
