package domain.model;

public abstract class AbstractCategory {
    public static final String[] legal_names= {"Astrology", "Meditation", "Reincarnation", "Health", "Buddhism", "Nature", "Other"};
    private String name;
    private int number;
    
    public AbstractCategory(String name) {
	this.name=Character.toUpperCase(name.charAt(0))+ name.substring(1).toLowerCase();
	setNumber();
    }
    public void setNumber() {
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
    
}
