package fileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.LecturerList;
import model.MemberList;
import model.MyDate;

public class FileManager {
   
   private File eventFile, lecturerFile, memberFile;
   private ArrayList<File> newsletterFiles;
   
   public FileManager ()
   {
      newsletterFiles = new ArrayList<File>();
   }
   
   public void generateEventFile(MemberList memberList) throws IOException
   {
      eventFile = new File("events.bin");
      FileOutputStream fos = new FileOutputStream(eventFile);
      ObjectOutputStream out = new ObjectOutputStream(fos);

      out.writeObject(memberList);
      out.close();
      fos.close();
   } 
   public void generateLecturerFile(LecturerList lecturerList) throws IOException
   {
      lecturerFile= new File("lecturers.bin");
      FileOutputStream fos = new FileOutputStream(lecturerFile);
      ObjectOutputStream out = new ObjectOutputStream(fos);

      out.writeObject(lecturerList);
      out.close();
      fos.close();
      
   }
   public void generateMemberFile(MemberList memberList) throws IOException
   {
      memberFile= new File("members.bin");
      FileOutputStream fos = new FileOutputStream(memberFile);
      ObjectOutputStream out = new ObjectOutputStream(fos);

      out.writeObject(memberList);
      out.close();
      fos.close();
      
   }
   public void generateNewsletterFile(String newsletterContent) throws IOException
   {
      newsletterFiles.add( new File("Newsletter_"+new MyDate()+".txt"));
      PrintWriter out = new PrintWriter (newsletterFiles.get(newsletterFiles.size()-1));
      out.println(newsletterContent);
      out.flush();
      out.close();
   }
}
