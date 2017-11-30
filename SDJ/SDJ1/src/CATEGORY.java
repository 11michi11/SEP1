import java.util.ArrayList;

public class CATEGORY {
   private ArrayList<String> categoryList;
   
   public CATEGORY()
   {
      categoryList=new ArrayList<String>();
   }
   public void addCategory(String category)
   {
      categoryList.add(category);
   }
   public String findCategory(String category)
   {
      for(String i:categoryList)
      {
         if(i.equals(category))
            return i;
      }
      return "No such category";
   }
   public String toString()
   {
      String print="";
      for(String i:categoryList)
      {
         if(i!=categoryList.get(categoryList.size()-1))
            print+=i+", ";
         else
            print+=i;
      }
      return print;
   }
   
}
