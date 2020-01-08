package view;

import java.util.Scanner;

import model.User;
import utility.Format;
import utility.GetMapping;
import utility.ObjectCreator;

public class Login {

	/*
	 * A page for the returning users to sign back into their account,
	 * but only after we verify their information is correct. 
	 */
	
	@SuppressWarnings("resource")
	public static User LoginView() throws InterruptedException {
		boolean done = false;
		String json = null;
		Scanner scan = new Scanner(System.in);
		String email = "", password = "";
		//String email = "yuan@iastate.edu", password = "a";
		//String email = "noah@iastate.edu", password = "a";
		//String email = "tyler@iastate.edu", password = "a";
		while (done == false) {
			try {
				Format.Formatter(0, 1);
				System.out.print(Format.actionLine);
				String q1 = scan.nextLine();
				if (q1.equals("enter info")) {
					System.out.print("email: ");
					email = scan.nextLine();
					System.out.print("password: ");
					password = scan.nextLine();
				}
				else if (q1.equals("forgot email")) {
					System.out.println("Well that sucks dude...");
				}
				else if (q1.equals("forgot password")) {
					System.out.println("Well that sucks dude...");
				}
				json = GetMapping.Helper("/17/" + email + "/" + password);
				done = true;
			}
			catch (Exception e) {
				System.out.println("Error In Login.LoginView");
				Thread.sleep(5000);
			}
		}
		if (json.length() == 0) {
			System.out.println("Invalid email or password!");
			return null;
		}
		return ObjectCreator.toUser(json);
	}
}
