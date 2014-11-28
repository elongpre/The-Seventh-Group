package objectifytest;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class OtherThing {
	@Id Long id;
	private String name;
	private Thing things;
	
	public OtherThing(String name){
		this.name = name;
		this.things = new Thing("thing", 2);
	}
}
