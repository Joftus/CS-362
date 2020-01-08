package view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import model.User;
import utility.Format;
import utility.GetMapping;

public class SchoolInfo {

	@SuppressWarnings("resource")
	public static boolean SchoolInfoView(User user) throws MalformedURLException, IOException, InterruptedException {
		boolean back = false;
		while (back == false) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println(Format.newPage);
				System.out.println("School: " + user.school.name);
				String studentEmails = GetMapping.Helper("/67/" + user.school.name);
				String teacherEmails = GetMapping.Helper("/68/" + user.school.name);
				String taEmails = GetMapping.Helper("/69/" + user.school.name);
				taEmails = taEmails.replace("\"", "");
				if (studentEmails.length() > 5) studentEmails = studentEmails.substring(2, studentEmails.length() - 2);
				else studentEmails = "n/a";
				System.out.println("Students: " + studentEmails);
				if (teacherEmails.length() > 5) teacherEmails = teacherEmails.substring(2, teacherEmails.length() - 2);
				else teacherEmails = "n/a";
				System.out.println("Teachers: " + teacherEmails);
				if (taEmails.length() > 5) taEmails = taEmails.substring(2, taEmails.length() - 2);
				else taEmails = "n/a";
				System.out.println("Teacher Assistants: " + taEmails);
				System.out.print("\n\n\n\n\nback or exit: ");
				String q1 = scan.nextLine();
				if (q1.equals("back")) {
					back = true;
				}
				else if (q1.equals("exit")) {
					scan.close();
					return true;
				}
			}
			catch (Exception e) {
				System.out.println("Error in SchoolInfo.SchoolInfoView");
				Thread.sleep(5000);
			}
		}
		return false;
	}
}
