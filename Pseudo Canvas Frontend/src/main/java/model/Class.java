package model;

public class Class {
	
// ---------- Variables ----------
	public Integer id;
	public Integer section;
	public String name;
	public String description;
	public String info;	// "{days} / {time} / {location} / {room number}"
	public String school;
	
	//public List<Assignment> gradebook;
	
	

// ---------- Constructors ----------
	public Class() {
		init();
	}
	
	public Class(String name) {
		init();
		this.name = name;
	}
	
	public Class(Integer id, Integer section, String name, String des, String info, String school) {
		init();
		this.id = id;
		this.section = section;
		this.name = name;
		this.description = des;
		this.info = info;
		this.school = school;
		//this.gradebook = gradebook;
	}
	
	
	
// ---------- Custom Methods ----------
	
	
	private void init() {
		//this.gradebook = new ArrayList<Assignment>();
		this.info = " ";
		section = 1;
	}
	
	
	
// ---------- To String ----------
	@Override
    public String toString() {
		String out =
				"\n		Name: " + this.name +
				"\n		ID: " + this.id +
    				"\n		Description: " + this.description +
    				"\n		Class Info: " + this.info +
    				"\n		Sections: " + this.section +
    				//"\n		Gradebook: " + this.gradebook.toString() +
    				"\n		School: " + this.school + "\n";
    		return out;
    }
}
