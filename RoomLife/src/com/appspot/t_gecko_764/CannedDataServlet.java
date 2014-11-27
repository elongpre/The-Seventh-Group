package com.appspot.t_gecko_764;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServlet;

public class CannedDataServlet extends HttpServlet{
	public void doGet(){
		Person alice = new Person("Alice", "alice@example.com");
		Person bob = new Person("Bob", "bob@example.com");
		Person candice = new Person("Candice", "candice@example.com");
		Person danny = new Person("Danny", "danny@example.com");
		Group group = new Group("TestGroup", alice);
		group.addMember(bob);
		group.addMember(candice);
		group.addMember(danny);
		Group guysGroup = new Group("GuysGroup", bob);
		guysGroup.addMember(danny);		
		
		Calendar deadline = new GregorianCalendar();
		deadline.add(Calendar.DAY_OF_MONTH, 10);
		Bill bill1 = new Bill.Builder("Water", 11.11, alice).setGroup(group).build();
		
		
	}
}
