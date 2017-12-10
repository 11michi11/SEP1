package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate {
	private int day, month, year, hour, minute;

	public MyDate() {
		GregorianCalendar currentDate = new GregorianCalendar();
		day = currentDate.get(GregorianCalendar.DATE);
		month = currentDate.get(GregorianCalendar.MONTH) + 1;
		year = currentDate.get
		     (GregorianCalendar.YEAR);
		hour = currentDate.get(GregorianCalendar.HOUR);
		minute = currentDate.get(GregorianCalendar.MINUTE);
	}

	public MyDate(int day, int month, int year, int hour, int minute) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
	}

	public MyDate(MyDate object) {
		day = object.day;
		month = object.month;
		year = object.year;
		hour = object.hour;
		minute = object.minute;
	}
	
	public MyDate (String dateString) throws InvalidDateInput {
	   String[] dateArray, timeArray;
	   dateArray = dateString.split("/");
	   day = Integer.parseInt(dateArray[0]);
	   month = Integer.parseInt(dateArray[1]);
	   year = Integer.parseInt(dateArray[2]);
	   timeArray = dateArray[3].split(":");
	   hour = Integer.parseInt(timeArray[0]);
	   minute = Integer.parseInt(timeArray[1]);
	   if (!isValid()) {
         throw new InvalidDateInput ("Invalid date");
      }
	}

	public static int convertToMonthNumber(String monthName) {
		switch (monthName.toLowerCase()) {
		case "january":
			return 1;
		case "february":
			return 2;
		case "march":
			return 3;
		case "april":
			return 4;
		case "may":
			return 5;
		case "june":
			return 6;
		case "july":
			return 7;
		case "august":
			return 8;
		case "september":
			return 9;
		case "october":
			return 10;
		case "november":
			return 11;
		case "december":
			return 12;
		default:
			return -1;
		}
	}

	public static int getCurrentYear() {
		return today().getYear();
	}

	public static MyDate today() {
		GregorianCalendar currentDate = new GregorianCalendar();
		return new MyDate(currentDate.get(GregorianCalendar.DATE), currentDate.get(GregorianCalendar.MONTH) + 1,
				currentDate.get(GregorianCalendar.YEAR), currentDate.get(GregorianCalendar.HOUR), currentDate.get(GregorianCalendar.MINUTE));
	}

	public static MyDate getDefaultDate(){
		return new MyDate(1, 1, 1, 0, 0);
	}
	
	public boolean isValid () {
	   return month > 1 && month < 12 && day>1 && day<DayInMonth() && year>1000; 
	}

	public boolean LeapYear() {
		if (year % 400 == 0)
			return true;
		else if (year % 4 == 0 && year % 100 != 0)
			return true;
		return false;
	}

	public int DayInMonth() {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			return 31;
		else if (month != 2)
			return 30;
		else if (LeapYear())
			return 29;
		else
			return 28;
	}

	public String DayOfWeek() {
		int h;
		if (month > 2)
			h = (day + (13 * (month + 1) / 5) + year % 100 + (year % 100) / 4 + year / 400 + year / 20) % 7;
		else
			h = (day + (13 * (month + 13) / 5) + (year - 1) % 100 + ((year - 1) % 100) / 4 + (year - 1) / 400
					+ (year - 1) / 20) % 7;
		if (h == 0)
			return "Saturday";
		else if (h == 1)
			return "Sunday";
		else if (h == 2)
			return "Monday";
		else if (h == 3)
			return "Tuesday";
		else if (h == 4)
			return "Wednesday";
		else if (h == 5)
			return "Thursday";
		else if (h == 6)
			return "Friday";
		return null;
	}

	public String getMonthName() {
		switch (month) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		}
		return "That's not a month!";
	}

	public void nextDay() {
		if (DayInMonth() == day && month != 12) {
			day = 1;
			month++;
		} else if (DayInMonth() == day && month == 12) {
			day = 1;
			month = 1;
			year++;
		} else
			day++;

	}

	public boolean equals(Object sth) {
		if (!(sth instanceof MyDate))
			return false;
		else {
			MyDate obj = (MyDate) sth;
			if (day == obj.day && month == obj.month && year == obj.year)
				return true;
			else
				return false;
		}
	}

	public MyDate copy() {
		return new MyDate(day, month, year, hour, minute);
	}

	public void nextDays(int days) {
		if (DayInMonth() < day + days) {
			while (days + day > DayInMonth()) {
				days -= DayInMonth();
				if (month < 12)
					month++;
				else {
					month = 1;
					year++;
				}
			}
			day += days;
		} else
			day += days;
	}

	public boolean isBefore(MyDate obj) {
		if (year < obj.year)
			return true;
		else if (year > obj.year)
			return false;
		else {
			if (month < obj.month)
				return true;
			else if (month > obj.month)
				return false;
			else {
				if (day < obj.day)
					return true;
				else
					return false;
			}
		}
	}

	public int daysBetween(MyDate other) {
		int result = 0;
		if (this.isBefore(other)) {
			MyDate additional = this.copy();
			while (!additional.equals(other)) {
				additional.nextDay();
				result++;
			}
		} else {
			MyDate additional = other.copy();
			while (!additional.equals(this)) {
				additional.nextDay();
				result++;
			}
		}
		return result;

	}

	public void setDay(int d) {
		day = d;
	}

	public void setMonth(int m) {
		month = m;
	}

	public void setYear(int y) {
		year = y;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String toString() {
		if (month < 10) {
			String date = day + "/0" + month + "/" + year;
			return date;
		} else {
			String date = day + "/" + month + "/" + year;
			return date;
		}
	}
}
