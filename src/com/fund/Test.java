package com.fund;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

public class Test {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		Session session = HibernateUtilities.getSessionFactory().openSession();

		session.beginTransaction();

		// Query query = session.createQuery("select user from User user where user.name
		// = :n");
		// query.setString("n", "mani");
		// Query query = session.createQuery("from GoalAlert");

		// Query query = session.getNamedQuery("AllGoalAlerts");
		// query.setFirstResult(0);
		// query.setMaxResults(2);
		
		/*Query query = session.createQuery("select new com.fund.UserTotal(user.name, user.proteinData.total)"+
		"from User user");
		
		List<UserTotal> user = query.list();

		for (UserTotal u : user) {
			System.out.println(u.getName());
			System.out.println(u.getTotal());
			// System.out.println(u.toString());
			System.out.println("--------------------");
			// System.out.println(u.getProteinData().toString());
			// System.out.println(u.getHistory().toString());

		}*/
		
/*		Criteria criteria = session.createCriteria(User.class)
				.add(Restrictions.or(
						Restrictions.eq("name", "mani"), 
						Restrictions.eq("name", "dad")
						)).setProjection(Projections.projectionList()
								.add(Projections.property("name"))
								.add(Projections.property("id")));
		
		List<Object[]> list = criteria.list();
		
		for(Object[] u : list) {
			
			for(Object o: u) {
			System.out.println(o.toString());
			}
		}*/
		
		
		//joins using criteria
	/*	Criteria criteria = session.createCriteria(User.class)
				.createAlias("proteinData", "pd")
				.add(Restrictions.or(
						Restrictions.eq("name", "mani"), 
						Restrictions.eq("name", "dad")
						)).setProjection((Projections.property("pd.total")));
		
		List<User> list = criteria.list();
		
		for(User u : list) {
			
			System.out.println(u.toString());
			
		}*/
		
		//query by example
	/*	User user = new User();
		user.setId(1);
		user.setName("mani");

		Example e = Example.create(user);

		Criteria c = session.createCriteria(User.class).add(e);
		List<User> li = c.list();
		
		
		for(User u:li) {
			System.out.println(u.toString());
		}
		*/
		
		Query query = session.createQuery("update ProteinData pd set pd.goal=0");
		query.executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		HibernateUtilities.getSessionFactory().close();

	}

}
