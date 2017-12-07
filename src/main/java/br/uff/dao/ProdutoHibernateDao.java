package br.uff.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.uff.model.Categoria;
import br.uff.model.Produto;

public class ProdutoHibernateDao implements IGenericDao<Produto> {
	
	private SessionFactory sessionFactory;
	
	 public ProdutoHibernateDao(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Produto> getAll() {
		Session session = sessionFactory.openSession();
		List<Produto> result = session.createQuery( "from Produto" ).list();
		session.close();
		return result;
	}
	
	public ArrayList<Produto> getByCategoryId(int id) {
		Session session = sessionFactory.openSession();
		ArrayList<Produto> prod = null;
		try {
			session.getTransaction().begin();
			
			prod = (ArrayList<Produto>) session.createQuery("select a from Produto a where a.Categoria.id = :id")
					.setParameter("id", id).list();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			
		}finally {
			session.close();
		}
		return prod;
	}

	
	@Override
	public Produto getById(int id) {
		Session session = sessionFactory.openSession();
		Produto prod = null;
		try {
			session.getTransaction().begin();
			
			prod = (Produto) session.createQuery("select a from Produto a where a.id = :id")
					.setParameter("id", id).getSingleResult();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			
		}finally {
			session.close();
		}
		return prod;
	}

	@Override
	public void save(Produto item) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.saveOrUpdate(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Produto delete(int id) {
		Produto prod = this.getById(id);
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			Logger.getGlobal().log(Level.WARNING, "try delete administrador: "+id+"!");
			session.delete(prod);
			session.getTransaction().commit();
		}catch(Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Failed to delete administrador: "+id+"!", e.getCause());
		}finally {
			session.close();
		}
		return prod;
	}

}
