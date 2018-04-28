package server.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import server.domain.mediator.FileManager;

public class FileManagerTest
{
   public static void main(String[] args) throws IOException
   {
      /*File memberFile = new File("src/resources/members.txt");
      
      System.out.println(FileManager.readMemberFile(memberFile));*/
      
      /*File lecturerFile = new File("src/resources/lecturer.txt");
      
      System.out.println(FileManager.readLecturerFile(lecturerFile));*/
      
      File eventFile = new File("src/resources/events.txt");
      
      System.out.println(FileManager.readEventFile(eventFile));
   }
}
