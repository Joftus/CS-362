package utility;

import java.util.ArrayList;
import java.util.List;

import model.Assignment;
import model.Class;
import model.Message;
import model.School;
import model.User;

public class ObjectCreator {

	/*
	 * This class is for converting from the String response from the back end,
	 * back to the data structure we are using
	 */
	
	public static User toUser(String json) {
		User user = null;
		if (json.length() < 5) {
			return null;
		}
		int[] obj = {json.indexOf("id"), json.indexOf("email"), json.indexOf("password"), json.indexOf("name"), json.indexOf("type"), 
				json.indexOf("pri"), json.indexOf("cGroup"), json.indexOf("gGroup"), json.indexOf("school"), json.indexOf("classes"), json.indexOf("messages"), json.indexOf("assignments")};
		try {
			Integer id = Integer.parseInt(json.substring(obj[0] + 4, obj[1] - 2));
			String email = json.substring(obj[1] + 8, obj[2] - 3);
			String password = json.substring(obj[2] + 11, obj[3] - 3);
			String name = json.substring(obj[3] + 7, obj[4] - 3);
			String type = json.substring(obj[4] + 7, obj[5] - 3);
			String sPri = json.substring(obj[5] + 6, obj[6] - 3);
			Integer cGroup = Integer.parseInt(json.substring(obj[6] + 8, obj[7] - 2));
			Integer gGroup = Integer.parseInt(json.substring(obj[7] + 8, obj[8] - 2));
			School school = toSchool(json.substring(obj[8] + 9, obj[9] - 3));
			List<Class> classes = new ArrayList<Class>();
			List<Assignment> assignments = new ArrayList<Assignment>();
			List<Message> messages = new ArrayList<Message>();
			if (json.substring(obj[9], obj[10]).length() < 15) classes.add(new Class(0, 0, "blank", "blank", "blank", "blank"));
			else classes = toClassList(json.substring(obj[9] + 9, obj[10] - 2));
			if (json.substring(obj[10], obj[11]).length() < 15) assignments.add(new Assignment(0, "blank", 0, "blank", "blank", "blank", false));
			else assignments = toAssignmentList(json.substring(obj[11] + 10, json.length() - 3));
			if (json.substring(obj[11], json.length()).length() < 25) messages.add(new Message(0, "blank", "blank", false, false, "blank", "blank"));
			else messages = toMessageList(json.substring(obj[10] + 11, obj[11] - 4));
			boolean pri;
			if (sPri.startsWith("t")) pri = true;
			else pri = false;
			user = new User(id, email, password, name, type, pri, cGroup, gGroup, school, classes, messages, assignments);
			return user;
		}
		catch (Exception l) {
			System.out.println(l.getMessage());
			l.printStackTrace();
			System.out.println("\nError in ObjectCreator.toUser");
			return null;
		}
	}
	
	
	
	public static School toSchool(String json) {
		School school = new School();
		if (json.length() < 5) {
			return school;
		}
		int[] obj = {json.indexOf("id"), json.indexOf("name")};
		try {
			school.id = Integer.parseInt(json.substring(obj[0] + 4, obj[1] - 2));
			school.name = json.substring(obj[1] + 7, json.length() - 1);
		}
		catch (Exception z) {
			z.printStackTrace();
			System.out.println("\nError in toSchool");
		}
		return school;
	}
	
	
	
	public static Class toClass(String json) {
		if (json.length() < 5) {
			return null;
		}
		Class c = null;
		int[] obj = {json.indexOf("id"), json.indexOf("section"), json.indexOf("name"), json.indexOf("description"), json.indexOf("info"), json.indexOf("school")};
		try {
			Integer id = Integer.parseInt(json.substring(obj[0] + 4, obj[1] - 2));
			Integer section = Integer.parseInt(json.substring(obj[1] + 9, obj[2] - 2));
			String name = json.substring(obj[2] + 7, obj[3] - 3);
			String des = json.substring(obj[3] + 14, obj[4] - 3);
			String info = json.substring(obj[4] + 7, obj[5] - 3);
			String school = json.substring(obj[5] + 9, json.length() - 2);
			c = new Class(id, section, name, des, info, school);
		}
		catch (Exception z) {
			z.printStackTrace();
			System.out.println("\n\n\nError in toClass\n\n\n");
		}
		return c;
	}
	
	
	
	public static Assignment toAssignment(String json) {
		if (json.length() < 5) {
			return null;
		}
		Assignment grade = null;
		int[] obj = {json.indexOf("id"), json.indexOf("assignment"), json.indexOf("grade"), json.indexOf("userEmail"), json.indexOf("ta"), json.indexOf("className"), json.indexOf("viewable")};
		try {
			Integer id = Integer.parseInt(json.substring(obj[0] + 4, obj[1] - 2));
			String assign = json.substring(obj[1] + 13, obj[2] - 3);
			Integer gra = Integer.parseInt(json.substring(obj[2] + 7, obj[3] - 2));
			String email = json.substring(obj[3] + 12, obj[4] + 8);
			String ta = json.substring(obj[4] + 16, obj[5] - 3);
			String c = json.substring(obj[5] + 12, obj[6] - 3);
			String sViewable = json.substring(obj[6], json.length() - 2);
			boolean viewable;
			if (sViewable.startsWith("t")) viewable = true;
			else viewable = false;
			grade = new Assignment(id, assign, gra, email, ta, c, viewable);
		}
		catch (Exception z) {
			z.printStackTrace();
			System.out.println("\nError in toGrade");
		}
		return grade;
	}
	
	
	
