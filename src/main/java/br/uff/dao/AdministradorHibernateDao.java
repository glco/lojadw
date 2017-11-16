package br.uff.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import br.uff.model.Administrador;

public class AdministradorHibernateDao extends AdministradorDao {
	
	private SessionFactory sessionFactory;
	
	public AdministradorHibernateDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Administrador getByLogin(String login, String password) {
		Session session = sessionFactory.openSession();
		Administrador adm = null;
		try {
			session.getTransaction().begin();
			
			adm = (Administrador) session.createQuery("select a from Administrador a where a.login = :login and a.senha = :password")
					.setParameter("login", login).setParameter("password",password).getSingleResult();
			
			session.getTransaction().commit();
		}catch(HibernateException | NoResultException ex){
			Logger.getGlobal().log(Level.SEVERE, ex.getMessage());
		}finally {
			session.close();
		}
		return adm;
	}
	
	
	@Override
	public List<Administrador> getAll() {
		Session session = sessionFactory.openSession();
		List<Administrador> admins = session.createQuery("from Administrador").getResultList();
		session.close();
		return admins;
	}
	
	
	
	@Override
	public Administrador getById(int id) {
		Session session = sessionFactory.openSession();
		Administrador adm = null;
		try {
			session.getTransaction().begin();
			
			adm = (Administrador) session.createQuery("select a from Administrador a where a.id = :id")
					.setParameter("id", id).getSingleResult();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			
		}finally {
			session.close();
		}
		return adm;
	}

	@Override
	public void save(Administrador item) {
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			session.saveOrUpdate(item);
			session.getTransaction().commit();
		}catch(HibernateException e) {
			Logger.getGlobal().log(Level.SEVERE, "Exception at AdministradorHibernateDAO SAVE!", e.getCause());
		}finally {
			session.close();
		}
	}

	@Override
	public Administrador delete(int id) {
		Administrador adm = this.getById(id);
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			Logger.getGlobal().log(Level.WARNING, "try delete administrador: "+id+"!");
			session.delete(adm);
			session.getTransaction().commit();
		}catch(Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Failed to delete administrador: "+id+"!", e.getCause());
		}finally {
			session.close();
		}
		return adm;
	}

}
