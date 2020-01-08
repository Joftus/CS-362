package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.User;
import utility.Format;
import utility.GetMapping;
import utility.PostMapping;

public class MessageBoard {

	/*
	 * This is where users can view and send messages to
	 * people they have classes with.
	 */
	
	@SuppressWarnings("resource")
	public static Boolean MessageBoardView(User user) throws InterruptedException {
		Boolean exit = false;
		Boolean back = false;
		while (back == false) {
			try {
				int pageNum = 7, msgCount = 0;
				Format.Formatter(pageNum);
				for (int i = 0; i < user.messages.size(); i ++) {
					if (!user.messages.get(i).notification) {
						System.out.println(user.messages.get(i).toString());
						msgCount++;
					}
				}
				Format.VerSpace(msgCount * 9);
				System.out.print(Format.actionLine);
				Scanner scan = new Scanner(System.in);
				String q1 = scan.nextLine();
				if (q1.equals("exit")) {
					back = true;
					exit = true;
				}
				else if (q1.equals("back")) back = true;
				else if (q1.equals("send message")) {
					System.out.println(Format.newPage);
					System.out.println("Possible Recipiants|");
					System.out.println("-------------------\n");
					String connected = "";
					List<String> emails = new ArrayList<String>();
					for (int j = 0; j < user.classes.size(); j++) {
						connected = connected + GetMapping.Helper("/15/" + user.classes.get(j).id);
					}
					int count = 0;
					if (connected.length() != 0) connected = connected.substring(1);
					while (connected.contains("|")) {
						emails.add(connected.substring(0, connected.indexOf("|")));
						connected = connected.substring(connected.indexOf("|") + 1);
					}
					if (connected.length() != 0) emails.add(connected);
					for (int k = 0; k < emails.size(); k++) {
						if (!emails.get(k).equals(user.email)) {
							count++;
							System.out.println(count + ". " + emails.get(k));
						}
					}
					Format.VerSpace(count - 3);
					System.out.print("send to: ");
					String q2 = scan.nextLine();
					System.out.print("message: ");
					String q3 = scan.nextLine();
					q3 = q3.replaceAll(" ", "_");
					PostMapping.Helper("/25/" + q2 + "/" + user.email + "/" + q3);
				}
			}
			catch (Exception e) {
				System.out.println("Error in MessageBoard.MessageBoardView");
				Thread.sleep(5000);
			}
		}
		return exit;
	}
}
