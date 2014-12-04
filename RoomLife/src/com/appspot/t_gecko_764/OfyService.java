package com.appspot.t_gecko_764;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
	static {
        factory().register(Person.class);
        factory().register(Group.class);
        factory().register(Bill.class);
        factory().register(Debt.class);
        factory().register(MaintenanceRequest.class);
        factory().register(Landlord.class);
        factory().register(Building.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
