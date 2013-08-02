package com.rise.common.util.converters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConverter implements Converter<CustomDate, Long> {

	private final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

//	@Override
	public CustomDate convert(Long argToken) {
		if (argToken != null && argToken.longValue() != -1) {
			Date date = new java.sql.Date(argToken.longValue());
			return (CustomDate) date;
		}
		return null;
	}

	protected String getDate(long milliSeconds, String dateFormat) {
		// Create a DateFormatter object for displaying date in specified
		// format.
		DateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in
		// milliseconds to date.
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		System.out.println(new java.sql.Date(System.currentTimeMillis()));
		return formatter.format(calendar.getTime());
	}

	@Override
	public CustomDate convert(Long argToken, Long argFieldToken) {
		// TODO Auto-generated method stub
		return null;
	}
}
