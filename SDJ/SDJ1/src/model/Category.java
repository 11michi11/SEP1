package model;

public enum Category {
	Astrology, Meditation, Reincarnation, Health, Buddhism, Nature, Other;

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
		case "TraditionalBuddhism":
			return Category.Buddhism;
		case "Nature":
			return Category.Nature;
		case "Other":
			return Category.Other;
		default:
			return Category.Other;
		}
	}

	public static Category fromNumberToCategory(int number) {
		switch (number) {
		case 0:
			return Category.Astrology;
		case 1:
			return Category.Meditation;
		case 2:
			return Category.Reincarnation;
		case 3:
			return Category.Health;
		case 4:
			return Category.Buddhism;
		case 5:
			return Category.Nature;
		case 6:
			return Category.Other;
		default:
			return Category.Other;
		}
	}

}
