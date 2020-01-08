package utility;

public class Format {

	public static final String newPage = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
	public static final String actionLine = "Action: ";
	// public static final int pageSizeVert = 46; Without option line by action line
	public static final int pageSizeVert = 45;
	public static final int pageSizeHor = 209;
	
	// Old server info
	//spring.datasource.url=jdbc:mysql://cs309-ad-2.misc.iastate.edu:3306/demo?verifyServerCertificate=false&useSSL=false&requireSSL=false
	//spring.datasource.username=team_acct
	//spring.datasource.password=Jz2778lp!
	
	// Format.Formatter();
	// System.out.print(Format.actionLine);
	
	public final static String[] pageOptions = {
			"| sign in | new account |", 
			"| enter info | forgot email | forgot password |", 
			"| new user | exit |", 
			"| assignments | class list | message board | notification wall | groups | settings | exit |", 
			"| assignments | back | exit |",
			"| assignments | change a grade | new assignment | stats | curve | back | exit |",
			"| classes | gradebook | back | exit |",
			"| messages | send message | back | exit |",
			"| notifications | back | exit |",
			"| setting number | back | exit |",
			"| create channel | view channel | join channel | msg in channel | assignment group | back | exit |",
			"| create channel | view channel | join channel | msg in channel | create assignment group | post grade for group | back | exit |",
			"| assignment name | general |",
			"| notifications | send to class | back | exit |",
			"| assignments | class list | message board | notification wall | groups | settings | school info | exit |"
			};
	
	/* 
	 * 0. main
	 * 1. login
	 * 2. newUser
	 * 3. dashboard
	 * 4. assignments student
	 * 5. assignments admin
	 * 6. class list
	 * 7. message board
	 * 8. notification wall
	 * 9. Settings
	 * 10. groups student
	 * 11. groups admin
	 * 12. attendance stats
	 * 13. teacher notification wall
	 * 14. admin dashboard
	 */
	
	public final static String[] pageHeaders = {
			"| Main |",
			"| Login |",
			"| New User |",
			"| Dashboard |",
			"| Assignment |",
			"| Assignment |",
			"| Class List |",
			"| Message Board |",
			"| Notification Wall |",
			"| Settings |",
			"| Groups |",
			"| Groups |",
			"| Stats |",
			"| Notification Wall |",
			"| Dashboard |",
	};
	
	
	
	public static void Formatter(int used, int page) {
		System.out.println(newPage);
		ViewHeader(page);
		ViewOptions(page);
		VerSpace(used);
	}
	

	
	public static void Formatter(int page) {
		System.out.println(newPage);
		ViewHeader(page);
		ViewOptions(page);
	}
	
	
	
	public static void Formatter(int used, int page, String input) {
		System.out.println(newPage);
		ViewHeader(page, input);
		ViewOptions(page);
		VerSpace(used);
	}
	
	
	
	public static void VerSpace(int used) {
		int spaces = pageSizeVert - used;
		for (int i = 0; i < spaces; i++) {
			System.out.println("");
		}
	}
	
	
	
	public static String HorSpace(int used) {
		int num = (pageSizeHor - used) / 2;
		String spaces = "";
		for (int i = 0; i < num; i++) {
			spaces += " ";
		}
		return spaces;
	}
	
	
	
	public static String Line(int len) {
		String out = "";
		for (int i = 0; i < len; i++) {
			out += "-";
		}
		return out;
	}
	
	
	
	public static void ViewHeader(int page) { 
		System.out.println("Pseudo-Canvas");
		System.out.println("Version: 0.0.2");
		String line = Line((pageSizeHor - pageHeaders[page].length()) / 2);
		System.out.println(line + pageHeaders[page] + line);
	}
	
	
	
	public static void ViewHeader(int page, String input) { 
		System.out.println("Pseudo-Canvas");
		System.out.println("Version: 0.0.2");
		String line = Line((pageSizeHor - pageHeaders[page].length()) / 2);
		System.out.println(line + pageHeaders[page] + line);
		line = HorSpace(input.length());
		line = line.replaceAll("-", " ");
		System.out.println(line + input + line);
	}
	
	
	
	public static void ViewOptions(int page) {
		String spaces = HorSpace(pageOptions[page].length());
		System.out.println("\n" + spaces + pageOptions[page]);
	}
	
	
	
	public static void OptionLine(int page) {
		String original = pageOptions[page];
		int num = 1;
		String a = "", b = "";
		while (original.contains("|")) {
			a = original.substring(0, original.indexOf('|'));
			b = original.substring(original.indexOf('|') + 1, original.length());
			original = a + "(" + num + ")" + b;
			num++;
		}
		if (original.length() > 4) System.out.println(original.substring(0, original.length() - 3));
	}
}
