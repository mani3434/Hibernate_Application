package com.fund;

import java.util.Collection;
import java.util.Date;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Program {

	public static void main(String[] args) {

		// Configuration config = new Configuration();
		// config.configure();
		// SessionFactory sf = config.buildSessionFactory();

		Session session = HibernateUtilities.getSessionFactory().openSession();

		// Session session = sf.openSession();
		session.beginTransaction();

		User user = new User();

		// user.setId(1);
		user.setName("mani");
		// user.setTotal(10);
		// user.setGoal(250);
		 user.getHistory().add(new UserHistory(new Date(), "set the name to mani"));
		//user.getHistory().put("ME123", new UserHistory(new Date(), "set the name to mani"));
		user.getProteinData().setGoal(300);
		user.getProteinData().setTotal(20);
		 user.getHistory().add(new UserHistory(new Date(), "set the goal to 200"));
		//user.getHistory().put("ME124", new UserHistory(new Date(), "set the goal to 200"));
		session.save(user);
		session.getTransaction().commit();

		session.beginTransaction();

		// User loadedUser = (User)session.get(User.class,1);
		User loadedUser = (User) session.load(User.class, 1);

		System.out.println(loadedUser.toString());

		for (UserHistory uh : loadedUser.getHistory()) {

			//System.out.println("Key value: "+uh.getKey());
			//System.out.println(uh.getValue().toString());
			 System.out.println(uh.toString());
		}

		int total = loadedUser.getProteinData().getTotal() + 50;
		user.getHistory().add(new UserHistory(new Date(), "added 50 protein"));
		//user.getHistory().put("ME125",new UserHistory(new Date(), "added 50 protein"));
		loadedUser.getProteinData().setTotal(total);

		session.getTransaction().commit();

		session.close();

		// sf.close();

		HibernateUtilities.getSessionFactory().close();
	}
}
