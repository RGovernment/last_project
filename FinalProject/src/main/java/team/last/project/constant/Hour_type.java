package team.last.project.constant;

public enum Hour_type {
	H3("3시간"), H6("6시간"), DAY("올데이");
	
	private final String description;
	
	Hour_type(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
