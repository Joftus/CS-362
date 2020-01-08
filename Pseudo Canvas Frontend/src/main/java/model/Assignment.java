package model;

public class Assignment {

// ---------- Variables ----------
	public Integer id;
	public String assignment;
	public Integer grade;
	public String userEmail;
	public String ta;
	public String className;
	public Boolean viewable;

	
	
// ---------- Constructors ----------
	public Assignment() {
		init();
	}
	
	public Assignment(String userEmail, String className, Integer grade, String assignment) {
		init();
		this.userEmail = userEmail;
		this.className = className;
		this.grade = grade;
		this.assignment = assignment;
	}
	
	public Assignment(Integer id, String assignment, Integer grade, String userEmail, String ta, String className, Boolean viewable) {
		init();
		this.id = id;
		this.userEmail = userEmail;
		this.ta = ta;
		this.className = className;
		this.grade = grade;
		this.assignment = assignment;
		this.viewable = viewable;
	}

	

// ---------- Custom Methods ----------
	private void init() {
	}
			
		
		
// ---------- To String ------------
	@Override
	public String toString() {
		String out = 
				"\n		assignment: " + this.assignment +
				"\n		grade: " + this.grade +
				"\n		user: " + this.userEmail +
				"\n		ta: " + this.ta +
				"\n		class: " + this.className +
				"\n		viewable: " + this.viewable +
				"\n		id: " + this.id + "\n";
		return out;		
	}
}
