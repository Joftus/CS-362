package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Class;
import model.User;
import utility.Format;
import utility.GetMapping;
import utility.ObjectCreator;
import utility.PostMapping;

public class Settings {

	/*
	 * Here users can edit their information and if the users
	 * type = admin, they are able to create classes, schools and users. 
	 */
	
	@SuppressWarnings("resource")
	public static Boolean SettingView(User user) throws InterruptedException {
		Boolean exit = false;
		Boolean back = false;
		while (back == false) {
			try {
				int pageNum = 9;
				Format.Formatter(pageNum);
				Scanner scan = new Scanner(System.in);
				System.out.println("1. private:        " + user.pri);
				System.out.println("2. email:          " + user.email);
				System.out.println("3. name:           " + user.name);
				System.out.println("4. password:       " + user.password);
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < user.classes.size(); i++) {
					list.add(user.classes.get(i).name);
				}
				System.out.println("5. add class       ");
				System.out.println("6. remove class    ");
				if (user.type.equals("admin")) {
					System.out.println("7. create class    ");
					System.out.println("8. create school   ");
					System.out.println("9. create user     ");
					System.out.println("10. create section ");
				}
				if (user.type.equals("admin")) Format.VerSpace(10);
				else Format.VerSpace(6);
				Format.OptionLine(pageNum);
				System.out.print(Format.actionLine);
				String q2 = scan.nextLine();
				if (q2.equals("exit")) {
					back = true;
					exit = true;
				}
				else if (q2.equals("back")) back = true;
				else if(q2.equals("1")) {
					if (user.pri == false) {
						user.pri = true;
					}
					else {
						user.pri = false;
					}
				}
				else if (q2.equals("2")) {
					System.out.println("old email: " + user.email);
					System.out.print("new email: ");
					user.email = scan.nextLine();
				}
				else if (q2.equals("3")) {
					System.out.println("old name: " + user.name);
					System.out.print("new name: ");
					user.name = scan.nextLine();
				}
				else if (q2.equals("4")) {
					System.out.println("old password: " + user.password);
					System.out.print("new email: ");
					user.password = scan.nextLine();
				}
				else if (q2.equals("5")) {
					System.out.println(Format.newPage);
					String line = Format.Line((Format.pageSizeHor - "possible classes".length()) / 2);
					System.out.println(line + "possible classes" + line);
					List<String> clist = new ArrayList<String>();
					List<Class> blist = new ArrayList<Class>();
					try {
						blist = ObjectCreator.toClassList(GetMapping.Helper("/32"));
						for (int j = 0; j < blist.size(); j++) {
							clist.add(blist.get(j).name);
						}
					}
					catch (Exception e) {
						System.out.println("Error in Settings.SettingView");
					}
					for (int o = 0; o < clist.size(); o++) {
						System.out.println(o + ". " + clist.get(o));
					}
					System.out.println("\nAdd class by number");
					System.out.print("Action: ");
					String q3 = scan.nextLine();
					for (int k = 0; k < clist.size(); k++) {
						if (clist.get(k).equals(q3)) user = ObjectCreator.toUser(PostMapping.Helper("/23/" + user.email + "/" + q3));
					}
				}
				else if (q2.equals("6")) {
					System.out.println("\n\n\n\n\n");
					boolean empty = false;
					for (int m = 0; m < user.classes.size(); m++) {
						if (user.classes.get(0).name.equals("blank") && user.classes.size() == 1) {
							System.out.println("No classes to remove!");
							empty = true;
						}
						else System.out.println((m + 1) + ". " + user.classes.get(m).name);
					}
					if (empty == false) {
						System.out.print("Name: ");
						String q4 = scan.nextLine();
						for (int l = 0; l < user.classes.size(); l++) {
							if (user.classes.get(l).name.equals(q4)) user = ObjectCreator.toUser(PostMapping.Helper("/24/" + user.email + "/" + q4));
						}
					}
				}
				else if (q2.equals("7")) {
					System.out.println("Create a new class");
					System.out.print("Class name: ");
					String q5 = scan.nextLine();
					PostMapping.Helper("/35/" + q5);
				}
				else if (q2.equals("8")) {
					System.out.println("Create a new school");
					System.out.print("School name: ");
					String q5 = scan.nextLine();
					PostMapping.Helper("/43 /" + q5);
				}
				else if (q2.equals("9")) {
					System.out.println("Create a new user");
					System.out.print("User email: ");
					String q5 = scan.nextLine();
					System.out.print("User password: ");
					String q6 = scan.nextLine();
					System.out.print("User type: ");
					String q7 = scan.nextLine();
					PostMapping.Helper("/50/" + q5 + "/" + q6 + "/" + q7);
				}
				else if (q2.equals("10")) {
					System.out.println("New class section");
					System.out.println("Class name: ");
					String q3 = scan.nextLine();
					PostMapping.Helper("/9/" + q3);
				}
				PostMapping.Helper("/update/user/" + user.id + "/" + user.email + "/" + user.password + "/" + user.name + "/" + user.type + "/" + user.pri);
			}
			catch (Exception e) {
				System.out.println("Error in Settings.SettingsView");
				Thread.sleep(5000);
			}
		}
		return exit;
	}
}
