package com.example.demo.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateConverter {

	public static Calendar convert(String date) {
		Calendar cal = Calendar.getInstance();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < date.length(); i++) {
			if (date.charAt(i) == '/') list.add(i);
		}
		Integer month = Integer.parseInt(date.substring(0, list.get(0))); // 2
		Integer day = Integer.parseInt(date.substring(list.get(0) + 1, list.get(1))); // 5
		Integer year = Integer.parseInt(date.substring(list.get(1) + 1)) - 1; // 1
		cal.clear();
		cal.set(year, month, day);
		return cal;
	}
}
