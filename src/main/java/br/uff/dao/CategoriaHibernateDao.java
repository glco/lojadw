package br.uff.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.inject.Inject;

import br.uff.model.Categoria;

public class CategoriaHibernateDao implements IGenericDao<Categoria> {
	
	private SessionFactory sessionFactory = null;
	@Inject
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Categoria item) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Serializable id = session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Categoria delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
