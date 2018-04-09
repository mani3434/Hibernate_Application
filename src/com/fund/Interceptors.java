package com.fund;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.EmptyInterceptor;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.type.Type;

public class Interceptors extends EmptyInterceptor {

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
			System.out.println("Saving an entity");
			return false;
	}

	@Override
	public void postFlush(Iterator entities) {
		System.out.println("After entity has been flushed");
	}

	public static void main(String[] args) {
		
		Configuration config = new Configuration().setInterceptor(new Interceptors()).configure();
		//config.configure();
	
		SessionFactory sf = config.buildSessionFactory();
	
		Session s = sf.openSession();
		//s.beginTransaction();
		
		User user = new User();
		user.setName("mom");
		s.save(user);
		s.close();
		
		s = sf.openSession();
		
		Query q = s.createQuery("from User");
		
		List<User> l = q.list();
		for(User a: l) {
			System.out.println(a);
		}
		//s.getTransaction().commit();
		s.close();
		
		Session session = sf.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		
		ScrollableResults users = criteria.setCacheMode(CacheMode.IGNORE).scroll();
		int count = 0;
		
		while(users.next()) {
			
			User user1 = (User) users.get(0);
			user.setProteinData(new ProteinData());
			session.save(user1);
			
			if(++count %10 == 0) {
				session.flush();
				session.clear();
			}
			System.out.println(user1.getName());
		}
		
		
		User u = (User) session.load(User.class, 1);
		System.out.println(u.getName());
		
		//data filters
		
		
		session.getTransaction().commit();
		session.close();
		
		sf.close();
	}

}
