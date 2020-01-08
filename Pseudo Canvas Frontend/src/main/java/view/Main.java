package view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import model.User;
import utility.Format;

public class Main {

	public static boolean exit;
	
	/*
	 * This is the first page our users will see and it gives them the
	 * option to make a new account or sign into an existing one.
	 */
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		exit = false;
		User user = null;
		Scanner scan = new Scanner(System.in);
		Format.Formatter(0, 0);
		System.out.print(Format.actionLine);
		String q1 = scan.nextLine().toLowerCase();
		//String q1 = "sign in";
		if (q1.equals("new account")) {
			user = NewUser.NewUserView();
		}
		else if (q1.equals("sign in") || q1.equals("a")) {
			user = Login.LoginView();
		}
		else {
			System.out.println("Error in Main");
			exit = true;
		}
		while (exit == false && user != null) {
			exit = Dashboard.DashboardView(user);
		}
		System.out.println("Goodbye!");
	}
}
