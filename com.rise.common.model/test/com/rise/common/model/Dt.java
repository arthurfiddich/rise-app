package com.rise.common.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Dt {
	public static void main(String[] args) throws ParseException {
//		Timestamp dbDateTime = new java.sql.Timestamp(System.currentTimeMillis());
//		System.out.println(dbDateTime.toString());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
//				Locale.US);
//		GregorianCalendar calendar = new GregorianCalendar(
//				TimeZone.getTimeZone("US/Central"));
//		calendar.setTimeInMillis(System.currentTimeMillis());
//		Date x = new java.sql.Date(System.currentTimeMillis());
//		System.out.println(x.toString());
//		System.out.println("GregorianCalendar -"
//				+ sdf.format(calendar.getTime()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
		calendar.setTimeInMillis(System.currentTimeMillis());
		String date =  sdf.format(calendar.getTime());
		Date dateObject = sdf.parse(date);
		System.out.println(dateObject);
	}
}
