package view;

import java.util.List;
import java.util.Scanner;

import model.User;
import utility.Format;
import utility.GetMapping;
import utility.ObjectCreator;
import utility.PostMapping;

public class Groups {

	@SuppressWarnings("resource")
	public static boolean GroupView(User user) throws InterruptedException {
		System.out.println(user.toString());
		boolean back = false;
		int a, b;
		while (back == false) {
			try {
				int pageNum = 0;
				if (user.type.equals("student")) pageNum = 10;
				else pageNum = 11;
				Format.Formatter(0, pageNum);
				Format.OptionLine(pageNum);
				System.out.print(Format.actionLine);
				Scanner scan = new Scanner(System.in);
				String q1 = scan.nextLine();
				if (q1.equals("back")) {
					back = true;
				}
				if (q1.equals("exit")) {
					return true;
				}
				if (q1.equals("create channel")) {
					System.out.println("New channel: " + PostMapping.Helper("/61/" + user.email));
				}
				else if(q1.equals("view channel")) {
					System.out.println(user.cGroup);
				}
				else if(q1.equals("msg in channel")) {
					System.out.print("msg: ");
					String q2 = scan.nextLine();
					q2.replaceAll(" ", "_");
					PostMapping.Helper("/63/" + user.email + "/" + q2);
				}
				else if(q1.equals("assignment group")) {
					System.out.print("Current Grading Group: ");
					if (user.gGroup == 0) System.out.println("None");
					else { 
						List<User> group = ObjectCreator.toUserList(GetMapping.Helper("/71/" + user.gGroup));
						for (a = 0; a < group.size(); a++) {
							if (!group.get(1).email.equals(user.email)) System.out.println(group.get(1).toString());
						}
					}
					System.out.print("Create grading group (y/n): ");
					String q2 = scan.nextLine();
					if (q2.equals("y")) {
						System.out.print("Teacher Email: ");
						String email = scan.nextLine();
						for (b = 0; b < user.assignments.size(); b++) {
							if (user.assignments.get(b).grade == 0) System.out.println(user.assignments.get(b).toString());
						}
						System.out.print("Assignment Name: ");
						String assName = scan.nextLine();
						// find class then print all students in that class
						// after add more random students to mainController
						// make sure no where else using /15
						System.out.print("Partner 1: ");
						String part1 = scan.nextLine();
						System.out.print("Partner 2: ");
						String part2 = scan.nextLine();
						String msg = user.email + ", " + part1 + " and " + part2 + " want to work together on " + assName;
						msg = msg.replaceAll(" ", "_");
						PostMapping.Helper("/25/" + email + "/" + user.email + "/" + msg);
					}
				}
				else if(q1.equals("create assignment group")) {
					System.out.print("1st email: ");
					String emailA = scan.nextLine();
					System.out.print("2nd email: ");
					String emailB = scan.nextLine();
					System.out.print("3rd email: ");
					String emailC = scan.nextLine();
					PostMapping.Helper("/64/" + emailA + "/" + emailB + "/" + emailC);
				}
				else if(q1.equals("join channel")) {
					System.out.print("channel number: ");
					Integer q2 = scan.nextInt();
					PostMapping.Helper("/62/" + user.email + "/" + q2);
					user.cGroup = q2;
				}
				else if (q1.equals("post grade for group")) {
					System.out.print("group number: ");
					Integer groupNum = scan.nextInt();
					System.out.print("assignment name: ");
					String assignName = scan.nextLine();
					System.out.print("grade: ");
					Integer grade = scan.nextInt();
					PostMapping.Helper("/60/" + assignName + "/" + groupNum + "/" + grade);
				}
			}
			catch (Exception e) {
				System.out.println("Error in Groups.GroupView");
				Thread.sleep(5000);
			}
		}
		return false;
	}
}
