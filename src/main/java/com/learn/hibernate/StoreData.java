package com.learn.hibernate ;
  
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
  
public class StoreData {  
	public static void main(String[] args) {  

		Scanner sc = new Scanner(System.in);
		//creating configuration object  
		Configuration cfg=new Configuration();  
		cfg.configure("/com/learn/hibernate/hibernate.cfg.xml");//populates the data of the configuration file  

		//creating seession factory object  
		SessionFactory factory=cfg.buildSessionFactory();  

		//creating session object  
		Session session=factory.openSession();  

		//creating transaction object  
		Transaction t=session.beginTransaction();  

//		System.out.println("Enter Number of Students");
//		int num = sc.nextInt();
//		
//		Student e1;  
//		for(int i = 1; i <= num ; i++)
//		{
//			e1 = new Student();
//			System.out.println("Enter roll number");
//			int rno = sc.nextInt();
//			e1.setRollNo(rno);
//			System.out.println("Enter Name:");
//			String name = sc.next();
//			e1.setName(name);  
//			sc.nextLine();
//			System.out.println("Enter Age:");
//			int age = sc.nextInt();
//			e1.setAge(age);
//			session.save(e1);//persisting the object 
//			
//		}
		  

		
		readDataFromTable();
		
//		System.out.println("Delete a record?");
		System.out.println("Update a record?");
		String ans = sc.next();
		sc.nextLine();
		if(ans.equalsIgnoreCase("yes")) {
			System.out.println("Enter a record number");
			int rno = sc.nextInt();
			Student s = session.load(Student.class, rno);
			System.out.println("update age of: "+s.getAge()+" to : ");
			int age = sc.nextInt();
			s.setAge(age);
			System.out.println("Update name : "+s.getName()+" to: ");
			String name = sc.next();
			sc.nextLine();
			
			s.setName(name);
//			session.save(s);
			
			t.commit();
			readDataFromTable();
		}
		else if(ans.equalsIgnoreCase("no")) {
			System.out.println("no selected");
		}
		else {
			System.out.println("Wrong choice");
		}
		//t.commit();//transaction is committed  
		session.close();  

		System.out.println("successfully saved");  

	}  
	
	
	
	
	private static void readDataFromTable() {
		Configuration cfg=new Configuration();  
		cfg.configure("/com/learn/hibernate/hibernate.cfg.xml");//populates the data of the configuration file  

		//creating seession factory object  
		SessionFactory factory=cfg.buildSessionFactory();  

		//creating session object  
		Session session=factory.openSession();  
		List StudentList = new ArrayList();
		StudentList = session.createQuery("From Student").list();
		System.out.println("|S.NO|Name|Age|RollNo-PrimaryKey|");
		Student e1 = new Student();
		for(int i = 0 ; i < StudentList.size(); i ++)
		{
			e1 =(Student) StudentList.get(i);
			System.out.println("------------------------");
			System.out.println("| "+ (i+1) + " | "+e1.getName() + " | " + e1.getAge()+" | "+e1.getRollNo());
		}
	}
}  