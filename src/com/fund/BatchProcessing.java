package com.fund;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class BatchProcessing {

	@SuppressWarnings("deprecation")
	public static void main(String[]args) {
		
	Session session = HibernateUtilities.getSessionFactory().openSession();

	session.beginTransaction();

	//basic update statement to table table data in database	
	/*Query query = session.createQuery("update ProteinData pd set pd.goal=0");
	query.executeUpdate();
	*/
	
	Criteria criteria = session.createCriteria(User.class);
	
	ScrollableResults users = criteria.setCacheMode(CacheMode.IGNORE).scroll();
	int count = 0;
	
	while(users.next()) {
		
		User user = (User) users.get(0);
		user.setProteinData(new ProteinData());
		session.save(user);
		
		if(++count %10 == 0) {
			session.flush();
			session.clear();
		}
		System.out.println(user.getName());
	}
	
	
	
	session.getTransaction().commit();
	session.close();
	HibernateUtilities.getSessionFactory().close();

}
	
}
