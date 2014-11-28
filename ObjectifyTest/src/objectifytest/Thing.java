package objectifytest;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Thing {
	@Id Long id;
	@Index String name;
	int color;
	OtherThing thing;
	
	public Thing(String name, int color){
		this.name = name;
		this.color = color;
		this.thing = new OtherThing("String");
	}
}
