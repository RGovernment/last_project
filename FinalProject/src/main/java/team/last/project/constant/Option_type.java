package team.last.project.constant;

public enum Option_type {
	SINGLE("싱글"), PACKAGE("패키지");
	
	private final String description;
	
	Option_type(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
