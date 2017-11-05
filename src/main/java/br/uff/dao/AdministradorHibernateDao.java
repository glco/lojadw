package br.uff.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import br.uff.model.Administrador;

public class AdministradorHibernateDao implements IGenericDao<Administrador>,AdministradorDao {
	
	private SessionFactory sessionFactory;
	
	public AdministradorHibernateDao(SessionFactory sessionFactory) {
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
		}catch(HibernateException he){
			
		}finally {
			session.close();
		}
		return adm;
	}
	
	
	@Override
	public List<Administrador> getAll() {
		Session session = sessionFactory.openSession();
		List<Administrador> admins = session.createQuery("from Administrador").list();
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
			session.save(item);
			session.getTransaction().commit();
		}catch(HibernateException e) {
			Logger.getGlobal().log(Level.SEVERE, "Exception at AdministradorHibernateDAO SAVE!", e.getCause());
		}finally {
			session.close();
		}
	}

	@Override
	public Administrador delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
