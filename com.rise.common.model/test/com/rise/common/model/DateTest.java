package com.rise.common.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTest {
	// public static void main(String[] args) throws ParseException {
	// String start_dt = "2013-07-05 18:17:48";
	// DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date date = (Date) formatter.parse(start_dt);
	// System.out.println(new Date(System.currentTimeMillis()));
	// System.out.println(formatter.format(date));
	// System.out.println(date);
	// }

	/**
	 * Main Method
	 */
	public static void main(String[] args) {
		System.out.println(getDate(82233213123L, "dd-MM-yyyy hh:mm:ss.SSS"));
	}

	/**
	 * Return date in specified format.
	 * 
	 * @param milliSeconds
	 *            Date in milliseconds
	 * @param dateFormat
	 *            Date format
	 * @return String representing date in specified format
	 */
	public static String getDate(long milliSeconds, String dateFormat) {
		// Create a DateFormatter object for displaying date in specified
		// format.
		DateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in
		// milliseconds to date.
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		System.out.println(calendar.getTime());
		return formatter.format(calendar.getTime());
	}
}
