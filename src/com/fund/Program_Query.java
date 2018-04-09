package com.fund;

import java.util.Date;

import org.hibernate.Session;

public class Program_Query {

	public static void main(String[] args) {
		
		
		PopulateSampleData();
		
		HibernateUtilities.getSessionFactory().close();
	}
	
	private static void PopulateSampleData() {
		
		Session session = HibernateUtilities.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		User mani = CreateUser("mani",250,29, "Good job","you made it!");
		session.save(mani);
		
		User jagadesh = CreateUser("jagadesh",500,34, "It's movie time!");
		session.save(jagadesh);
		
		User dad = CreateUser("dad",300,9, "yahhoo!");
		session.save(dad);
		
		session.getTransaction().commit();
		session.close();
	}
	
	private static User CreateUser(String name, int goal, int total, String...alerts) {
		
		User user = new User();
		user.setName(name);
		user.getProteinData().setGoal(goal);
		user.getProteinData().setTotal(total);
		user.addHistory(new UserHistory(new Date(), "Set goal to: "+ goal));
		user.addHistory(new UserHistory(new Date(), "Set goal to: "+ total));
		
		for(String alert : alerts) {
			
			user.getGoalAlerts().add(new GoalAlert(alert));
			
		}
		
		return user;
	}
}
