package utility;

public class Truple {

	public Integer grade;
	public String name;
	public String assignmentName;
	
	public Truple(Integer grade, String name) {
		this.grade = grade;
		this.name = name;
	}
	
	public Truple(Integer grade, String name, String assignmentName) {
		this.grade = grade;
		this.name = name;
		this.assignmentName = assignmentName;
	}
}
