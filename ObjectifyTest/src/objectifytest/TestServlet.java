package objectifytest;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static objectifytest.OfyService.ofy;

public class TestServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Thing porsche = new Thing("2FAST", 1);
		
		ofy().save().entity(porsche).now();    // async without the now()

		assert porsche.id != null;    // id was autogenerated

		// More likely this is what you will type
		Thing fetched2 = ofy().load().type(Thing.class).id(porsche.id).now();

		porsche.color = 2;
		ofy().save().entity(porsche).now();    // async without the now()

		// Delete it
		ofy().delete().entity(porsche).now();    // async without the now()
		
		resp.getWriter().println("Finished test");
	}
}
