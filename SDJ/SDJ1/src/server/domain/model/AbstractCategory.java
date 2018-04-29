package server.domain.model;

import java.util.Objects;

public abstract class AbstractCategory {
    public static final String ASTROLOGY = "Astrology";
    public static final String MEDITATION = "Meditation";
    public static final String REINCARNATION = "Reincarnation";
    public static final String HEALTH = "Health";
    public static final String BUDDHISM = "Buddhism";
    public static final String NATURE = "Nature";
    public static final String OTHER = "Other";

    private String name;
    private int number;
    
    public AbstractCategory(String name) {
	this.name=Character.toUpperCase(name.charAt(0))+ name.substring(1).toLowerCase();
	setNumber();
    }
    private void setNumber() {
	switch(getName()) {
	case "Astrology": 
	    number=0;
	    break;
	case "Meditation":
	    number=1;
	    break;
	case "Reincarnation":
	    number=2;
	    break;
	case "Health":
	    number=3;
	    break;
	case "Buddhism":
	    number=4;
	    break;
	case "Nature":
	    number=5;
	    break;
	default:
	    number=6;
	    break;
	}
    }
    public String getName() {
	return name;
    }
    public int getNumber() {
	return number;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractCategory that = (AbstractCategory) o;
		return number == that.number &&
				Objects.equals(name, that.name);
	}
}
