package survlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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


@WebServlet("/auth/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> signinUser = JsonParseUtil.toMap(request.getInputStream());		//signin page의 username, password를 들고옴
		Map<String, String> responseData = new HashMap<>();
		
		for(User user: UserData.userList) {
			if(Objects.equals(user.getUsername(), signinUser.get("username"))
					&& Objects.equals(user.getPassword(), signinUser.get("password"))) { //로그인 할 때 username, password가 일치하면 true
				
				String token = UUID.randomUUID().toString();	//클라이언트마다 새 토큰을 만듦
				SecurityContextHolder.addAuth(new Authentication(user, token));
				responseData.put("token", token);
//				ResponseUtil.response(response).of(200).body(responseData);
				break;
			}
		}
		
//		ResponseUtil.response(response).of(200).body(responseData);
		ResponseUtil.response(response).of(200).body(JsonParseUtil.toJson(responseData));	//Json으로 바꿔 줘야 참조를 함
		
	}

}
