package model;

public enum Category {
	Astrology, Meditation, Reincarnation, Health, TraditionalBuddhism, Nature, Other;

	public static Category parseCategory(String category) {
		switch (category) {
		case "Astrology":
			return Category.Astrology;
		case "Meditation":
			return Category.Meditation;
		case "Reincarnation":
			return Category.Reincarnation;
		case "Health":
			return Category.Health;
		case "TrafitionalBuddhism":
			return Category.TraditionalBuddhism;
		case "Nature":
			return Category.Nature;
		case "Other":
			return Category.Other;
		default:
			return Category.Other;
		}
	}

}
