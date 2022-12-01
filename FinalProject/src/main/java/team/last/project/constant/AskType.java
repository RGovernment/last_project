package team.last.project.constant;

public enum AskType {
	RESERVE("예약"), OPTION("옵션"), REFUND("환불"), ETC("기타");

	private final String description;

	AskType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
