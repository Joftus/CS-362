package view;

import java.util.Scanner;

import model.User;
import utility.Format;
import utility.ObjectCreator;
import utility.PostMapping;

public class NewUser {

	/*
	 * If a user does not have an existing account they
	 * would be directed here to enter their information
	 */
	
	@SuppressWarnings("resource")
	public static User NewUserView() throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		String email = "", password = "", name = "", type = "", school = "", json = "";
		Format.Formatter(0, 2);
		System.out.print(Format.actionLine);
		String q1 = scan.nextLine();
		if (q1.equals("new user")) {
			System.out.print("Email: ");
			email = scan.nextLine();
			System.out.print("Password: ");
			password = scan.nextLine();
			System.out.print("Name: ");
			name = scan.nextLine();
			System.out.print("Type of User: ");
			type = scan.nextLine();
			System.out.print("School: ");
			school = scan.nextLine();
			try {
				json = PostMapping.Helper("/54/" + email + "/" + password + "/" + name + "/" + type + "/" + school);
				return ObjectCreator.toUser(json);
			}
			catch (Exception e) {
				System.out.println("Error In NewUser.NewUserView");
				Thread.sleep(5000);
				return NewUserView();
			}
		}
		else if (q1.equals("exit")) {
			Main.exit = true;
		}
		return null;
	}
}
