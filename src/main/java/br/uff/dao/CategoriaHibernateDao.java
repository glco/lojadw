package br.uff.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.uff.model.Administrador;
import br.uff.model.Categoria;

public class CategoriaHibernateDao implements IGenericDao<Categoria> {
	
	private SessionFactory sessionFactory = null;
	
	public CategoriaHibernateDao(SessionFactory session) {
		this.sessionFactory = session;
	}

	@Override
	public List<Categoria> getAll(){
		Session session = sessionFactory.openSession();
		List<Categoria> result = session.createQuery( "from Categoria" ).list();
		session.close();
		return result;
	}
	

	@Override
	public Categoria getById(int id) {
		Session session = sessionFactory.openSession();
		Categoria cat = null;
		try {
			session.getTransaction().begin();
			
			cat = (Categoria) session.createQuery("select a from Categoria a where a.id = :id")
					.setParameter("id", id).getSingleResult();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			
		}finally {
			session.close();
		}
		return cat;
	}

	@Override
	public void save(Categoria item) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.saveOrUpdate(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Categoria delete(int id) {
		Categoria cat = this.getById(id);
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			Logger.getGlobal().log(Level.WARNING, "try delete administrador: "+id+"!");
			session.delete(cat);
			session.getTransaction().commit();
		}catch(Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Failed to delete administrador: "+id+"!", e.getCause());
		}finally {
			session.close();
		}
		return cat;
	}

}
