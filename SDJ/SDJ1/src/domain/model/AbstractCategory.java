package domain.model;

public abstract class AbstractCategory {
    private String name;
    private static int nextNumber=0;
    private int number;
    
    public AbstractCategory(String name) {
	this.name=name;
	number=nextNumber++;
    }
    public String getName() {
	return name;
    }
    public int getNumber() {
	return number;
    }
}
