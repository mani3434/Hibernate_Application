package com.fund;

import java.util.Date;

import org.hibernate.Session;

public class Program2 {

	public static void main(String[] args) {

		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();

		User user = new User();

		user.setName("mani");
		user.addHistory(new UserHistory(new Date(), "set the name to mani"));

		user.getProteinData().setGoal(300);
		user.getProteinData().setTotal(20);
		user.addHistory(new UserHistory(new Date(), "set the goal to 200"));

		user.getGoalAlerts().add(new GoalAlert("Congratulations!"));
		user.getGoalAlerts().add(new GoalAlert("You did it!"));
		
		session.save(user);
		
		session.getTransaction().commit();

		session.beginTransaction();


		User loadedUser = (User) session.load(User.class, 1);
		System.out.println(loadedUser.toString());

		for (UserHistory uh : loadedUser.getHistory()) {

			System.out.println(uh.toString());
		}

		int total = loadedUser.getProteinData().getTotal() + 50;
		loadedUser.getProteinData().setTotal(total);
		
		user.addHistory(new UserHistory(new Date(), "added 50 protein"));
		
		user.setProteinData(new ProteinData());
		session.getTransaction().commit();

		session.close();

		

		HibernateUtilities.getSessionFactory().close();
	}
}
