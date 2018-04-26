package domain.model;

import java.util.HashMap;


public class CategoryFactory {
    private static HashMap<String, Category> categories= new HashMap<String, Category>();
    
    public static Category getCategory(String name) {
	Category item =categories.get(name);
	if(item==null)
	    categories.put(name, new Category(name));
	return item;
    }
    public static Category getCategory(int number) {
	
    }
}
