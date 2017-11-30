import java.util.Map;

public class Lecture extends Event {

	private CATEGORY category;

	public Lecture(Map<String, Object> configuration) {
		super(configuration);
		this.category = (CATEGORY) configuration.getOrDefault("category", CATEGORY.Other);
	}


	public void modify(Map<String, Object> configuration) {
		super.modify(configuration);
		this.category = (CATEGORY) configuration.getOrDefault("category", CATEGORY.Other);
	}
	
	public boolean canBeFinalized() {
		if(super.canBeFinalized())
			return true;
		return false;
	}

}