	public static Message toMessage(String json) {
		if (json.length() < 5) {
			return null;
		}
		Message msg = null;
		int[] obj = {json.indexOf("id"), json.indexOf("msg"), json.indexOf("date"), json.indexOf("sent"), json.indexOf("notification"), json.indexOf("toEmail"), json.indexOf("fromEmail")};
		try {
			Integer id = Integer.parseInt(json.substring(obj[0] + 4, obj[1] - 2));
			String note = json.substring(obj[1] + 6, obj[2] - 3);
			String date = json.substring(obj[2] + 7, obj[3] - 21);
			String sSent = json.substring(obj[3] + 6, obj[4] - 2);
			String sNotification = json.substring(obj[4] + 14, obj[5] - 2);
			String toEmail = json.substring(obj[5] + 10, obj[6] - 3);
			String fromEmail = json.substring(obj[6] + 12, json.length());
			if (fromEmail.contains("}")) fromEmail = fromEmail.substring(0, fromEmail.length() - 2);
			boolean sent, notification;
			if (sSent.startsWith("t")) sent = true;
			else sent = false;
			if (sNotification.startsWith("t")) notification = true;
			else notification = false;
			msg = new Message(id, note, date, sent, notification, toEmail, fromEmail);
		}
		catch (Exception z) {
			z.printStackTrace();
			System.out.println("\nError in toMessage");
		}
		return msg;
	}
	
	
	
// --------------- To List --------------------------------------------------------------------------------------------	
	
	public static List<User> toUserList(String json) {
		// Doesn't work due to id repeats
		// works if take indexOf first and last elmt?
		// could work just need new algo
		List<User> users = new ArrayList<User>();
		List<Integer> loc = ObjectCounter.Helper(json);
		String obj;
		if (json.length() < 7) {
			return null;
		}
		try {
			int i;
			for (i = 0; loc.size() - 1 > i; i++) {
				if (i != 0) {
					loc.set(i, loc.get(i) + 1);
					loc.set(i + 1, loc.get(i + 1) + 3 * i);
					obj = json.substring(loc.get(i), loc.get(i + 1));
				}
				else obj = json.substring(loc.get(i), loc.get(i + 1));
				users.add(toUser(obj));
			}
			if (!loc.isEmpty()) {
				obj = json.substring(loc.get(i) + 1, json.length() - 1);
				users.add(toUser(obj));
			}
		}
		catch (Exception z) {
			System.out.println("\nError in toUserList");
		}
		return users;
	}
	
	
	
	public static List<School> toSchoolList(String json){
		List<School> schools = new ArrayList<School>();
		// System.out.println(json);
		//List<Integer> loc = ObjectCounter.Helper(json);
		if (json.length() < 7) {
			return null;
		}
		try {
			
		}
		catch (Exception z) {
			z.printStackTrace();
			System.out.println("\nError in toSchoolList");
		}
		return schools;
	}
	
	
	
	public static List<Class> toClassList(String json){
		List<Class> classes = new ArrayList<Class>();
		List<Integer> loc = ObjectCounter.Helper(json);
		String obj;
		if (json.length() < 7) {
			return null;
		}
		try {
			int i;
			for (i = 0; loc.size() - 1 > i; i++) {
				if (i != 0) {
					loc.set(i, loc.get(i) + 1);
					loc.set(i + 1, loc.get(i + 1) + 3 * i);
					obj = json.substring(loc.get(i), loc.get(i + 1));
				}
				else obj = json.substring(loc.get(i), loc.get(i + 1));
				classes.add(toClass(obj));
			}
			if (!loc.isEmpty()) {
				obj = json.substring(loc.get(i) + 1, json.length() - 1);
				classes.add(toClass(obj));
			}
		}
		catch (Exception z) {
			z.printStackTrace();
			System.out.println("\nError in toClassList");
		}
		return classes;
	}
	
	
	
	public static List<Assignment> toAssignmentList(String json) {
		List<Assignment> grades = new ArrayList<Assignment>();
		List<Integer> loc = ObjectCounter.Helper(json);
		String obj;
		if (json.length() < 7) {
			return null;
		}
		try {
			int i;
			for (i = 0; loc.size() - 1 > i; i++) {
				if (i != 0) {
					loc.set(i, loc.get(i) + 1);
					loc.set(i + 1, loc.get(i + 1) + 3 * i);
					obj = json.substring(loc.get(i), loc.get(i + 1));
				}
				else obj = json.substring(loc.get(i), loc.get(i + 1));
				grades.add(toAssignment(obj));
			}
			if (!loc.isEmpty()) {
				obj = json.substring(loc.get(i) + 1, json.length() - 1);
				grades.add(toAssignment(obj));
			}
		}
		catch (Exception z) {
			z.printStackTrace();
			System.out.println("\nError in toGradeList");
		}
		return grades;
	}
	
	
	
	public static List<Message> toMessageList(String json) {
		List<Message> msgs = new ArrayList<Message>();
		List<Integer> loc = ObjectCounter.Helper(json);
		String obj;
		if (json.length() < 7) {
			return null;
		}
		try {
			int i;
			for (i = 0; loc.size() - 1 > i; i++) {
				if (i != 0) {
					loc.set(i, loc.get(i) + 1);
					loc.set(i + 1, loc.get(i + 1) + 3 * i);
					obj = json.substring(loc.get(i), loc.get(i + 1));
				}
				else obj = json.substring(loc.get(i), loc.get(i + 1));
				msgs.add(toMessage(obj));
			}
			if (!loc.isEmpty()) {
				obj = json.substring(loc.get(i) + 1, json.length() - 1);
				msgs.add(toMessage(obj));
			}
		}
		catch (Exception z) {
			z.printStackTrace();
			System.out.println("\nError in toMessageList");
		}
		return msgs;
	}
}
