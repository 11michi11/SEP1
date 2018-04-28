package server.domain.model;

import java.io.Serializable;
import java.util.HashMap;


public class CategoryFactory implements Serializable {
	private static HashMap<String, Category> categories = new HashMap<String, Category>();

	public static Category getCategory(String name) {
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
		return createCategory(name);
	}

	public static Category getCategory(int number){
		String name = fromNumberToName(number);
		return createCategory(name);
	}

	private static Category createCategory(String name){
		Category item = categories.get(name);
		if (item == null) {
			item = new Category(name);
			categories.put(name, item);
		}
		return item;
	}

	private static String fromNumberToName(int number) {
		switch (number) {
			case 0:
				return Category.ASTROLOGY;
			case 1:
				return Category.MEDITATION;
			case 2:
				return Category.REINCARNATION;
			case 3:
				return Category.HEALTH;
			case 4:
				return Category.BUDDHISM;
			case 5:
				return Category.NATURE;
			default:
				return Category.OTHER;

		}
	}
}
