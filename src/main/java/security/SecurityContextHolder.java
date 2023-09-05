package security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SecurityContextHolder {
	
	public static List<Authentication> authentications = new ArrayList<>();			//여기 안에 들어가 있으면 인증된 객체
	
	public static void addAuth(Authentication authentication) {
		authentications.add(authentication);
	}
	
	public static Boolean isAuthenticated(String token) {		//토큰을 받은 후 인증이 되었는지 확인
		for(Authentication authentication: authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				return true;
			}
		}
		return false;
	}
	
	public static Authentication findAuthenticationByToken (String token) {
		for(Authentication authentication: authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				return authentication;
			}
		}
		
		return null;
	}
	
	public static void removeAuth(String token) {				//토큰 제거
		for(Authentication authentication: authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				authentications.remove(authentication);
				break;
			}
		}
	}
}
