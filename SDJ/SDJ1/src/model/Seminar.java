package model;
import java.util.ArrayList;
import java.util.Map;

public class Seminar extends Event {

	private ArrayList<CATEGORY> categories;

	public Seminar(Map<String, Object> configuration) {
		super(configuration);
		this.categories = (ArrayList<CATEGORY>) configuration.getOrDefault("category", new ArrayList<CATEGORY>());
	}


	public void modify(Map<String, Object> configuration) {
		super.modify(configuration);
		this.categories = (ArrayList<CATEGORY>) configuration.getOrDefault("category", this.categories);
	}

	public boolean canBeFinalized() {
		if(super.canBeFinalized() && !categories.isEmpty())
			return true;
		return false;
	}
}
