package server.domain.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class MyDate implements Serializable {
	private int day, month, year, hour, minute;

	public MyDate() {
		GregorianCalendar currentDate = new GregorianCalendar();
		day = currentDate.get(GregorianCalendar.DATE);
		month = currentDate.get(GregorianCalendar.MONTH) + 1;
		year = currentDate.get(GregorianCalendar.YEAR);
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

	public MyDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = 0;
		this.minute = 0;
	}

	public MyDate(String dateString) throws InvalidDateInput {
		String[] dateArray, timeArray;
		dateArray = dateString.split("/");

		if (dateArray.length == 4) {
			day = Integer.parseInt(dateArray[0]);
			month = Integer.parseInt(dateArray[1]);
			year = Integer.parseInt(dateArray[2]);
			timeArray = dateArray[3].split(":");
			if (timeArray.length == 2) {
				hour = Integer.parseInt(timeArray[0]);
				minute = Integer.parseInt(timeArray[1]);
			} else
				throw new InvalidDateInput("Invalid date");

		} else if (dateArray.length == 3) {
			day = Integer.parseInt(dateArray[0]);
			month = Integer.parseInt(dateArray[1]);
			year = Integer.parseInt(dateArray[2]);
			hour = 0;
			minute = 0;
		} else
			throw new InvalidDateInput("Invalid date");

		if (!isValid())
			throw new InvalidDateInput("Invalid date");
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
				currentDate.get(GregorianCalendar.YEAR), currentDate.get(GregorianCalendar.HOUR),
				currentDate.get(GregorianCalendar.MINUTE));
	}

	public static MyDate getDefaultDate() {
		return new MyDate(1, 1, 1, 0, 0);
	}

	public boolean isValid() {
		return month > 1 && month <= 12 && day > 1 && day <= DayInMonth() && year > 1000;
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
		String returnString = "";
		if (day < 10) {
			returnString += "0" + day;
		} else {
			returnString += day;
		}
		if (month < 10) {
			returnString += "/0" + month;
		} else {
			returnString += "/" + month;
		}
		returnString += "/" + year;
		if (hour < 10) {
			returnString += "/0" + hour;
		} else {
			returnString += "/" + hour;
		}
		if (minute < 10) {
			returnString += ":0" + minute;
		} else {
			returnString += ":" + minute;
		}
		return returnString;
	}

	public String toStringToFile() {
		String returnString = "";
		if (day < 10) {
			returnString += "0" + day;
		} else {
			returnString += day;
		}
		if (month < 10) {
			returnString += "-0" + month;
		} else {
			returnString += "-" + month;
		}
		returnString += "-" + year;
		return returnString;
	}
}
