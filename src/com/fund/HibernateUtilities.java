package com.fund;



import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtilities {

	
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry serviceRegistry;
	
	
	static {
		try {
			
			serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata metaData = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

			sessionFactory =  metaData.getSessionFactoryBuilder().build();
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println("problem creating session factory");
			
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	} 
}
