package view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import model.User;
import utility.Format;

public class Dashboard {

	/*
	 * This is the main loop of the application as the users are able
	 * to navigate to different modules of the platform
	 */
	
	@SuppressWarnings("resource")
	public static Boolean DashboardView(User user) throws MalformedURLException, IOException, InterruptedException {
		Boolean exit = false;
		while (exit == false) {
			try {
				int pageNum = 0;
				if (!user.type.equals("admin")) pageNum = 3;
				else pageNum = 14;
				Format.Formatter(1, pageNum, "Welcome " + user.name + ", you are registered as a " + user.type + ".");
				Format.OptionLine(pageNum);
				System.out.print(Format.actionLine);
				Scanner scan = new Scanner(System.in);
				String q1 = scan.nextLine();
				if (q1.equals("assignments")) exit = Assignments.AssignmentView(user);
				else if (q1.equals("class list")) exit = ClassList.ClassListView(user);
				else if (q1.equals("message board")) exit = MessageBoard.MessageBoardView(user);
				else if (q1.equals("notification wall")) exit = NotificationWall.NotificationWallView(user);
				else if (q1.equals("settings")) exit = Settings.SettingView(user);
				else if (q1.equals("groups")) exit = Groups.GroupView(user);
				else if (q1.equals("exit")) return true;
				else if (q1.equals("school info")) exit = SchoolInfo.SchoolInfoView(user);
			}
			catch (Exception e) {
				System.out.println("Error in Dashboard.DashboardView");
				Thread.sleep(5000);
			}
		}
		return false;
	}
}
