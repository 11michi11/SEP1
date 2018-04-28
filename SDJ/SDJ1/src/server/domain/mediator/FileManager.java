package server.domain.mediator;

import server.domain.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileManager {

	private static File eventFile = new File("src/resources/events.bin");
	private static File lecturerFile = new File("src/resources/lecturers.bin");
	private static File memberFile = new File("src/resources/members.bin");
	private static File emailsWhoHasntPaid = new File("emailsWhoHasntPaid.txt");
	private static File allEmails = new File("allEmails.txt");
	private static File allNewsletters = new File("src/resources/allNewsletters.txt");

	public static void generateListOfEmailsWhoHasntPaid(ArrayList<String> emails) throws IOException {
		PrintWriter out = new PrintWriter(emailsWhoHasntPaid);
		out.println(emails.toString());
		out.flush();
		out.close();
	}

	public static void generateListOfAllEmails(ArrayList<String> emails) throws IOException {
		PrintWriter out = new PrintWriter(allEmails);
		out.println(emails.toString());
		out.flush();
		out.close();
	}

	public static void generateEventFile(EventList eventList) throws IOException {
		FileOutputStream fos = new FileOutputStream(eventFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(eventList);
		out.writeInt(Event.getNextId());
		out.close();
		fos.close();
	}

	public static void generateLecturerFile(LecturerList lecturerList) throws IOException {
		FileOutputStream fos = new FileOutputStream(lecturerFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(lecturerList);
		out.writeInt(Lecturer.getNextId());
		out.close();
		fos.close();
	}

	public static void generateMemberFile(MemberList memberList) throws IOException {
		FileOutputStream fos = new FileOutputStream(memberFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(memberList);
		out.writeInt(Member.getNextId());
		out.close();
		fos.close();
	}

	public static EventList readEventFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(eventFile);

		read = new ObjectInputStream(fis);
		EventList events = (EventList) read.readObject();
		Event.setNextID(read.readInt());
		fis.close();
		read.close();

		return events;
	}

	public static LecturerList readLecturerFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(lecturerFile);

		read = new ObjectInputStream(fis);
		LecturerList lecturers = (LecturerList) read.readObject();
		Lecturer.setNextID(read.readInt());
		fis.close();
		read.close();

		return lecturers;
	}

	public static MemberList readMemberFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(memberFile);

		read = new ObjectInputStream(fis);
		MemberList members = (MemberList) read.readObject();
		Member.setNextID(read.readInt());
		fis.close();
		read.close();

		return members;
	}

	public static MemberList readMemberFile(File file) throws FileNotFoundException {
		MemberList members = new MemberList();
		String line, name, email, address;
		String[] divide, divideDate, divideTime;
		int phone, day, month, year, hour, minute;
		MyDate dateOfMembership;

		Scanner read = new Scanner(new BufferedReader(new FileReader(file)));
		System.out.println(read.hasNextLine());
		while (read.hasNext()) {
			line = read.nextLine();
			divide = line.split(";");
			name = divide[0].trim();
			address = divide[1].trim();
			phone = Integer.parseInt(divide[2].trim());
			email = divide[3].trim();
			divideDate = divide[4].trim().split("/");
			day = Integer.parseInt(divideDate[0].trim());
			month = Integer.parseInt(divideDate[1].trim());
			year = Integer.parseInt(divideDate[2].trim());
			dateOfMembership = new MyDate(day, month, year);
			members.addMember(new Member(name, address, phone, email, dateOfMembership));
		}

		read.close();
		return members;
	}

	public static Lecturer readLecturerFileInside(String line) {
		String name, email;
		String[] divide, categoriesDivide;
		int phone;
		boolean wantsAdvertise;
		ArrayList<Category> categories = new ArrayList<Category>();
		
		divide = line.split(";");
		name = divide[0];
		email = divide[1];
		phone = Integer.parseInt(divide[2]);
		categoriesDivide = divide[3].split(",");
		for (String e : categoriesDivide) {
			categories.add(new Category(e));
		}
		wantsAdvertise = Boolean.parseBoolean(divide[4].trim());
		
		return (new Lecturer(name, email, phone, categories, wantsAdvertise));
	}

	public static LecturerList readLecturerFile(File file) throws FileNotFoundException {
		LecturerList lecturers = new LecturerList();
		String line;
		Scanner read = new Scanner(file);
		while (read.hasNext()) {
			line = read.nextLine();
			lecturers.addLecturer(readLecturerFileInside(line));
		}

		read.close();
		return lecturers;
	}

	public static EventList readEventFile(File file) throws FileNotFoundException {
		EventList events = new EventList();
		String line, type, part1, part2;
		String[] divide, divideStartDate, divideEndDate, divideStartTime, divideEndTime, divideLecturers;
		int startDay, startMonth, startYear, startHour, startMinute, endDay, endMonth, endYear, endHour, endMinute;

		Scanner read = new Scanner(file);
		while (read.hasNext()) {
			HashMap<String, Object> event = new HashMap<String, Object>();
			line = read.nextLine();
			part1 = line.substring(0, line.indexOf("{"));
			part2 = line.substring(line.indexOf("{") + 1, line.length() - 1);
			divide = part1.split(";");
			type = divide[0];

			event.put("title", divide[1]);
			divideStartDate = divide[2].split("/");
			startDay = Integer.parseInt(divideStartDate[0]);
			startMonth = Integer.parseInt(divideStartDate[1]);
			startYear = Integer.parseInt(divideStartDate[2]);
			divideStartTime = divideStartDate[3].split(":");
			startHour = Integer.parseInt(divideStartTime[0]);
			startMinute = Integer.parseInt(divideStartTime[1]);
			event.put("startDate", new MyDate(startDay, startMonth, startYear, startHour, startMinute));
			divideEndDate = divide[3].split("/");
			endDay = Integer.parseInt(divideEndDate[0]);
			endMonth = Integer.parseInt(divideEndDate[1]);
			endYear = Integer.parseInt(divideEndDate[2]);
			divideEndTime = divideEndDate[3].split(":");
			endHour = Integer.parseInt(divideEndTime[0]);
			endMinute = Integer.parseInt(divideEndTime[1]);
			event.put("endDate", new MyDate(endDay, endMonth, endYear, endHour, endMinute));
			event.put("price", Double.parseDouble(divide[4]));
			event.put("finalized", Boolean.parseBoolean(divide[5]));
			event.put("description", divide[6]);
			event.put("capacity", Integer.parseInt(divide[7]));
			ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();

			switch (type.toLowerCase()) {
			case "lecture":
				event.put("lecturer", readLecturerFileInside(part2.substring(1, part2.length() - 1)));
				events.addEvent(new Lecture(event));
				break;
			case "seminar":
				divideLecturers = part2.split(",");
				for (String i : divideLecturers) {
					readLecturerFileInside(i.substring(1, i.length() - 1));
					lecturers.add(readLecturerFileInside(i.substring(1, i.length() - 1)));
				}
				event.put("lecturers", lecturers);
				events.addEvent(new Seminar(event));
				break;

			case "workshop":
				divideLecturers = part2.split(",");
				for (String i : divideLecturers) {
					readLecturerFileInside(i.substring(1, i.length() - 1));
					lecturers.add(readLecturerFileInside(i.substring(1, i.length() - 1)));
				}
				event.put("lecturers", lecturers);
				events.addEvent(new Workshop(event));
				break;

			case "trip":
				event.put("location", part2);
				events.addEvent(new Trip(event));
				break;
			default:
				System.out.println("No such type");
			}
		}

		read.close();
		return events;
	}

	public static void generateFileOfNewsletters(String newsletterName) throws IOException {
		PrintWriter output = new PrintWriter(new FileWriter(allNewsletters, true));
		output.printf("%s\r\n", newsletterName);
		output.close();
	}

	public static ArrayList<File> getAllNewsletters() throws IOException, ClassNotFoundException {
		ArrayList<File> newsletters = new ArrayList<File>();
		Scanner read = new Scanner(allNewsletters);
		
		while (read.hasNextLine())
			newsletters.add(new File(read.nextLine()));
		
		read.close();
		return newsletters;
	}

	public static void generateNewsletter(String additionalInfo, ArrayList<File> newsletterFiles, EventList events,
			LecturerList lecturers) throws IOException {
		newsletterFiles.add(new File("src/resources/Newsletter_" + new MyDate().toStringToFile() + ".txt"));
		PrintWriter out = new PrintWriter(newsletterFiles.get(newsletterFiles.size() - 1));
		String newsletterContent = "";
		
		newsletterContent += new MyDate().toStringToFile();
		newsletterContent += "\nUpcoming events:\n" + events.getFinalizedNotFinished().toString();
		newsletterContent += "\nOur sponsors: \n" + lecturers.getLecturersToAdvertise().toString();
		if (!(additionalInfo.equals("")))
			newsletterContent += "\nYou may find interesting:\n" + additionalInfo;
		
		out.println(newsletterContent);
		out.flush();
		out.close();
		generateFileOfNewsletters("src/resources/Newsletter_" + new MyDate().toStringToFile() + ".txt");
	}

	public static String readNewsletter(MyDate date, ArrayList<File> newsletterFiles) throws FileNotFoundException {
		String newsletter = "";
		
		for (File i : newsletterFiles) {
			if (i.getName().equals("src/resources/Newsletter_" + date.toStringToFile() + ".txt")) {
				Scanner read = new Scanner(i);
				
				while (read.hasNext())
					newsletter += read.nextLine() + "\n";
				read.close();
				return newsletter;
			} else {
				throw new FileNotFoundException("No newsletter on " + date.toString());
			}
		}
		throw new FileNotFoundException("Newsletter has not been generated, contact admin");
	}

}
