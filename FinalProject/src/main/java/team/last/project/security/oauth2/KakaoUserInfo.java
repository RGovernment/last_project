package team.last.project.security.oauth2;

import java.util.Map;

public class KakaoUserInfo {
	private Map<String, Object> attributes;
	private Map<String, Object> attributesAccount;
	private Map<String, Object> attributesProfile;

	@SuppressWarnings("unchecked")
	public KakaoUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
		this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
		this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public String getProviderId() {
		return attributes.get("id").toString();
	}

	public String getProvider() {
		return "Kakao";
	}

	public String getEmail() {
		return attributesAccount.get("email").toString();
	}

	public String getName() {
		return attributesProfile.get("nickname").toString();
	}

}
