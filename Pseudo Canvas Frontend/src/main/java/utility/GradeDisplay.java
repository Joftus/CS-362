package utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import model.Assignment;

public class GradeDisplay {

	public static void singleChart(List<Assignment> list) {
		int del = 10;
		Integer[] avg = new Integer[list.size()];
		String[][] grid = new String[del][list.size()];
		int grd;
		for (int i = 0; i < list.size(); i++) {
			Assignment tmp = list.get(i);
			if (tmp.grade == 100) grd = 9;
			else grd = tmp.grade / del;
			avg[i] = tmp.grade;
			grid[grd][i] = ".";
		}
		System.out.print("    ");
		for (int j = 0; j < list.size() + 7; j++) {
			System.out.print("_");
		}
		System.out.println();
		System.out.println(100 + "|");
		for (int y = del - 1; y > 0; y--) {
			System.out.print((y * del) + " |");
			for (int x = 0; x < list.size(); x++) {
				if (grid[y][x] == ".") System.out.print(grid[y][x]);
				else System.out.print(" ");
			}
			System.out.print("\n");
		}
		System.out.print(0 + "  |");
		for (int j = 0; j < list.size() + 7; j++) {
			System.out.print("_");
		}
		System.out.println("\nmean: " + mean(avg));
		System.out.println("median: " + median(avg));
		System.out.println("mode: " + mode(avg));
	}
	
	
	
	public static void generalChart(List<Assignment> list) {
		try {
			for (int i = 0; i < list.size(); i++) if (list.get(i).grade <= 0 || list.get(i).equals(null)) list.remove(i);
			if (list.isEmpty()) System.out.println("No Assignments! GradeDisplay.generalChart");
			List<Integer> totals = new ArrayList<Integer>();
			List<String> users = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				if (!users.contains(list.get(i).userEmail)) {
					users.add(list.get(i).userEmail);
					totals.add(list.get(i).grade);
				}
				else {
					int index = users.indexOf(list.get(i).userEmail);
					totals.set(index, totals.get(index) + list.get(i).grade);
				}
			}
			List<Integer> percents = new ArrayList<Integer>();
			int tac = list.size() / users.size();
			for (int j = 0; j < totals.size(); j++) {
				percents.add(totals.get(j) / tac);
			}
			// here b4
			int del = 10;
			Integer[] avg = new Integer[percents.size()];
			String[][] grid = new String[del][list.size()];
			int grd;
			for (int i = 0; i < list.size() && i < percents.size(); i++) {
				if (percents.get(i) == 100) grd = 9;
				else grd = percents.get(i) / del;
				avg[i] = percents.get(i);
				if (grd > 9 || grd < 0) grd = 9;
				grid[grd][i] = ".";
			}
			System.out.print("    ");
			for (int j = 0; j < list.size() + 7; j++) {
				System.out.print("_");
			}
			System.out.println(Format.newPage);
			System.out.println(100 + "|");
			for (int y = del - 1; y > 0; y--) {
				System.out.print((y * del) + " |");
				for (int x = 0; x < list.size(); x++) {
					if (grid[y][x] == ".") System.out.print(".");
					else System.out.print(" ");
				}
				System.out.print("\n");
			}
			System.out.print(0 + "  |");
			for (int j = 0; j < list.size() + 7; j++) {
				System.out.print("_");
			}
			System.out.println("\n\n\nmean: " + mean(avg));
			System.out.println("median: " + median(avg));
			System.out.println("mode: " + mode(avg));
			System.out.println("\n\n\n\n\n");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error in GradeDisplay.GeneralChart");
		}
	}
	
	
	
	
	public static float mean(Integer[] grades) {
		int total = 0;
		for (int i = 0; i < grades.length; i++) {
			total += grades[i];
		} 
		return total / grades.length;
	}
	
	
	
	public static Integer median(Integer[] grades) {
		Integer median = 0;
		Arrays.sort(grades);
		if (grades.length % 2 == 0)
		    median = (grades[grades.length/2] + grades[grades.length/2 - 1])/2;
		else
		    median = grades[grades.length/2];
		return median;
	}
	
	
	
	public static Integer mode(Integer[] grades) {
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
	    int max  = 1;
	    int mode = 0;
	    if (grades.length == 1) {
	    		return grades[0];
	    }
	    for(int i = 0; i < grades.length; i++) {
	        if (hm.get(grades[i]) != null) {
	            int count = hm.get(grades[i]);
	            count++;
	            hm.put(grades[i], count);
	            if(count > max) {
	                max  = count;
	                mode = grades[i];
	            }
	        }
	        else 
	            hm.put(grades[i],1);
	    }
	    return mode;
	}
	
	public static int grid(List<Assignment> gradebook) {
		List<String> users = new ArrayList<String>();
		List<String> assNames = new ArrayList<String>();
		List<Truple> link = new ArrayList<Truple>();
		int longestEmail = 0, longestAss = 0;
		int a, b, c, d, e, f, g, h, i, j;
		String eSpace = "", aSpace = "", nSpace = "", space = "";
		System.out.println("");
		for (a = 0; a < gradebook.size(); a++) {
			Assignment ass = gradebook.get(a);
			if (!users.contains(ass.userEmail)) {
				if (ass.userEmail.length() > longestEmail) longestEmail = ass.userEmail.length();
				users.add(ass.userEmail);
			}
			if (!assNames.contains(ass.assignment)) {
				if (ass.assignment.length() > longestAss) longestAss = ass.assignment.length();
				assNames.add(ass.assignment);
			}
			link.add(new Truple(ass.grade, ass.userEmail, ass.assignment));
		}
		longestEmail++;
		for (f = 0; f < longestEmail; f++) eSpace = eSpace + " ";
		for (g = 0; g < longestAss; g++) aSpace = aSpace + " ";
		longestAss += 3;
		String assignmentString = "";
		for (e = 0; e < assNames.size(); e++) {
			for (h = 0; h < ((longestAss - assNames.get(e).length()) / 2); h++) space = space + " ";
			if (e == 0) assignmentString = eSpace + "|" + space + assNames.get(e) + space + "|";
			else if ((assNames.get(e).length() % 2) == 1) assignmentString = assignmentString + space + assNames.get(e) + space + " |";
			else assignmentString = assignmentString + space + assNames.get(e) + space + "|";
			space = "";
		}
		System.out.println(assignmentString);
		for (b = 0; b < users.size(); b++) {
			System.out.println(Format.Line(assignmentString.length()));
			for (j = 0; j < longestEmail - users.get(b).length(); j++) nSpace = nSpace + " ";
			System.out.print(users.get(b) + nSpace + "|");
			for (d = 0; d < assNames.size(); d++) {
				for (c = 0; c < link.size(); c++) {
					Truple chain = link.get(c);
					if (chain.name.equals(users.get(b)) && chain.assignmentName.equals(assNames.get(d))) {
						if ((chain.grade + "").length() == 1) {
							for (i = 0; i < ((longestAss - (chain.grade + "").length() - 1) / 2); i++) space = space + " ";
							System.out.print(space + chain.grade + space + " |");
						}
						else if ((chain.grade + "").length() == 3) {
							for (i = 0; i < ((longestAss - (chain.grade + "").length() - 1) / 2); i++) space = space + " ";
							System.out.print(space + " " + chain.grade + space + "|");
						}
						else {
							for (i = 0; i < ((longestAss - (chain.grade + "").length()) / 2); i++) space = space + " ";
							System.out.print(space + chain.grade + space + "|");
						}
					}
					space = "";
				}
			}
			nSpace = "";
			System.out.println("");
		}
		return users.size();
	}
}
