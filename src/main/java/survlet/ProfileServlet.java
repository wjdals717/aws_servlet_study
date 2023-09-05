package survlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import security.Authentication;
import security.SecurityContextHolder;
import utils.JsonParseUtil;
import utils.ResponseUtil;

@WebServlet("/mypage/profile") //auth로 시작하지 않기 때문에 무조건 인증을 거쳐야 함
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		
		User user = SecurityContextHolder.findAuthenticationByToken(token).getUser();
		
		ResponseUtil.response(response).of(200).body(JsonParseUtil.toJson(user));
	}
	
	//update
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> profileMap = JsonParseUtil.toMap(request.getInputStream());
		
		Authentication authentication = SecurityContextHolder.findAuthenticationByToken(request.getHeader("Authorization"));
		User oldUser = authentication.getUser(); //해당 토큰의 유저 정보를 가져옴
		
		List<User> userList = UserData.userList;
		
		User user = User.builder()
				.userId(oldUser.getUserId())
				.username((String) profileMap.get("username"))
				.password((String) profileMap.get("password"))
				.name((String) profileMap.get("name"))
				.email((String) profileMap.get("email"))
				.build();
		
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserId() == user.getUserId()) {
				userList.set(i, user);
				authentication.setUser(user);
				ResponseUtil.response(response).of(200).body(true);
			}
		}
		ResponseUtil.response(response).of(200).body(false);
	}

}
