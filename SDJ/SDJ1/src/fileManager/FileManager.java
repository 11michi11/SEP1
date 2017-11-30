package fileManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class FileManager {
   
   private File eventFile, lecturerFile, memberFile, newsletterFile;
   
   public void generateEventFile(MemberList memberList) throws IOException
   {
      eventFile = new File("events.bin");
      FileOutputStream fos = new FileOutputStream(eventFile);
      ObjectOutputStream out = new ObjectOutputStream(fos);

      out.writeObject(memberList);
      out.close();
      fos.close();
   } 
   public void generateLecturerFile(LectuerList lecturerList) 
   {
      lecturerFile= new File("lecturers.bin");
      FileOutputStream fos = new FileOutputStream(lecturerFile);
      ObjectOutputStream out = new ObjectOutputStream(fos);

      out.writeObject(lecturerList);
      out.close();
      fos.close();
      
   }
   public void generateMemberFile(MemberList memberList)
   {
      memberFile= new File("members.bin");
      FileOutputStream fos = new FileOutputStream(memberFile);
      ObjectOutputStream out = new ObjectOutputStream(fos);

      out.writeObject(memberList);
      out.close();
      fos.close();
      
   }
   public void generateNewsletterFile(String newsletterContent)
   {
      newsletterFile= new File("Newsletter_"+new MyDate()+".txt");
      PrintWriter out = new PrintWriter (newsletterFile);
      out.println(newsletterContent);
      out.flush();
      out.close();
   }
}
