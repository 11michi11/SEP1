package fileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.EventList;
import model.LecturerList;
import model.MemberList;
import model.MyDate;
import model.Newsletter;

public class FileManager {

	private File eventFile, lecturerFile, memberFile;
	private ArrayList<File> newsletterFiles;

	public FileManager() {
		this.newsletterFiles = new ArrayList<File>();
	}

	public void generateEventFile(EventList eventList) throws IOException {
		eventFile = new File("events.bin");
		
		FileOutputStream fos = new FileOutputStream(eventFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(eventList);
		out.close();
		fos.close();
	}

	public void generateLecturerFile(LecturerList lecturerList) throws IOException {
		lecturerFile = new File("lecturers.bin");
		
		FileOutputStream fos = new FileOutputStream(lecturerFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(lecturerList);
		out.close();
		fos.close();
	}

	public void generateMemberFile(MemberList memberList) throws IOException {
		memberFile = new File("members.bin");
		
		FileOutputStream fos = new FileOutputStream(memberFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(memberList);
		out.close();
		fos.close();
	}

	public void generateNewsletterFile(String newsletterContent) throws IOException {
		newsletterFiles.add(new File("Newsletter_" + new MyDate().toString() + ".txt"));
		PrintWriter out = new PrintWriter(newsletterFiles.get(newsletterFiles.size() - 1));
		
		out.println(newsletterContent);
		
		out.flush();
		out.close();
	}

	public EventList readEventFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(eventFile);
		
		read = new ObjectInputStream(fis);
		EventList events = (EventList) read.readObject();
		
		fis.close();
		read.close();
		
		return events;
	}

	public LecturerList readLecturerFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(lecturerFile);
		
		read = new ObjectInputStream(fis);
		LecturerList lecturers = (LecturerList) read.readObject();
		
		fis.close();
		read.close();
		
		return lecturers;
	}

	public MemberList readMemberFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(memberFile);
		
		read = new ObjectInputStream(fis);
		MemberList members = (MemberList) read.readObject();
		
		fis.close();
		read.close();
		
		return members;
	}

	public String readNewsletterFile(MyDate date) throws FileNotFoundException {
		File file;
		String newsletter = "";
		for (File i : newsletterFiles) {
			if (i.getName().equals("Newsletter_" + date.toString() + ".txt")) {
				file = i;
				Scanner read = new Scanner(file);
				while (read.hasNext())
					newsletter += read.nextLine() + "\n";
				read.close();
				return newsletter;
			} else {
				throw new FileNotFoundException("No newsletter on " + date.toString());
			}
		}
		return "Something went wrong...";
	}

}
