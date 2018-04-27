package domain.model;

import java.io.Serializable;
import java.util.HashMap;


public class CategoryFactory implements Serializable {
	private static HashMap<String, Category> categories = new HashMap<String, Category>();

	public static Category getCategory(String name) {
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
		Category item = categories.get(name);
		if (item == null) {
			item = new Category(name);
			categories.put(name, item);
		}
		return item;
	}

	public static Category getCategory(int number) {
		switch (number) {
			case 0:
				return categories.get("Astrology");
			case 1:
				return categories.get("Meditation");
			case 2:
				return categories.get("Reincarnation");
			case 3:
				return categories.get("Health");
			case 4:
				return categories.get("Buddhism");
			case 5:
				return categories.get("Nature");
			default:
				return categories.get("Other");

		}
	}
}
