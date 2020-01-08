package model;

public class School {

// ---------- Variables ----------
	public Integer id;
	public String name;
	
		

// ---------- Constructors ----------
	public School() {
		init();
	}
	
	public School(String name) {
		init();
		this.name = name;
	}
	

	
// ---------- Custom Methods ----------
	private void init() {
	}
	
	
	
// ---------- To String ----------
	@Override
    public String toString() {
        String out = 
        			"\n		Name: " + this.name +
                "\n		ID: " + this.id + "\n";
        return out;
	}
}
