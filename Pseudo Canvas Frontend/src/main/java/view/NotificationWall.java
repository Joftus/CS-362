package view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.User;
import utility.Format;
import utility.GetMapping;
import utility.PostMapping;

public class NotificationWall {

	/*
	 * This page shows the updates to a users
	 * account that were made by other users such as grade changes
	 */
	
	@SuppressWarnings("resource")
	public static Boolean NotificationWallView(User user) throws MalformedURLException, IOException, InterruptedException {
		Boolean exit = false;
		Boolean back = false;
		while (back == false) {
			try {
				int msgs = 0;
				int pageNum = 0;
				if (user.type.equals("student")) pageNum = 8;
				else pageNum = 13;
				Format.Formatter(pageNum);
				for (int i = 0; i < user.messages.size(); i ++) {
					if (user.messages.get(i).notification) {
						System.out.println(user.messages.get(i).toString());
						msgs++;
					}
				}
				Scanner scan = new Scanner(System.in);
				Format.VerSpace(msgs * 9);
				Format.OptionLine(pageNum);
				System.out.print(Format.actionLine);
				String q1 = scan.nextLine();
				if (q1.equals("exit")) {
					back = true;
					exit = true;
				}
				else if (q1.equals("back")) back = true;
				else if (q1.equals("send to class")) {
					System.out.print("Class Name: ");
					String c = scan.nextLine();
					System.out.print("Message: ");
					String msg = scan.nextLine();
					msg = msg.replaceAll(" ", "_");
					String out = GetMapping.Helper("/7/" + c);
					List<String> emails = new ArrayList<String>();
					if (out.length() > 2) out = out.substring(2);
					while (out.contains(",")) {
						emails.add(out.substring(0, out.indexOf(",") - 1));
						out = out.substring(out.indexOf(",") + 2);
					}
					if (out.length() > 0) emails.add(out.substring(0, out.length() - 2));
					for (int j = 0; j < emails.size(); j++) {
						if (!emails.get(j).equals(user.email)) {
							PostMapping.Helper("/66/" + emails.get(j) + "/" + user.email + "/" + msg);
						}
					}
					System.out.println("Sent: " + msg + " to all students in " + c + " class.");
					Thread.sleep(5000);
				}
			}
			catch (Exception e) {
				System.out.println("Error in NotificationWall.NotificationWallView " + e.getMessage());
				Thread.sleep(5000);
			}
		}
		return exit;
	}
}
