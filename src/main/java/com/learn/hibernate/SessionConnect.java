package com.learn.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionConnect {
	private static SessionFactory sessionFactory;
	public static SessionFactory connectSession() {
		if(sessionFactory == null) {
			
			Configuration cfg=new Configuration();
			cfg.configure("/com/learn/hibernate/hibernate.cfg.xml");
			sessionFactory = cfg.buildSessionFactory();
		}
		System.out.println("configured sessionfactor");
		
		return sessionFactory;
	}
}
