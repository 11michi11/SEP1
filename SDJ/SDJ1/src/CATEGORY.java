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
   
}
