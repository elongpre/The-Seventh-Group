package com.appspot.t_gecko_764.client;

import java.util.ArrayList;
import java.util.List;

import com.appspot.t_gecko_764.*;

public class HomeController {

	public static List<Bill> getBills(){
		ArrayList<Bill> bills = new ArrayList<Bill>();
		bills.add(new Bill("ThisBill"));
		bills.add(new Bill("ThatBill"));
		return bills;
	}
}
