package com.learn.hibernate ;
  
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
  
public class StoreData {  
	private static boolean isEnd = true;
	private static SessionFactory factory;
	private static Scanner sc;
	public static void main(String[] args) {  
		sc = new Scanner(System.in);
		
		//creating seession factory object  
		factory = SessionConnect.connectSession();  

		//creating session object  
		System.out.println("*-------------------------------------*");
		while(isEnd) {
			System.out.println("/n 1.Press 1 to add new user");
			System.out.println("/n 2.Press 2 to View all users");
			System.out.println("/n 3.Press 3 to update a user");
			System.out.println("/n 4.Press 4 to delete a user");
			System.out.println("/n 5.Press 5 to exit");
			System.out.println("Enter Your choice");
			int choice = sc.nextInt();
			switch(choice) {
				case 1: addUser();break;
				case 2: readDataFromTable();break;
				case 3: updateUser(); break;
				case 4: deleteUser();break;
				case 5: isEnd = false;break;
				default:System.out.println("Invalid choice");break;
			}
			
		} 
		if(isEnd == false) {
			System.exit(0);
		}
		System.out.println("successfully saved");  
	}  
	
	private static void addUser() {
		
		Session session=factory.openSession();  

		//creating transaction object  
		Transaction t=session.beginTransaction();  

		System.out.println("Enter Number of Students");
		int num = sc.nextInt();
		
		Student e1;  
		for(int i = 1; i <= num ; i++)
		{
			e1 = new Student();
			System.out.println("Enter roll number");
			int rno = sc.nextInt();
			e1.setRollNo(rno);
			System.out.println("Enter Name:");
			String name = sc.next();
			e1.setName(name);  
			sc.nextLine();
			System.out.println("Enter Age:");
			int age = sc.nextInt();
			e1.setAge(age);
			session.save(e1);//persisting the object 
		}
		t.commit();
		session.close();
	}
	
	private static void readDataFromTable() {

		//creating seession factory object  

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
	private static void updateUser() {
		Session session=factory.openSession();  
		//creating transaction object  
		Transaction t=session.beginTransaction();
		System.out.println("Enter roll no");
		int rno = sc.nextInt();
		Student s = session.load(Student.class, rno);
		System.out.println("update age of: "+s.getAge()+" to : ");
		int age = sc.nextInt();
		s.setAge(age);
		System.out.println("Update name : "+s.getName()+" to: ");
		String name = sc.next();
		sc.nextLine();

		s.setName(name);
		//			session.save(s);ye

		t.commit();
		readDataFromTable();
		session.close();
		//t.commit();//transaction is committed  
	}
	private static void deleteUser() {
		Session session=factory.openSession();  
		//creating transaction object  
		Transaction t=session.beginTransaction();
		System.out.println("Enter a roll number");
		int rno = sc.nextInt();
		Student s = session.load(Student.class, rno);
		session.delete(s);
		t.commit();
		readDataFromTable();
		session.close();
	}
}  
