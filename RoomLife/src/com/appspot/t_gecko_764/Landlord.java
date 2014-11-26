package cmcbride.utexas.edu;

import java.util.ArrayList;

public class Landlord {
	private String name;
	private ArrayList<Building> units;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Building> getUnits() {
		return units;
	}
	public void setUnits(ArrayList<Building> units) {
		this.units = units;
	}
	
	
}