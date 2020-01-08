package utility;

import java.util.ArrayList;
import java.util.List;

public class ObjectCounter {
	
	/*
	 * This class counts the number of objects in a String
	 */
	
	public static List<Integer> Helper(String json) {
		List<Integer> list = new ArrayList<Integer>();
		int count = 0;
		String part1, part2;
		if (json.length() < 5) {
			list.add(0);
			return list;
		}
		if (json.substring(4).contains("id")) {
			while (json.contains("id")) {
				int loc = json.indexOf("id");
				if (count == 0) {
					list.add(json.indexOf("id") - 2);
					part2 = json.substring(loc + 1);
				}
				else list.add(json.indexOf("id"));
				part1 = json.substring(0, loc - 2);
				part2 = json.substring(loc + 1);
				if (count > 0) {
					json = json.substring(1);
				}
				json = part1 + part2;
				count++;
			}
		}
		else list.add(0);
		return list;
	}
}
