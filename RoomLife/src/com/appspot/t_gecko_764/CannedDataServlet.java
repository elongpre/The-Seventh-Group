package com.appspot.t_gecko_764;

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
	}
}
