package br.uff.dao.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryHelper {
	
	protected static SessionFactory sessionFactory = null;
	
	private static void initSessionFactory() {
	  try {
			StandardServiceRegistry standardRegistry = 
		       new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata metaData = 
		        new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metaData.getSessionFactoryBuilder().build();
		   } catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		  }
	}
	  public static SessionFactory getSessionFactory() {
		  if(sessionFactory == null)
			  initSessionFactory();
		  return sessionFactory;
	  } 
}
