package com.fund;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class NativeSql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtilities.getSessionFactory().openSession();

		session.beginTransaction();

		Query query = session.createSQLQuery("select * from Users").addEntity(User.class);
		
		List<User>list = query.list();
		
		for(User u :list)
		{
			System.out.println(u.toString());
		}
		
		
		/*User u = (User) session.load(User.class, 1);
		System.out.println(u.getName());*/
		
		
		
		
		session.getTransaction().commit();
		session.close();
		HibernateUtilities.getSessionFactory().close();

	}

}
