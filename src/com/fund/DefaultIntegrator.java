package com.fund;

import org.hibernate.boot.Metadata;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;

import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class DefaultIntegrator implements Integrator{

	@Override
	public void disintegrate(SessionFactoryImplementor arg0, SessionFactoryServiceRegistry arg1) {
		// TODO Auto-generated method stub
		
	}


	public void integrate(Configuration arg0, SessionFactoryImplementor arg1, SessionFactoryServiceRegistry serviceRegistry) {
		
	EventListenerRegistry r = serviceRegistry.getService(EventListenerRegistry.class);
	r.prependListeners(EventType.LOAD, new LoadEventListener());
		
	}
	
	
	public void integrate(Metadata arg0, SessionFactoryImplementor arg1, SessionFactoryServiceRegistry arg2) {
		
		
	}

}
