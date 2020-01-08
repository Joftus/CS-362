package view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Assignment;
import model.User;
import utility.BellDistribution;
import utility.Format;
import utility.GetMapping;
import utility.GradeDisplay;
import utility.ObjectCreator;
import utility.PostMapping;

public class Assignments {

	/*
	 * Class is for users to view or edit their assignments depending on their type
	 */
	
	@SuppressWarnings("resource")
	public static Boolean AssignmentView(User user) throws MalformedURLException, IOException, InterruptedException {
		Boolean back = false;
		String q1 = "";
		while (back == false) {
			try {
				List<Assignment> assignments = new ArrayList<Assignment>();
				if (user.type.equals("teacher")) {
					for (int m = 0; m < user.classes.size(); m ++) {
						assignments.addAll(ObjectCreator.toAssignmentList(GetMapping.Helper("/56/" + user.classes.get(m).name)));
					}
				}
				else for (int n = 0; n < user.assignments.size(); n++) if (user.assignments.get(n).viewable) assignments.add(user.assignments.get(n));
				int pageNum;
				if (user.type.equals("student"))	pageNum = 4;
				else pageNum = 5;
				Scanner scan = new Scanner(System.in);
				Format.Formatter(pageNum);
				for (int q = 0; q < assignments.size(); q++) {
					System.out.println(assignments.get(q).toString());
				}
				Format.VerSpace(assignments.size() * 8);
				Format.OptionLine(pageNum);
				System.out.print(Format.actionLine);
				q1 = scan.nextLine();
				if (q1.equals("exit")) {
					back = true;
					Main.exit = true;
				}
				else if (q1.equals("back")) back = true;
				else if (q1.equals("change a grade")) {
					System.out.print("assignment name: ");
					String q2 = scan.nextLine();
					System.out.print("student: ");
					String q3 = scan.nextLine();
					Assignment change = null;
					for (int n = 0; n < assignments.size(); n++) {
						if (assignments.get(n).assignment.equals(q2) && assignments.get(n).userEmail.equals(q3)) {
							change = assignments.get(n);
						}
					}
					if (change != null) {
						System.out.print("new score: ");
						Integer q4 = scan.nextInt();
						user = ObjectCreator.toUser(PostMapping.Helper("/55/" + q3  + "/" + user.email  + "/" + q2  + "/" + change.className  + "/" + q4));
					}
					else {
						System.out.println("No changes were made...");
						Thread.sleep(5000);
					}
				}
				else if (q1.equals("new assignment")) {
					System.out.print("class name: ");
					String className = scan.nextLine();
					System.out.print("assignment: ");
					String ass = scan.nextLine();
					PostMapping.Helper("/57/" + className + "/" + ass);
				}
				else if (q1.equals("stats")) {
					System.out.print("class name: ");
					String q2 = scan.nextLine();
					Format.Formatter(0, 12);
					System.out.print(Format.actionLine);
					String q3 = scan.nextLine();
					if (q3.equals("general")) {
						List<Assignment> classGrades = ObjectCreator.toAssignmentList(GetMapping.Helper("/58/" + q2));
						GradeDisplay.generalChart(classGrades);
						Thread.sleep(10000);
						BellDistribution.bellDist(classGrades);
						Thread.sleep(10000);
					}
					else {
						List<Assignment> classGrades = ObjectCreator.toAssignmentList(GetMapping.Helper("/59/" + q2 + "/" + q3));
						GradeDisplay.singleChart(classGrades);
						Thread.sleep(10000);
					}
				}
				else if (q1.equals("curve")) {
					System.out.print("class name: ");
					String q2 = scan.nextLine();
					System.out.print("assignment name: ");
					String q3 = scan.nextLine();
					List<Assignment> classGrades = ObjectCreator.toAssignmentList(GetMapping.Helper("/58/" + q2));
					System.out.println(Format.newPage);
					BellDistribution.bellDist(classGrades);
					System.out.print("percent curve: ");
					Integer q4 = scan.nextInt();
					PostMapping.Helper("/65/" + q2 + "/" + q3 + "/" + q4);
					Thread.sleep(5000);
					//GradeDisplay.generalChart(classGrades);
				}
			}
			catch (Exception e) {
				System.out.println("Error in Assignments.AssignmentView");
				Thread.sleep(5000);
			}
		}
		return Main.exit;
	}
}
