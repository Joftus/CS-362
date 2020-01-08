package view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import model.User;
import utility.Format;
import utility.GetMapping;
import utility.GradeDisplay;
import utility.ObjectCreator;

public class ClassList {

	/*
	 * Used to view all of the classes this user is enrolled in
	 */
	
	@SuppressWarnings("resource")
	public static Boolean ClassListView(User user) throws MalformedURLException, IOException, InterruptedException {
		Boolean exit = false;
		Boolean back = false;
		int pageNum = 6;
		while (back == false) {
			try {
				Format.Formatter(pageNum);
				for (int i = 0; i < user.classes.size(); i ++) {
					System.out.println(user.classes.get(i).toString());
				}
				Format.VerSpace(user.classes.size() * 8);
				Format.OptionLine(pageNum);
				System.out.print(Format.actionLine);
				Scanner scan = new Scanner(System.in);
				String q1 = scan.nextLine();
				if (q1.equals("exit")) {
					back = true;
					exit = true;
				}
				else if (q1.equals("back")) back = true;
				else if (q1.equals("gradebook")) {
					System.out.print("Class Name: ");
					String q2 = scan.nextLine();
					System.out.println(Format.newPage);
					int classSize = GradeDisplay.grid(ObjectCreator.toAssignmentList(GetMapping.Helper("/58/" + q2)));
					for (int i = Format.pageSizeVert - (classSize * 2); i > 0; i--) {
						System.out.println("");
					}
					Thread.sleep(10000);
				}
			}
			catch (Exception e) {
				System.out.println("Error in ClassList.ClassListView");
				Thread.sleep(5000);
			}
		}
		return exit;
	}
}
