package com.java.hibernate.second;


import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

public class EmployeeApp {

	private Session session;
	private SessionFactory factory;
	public static void main(String ...args){
		EmployeeApp obj=new EmployeeApp();
		obj.configuration();
	//	obj.saveEmployee();
		//obj.evictSecondLevel();
		obj.loadEmployee();
	}
	
	public Session configuration(){
	Configuration configuration=new Configuration();
	configuration.configure("hibernate.cfg.xml");
	factory=configuration.buildSessionFactory();
	session=factory.openSession();
	return session;
	}
	
	public void saveEmployee(){
		Transaction transaction=session.beginTransaction();
		Employee employee=new Employee();
		employee.setName("Aakash");
		employee.setSalay(10000);
		session.save(employee);
		transaction.commit();
		System.out.println("Data Saved");
	}
	
	public void loadEmployee(){
		Employee employee=(Employee)session.load(Employee.class,new Integer(23));
		System.out.println(employee.getName());
		//session.evict(employee);
		System.out.println("1st");
		System.out.println(session.getSessionFactory().getStatistics().getEntityFetchCount());
		System.out.println(session.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
		System.out.println(session.getSessionFactory().getStatistics().getSecondLevelCacheMissCount());
		
		employee=(Employee)session.load(Employee.class,new Integer(23));
		System.out.println("2nd");
		System.out.println(employee.getName());
		System.out.println(factory.getStatistics().getEntityFetchCount());
		System.out.println(factory.getStatistics().getSecondLevelCacheHitCount());
		System.out.println(factory.getStatistics().getSecondLevelCacheMissCount());
		
		session.evict(employee);
		employee=(Employee)session.load(Employee.class,new Integer(23));
		System.out.println(employee.getName());
		System.out.println("3rd");
		System.out.println(factory.getStatistics().getEntityFetchCount());
		System.out.println(factory.getStatistics().getSecondLevelCacheHitCount());
		System.out.println(factory.getStatistics().getSecondLevelCacheMissCount());
	}
	
	public void evictSecondLevel(){
		Map<String,ClassMetadata> classMetadata=factory.getAllClassMetadata();
		for(String entity:classMetadata.keySet()){
			System.out.println("Evicting: "+entity);
			factory.evictEntity(entity);
		}
	}
}