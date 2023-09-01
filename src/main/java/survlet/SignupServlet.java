package survlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import utils.JsonParseUtil;
import utils.ResponseUtil;

/**
 * 회원가입 -> 사용자 정보 데이터의 추가를 의미
 * 추가 -> Create, 데이터 베이스에 정보를 insert -> Post 요청
 * Post 메소드의 특징
 * 1. 요청시 서버로 전달되어지는 데이터가 주소창에 표시되지 않는다. 
 * -> Get : http://localhost:8080/category?categoryName=한식(X)
 * -> Post : http://localhost:8080/category (BODY에 데이터를 담아서 서버로 전송)
 * 2. 전송 데이터의 크기 제한이 없다.
 */

@WebServlet("/auth/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		InputStream inputStream = request.getInputStream();
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//		
//		StringBuilder requestBody = new StringBuilder(""); //문자열 한 줄로 저장해주는 역할
//		
//		
//		while(true) {
//			String data = bufferedReader.readLine();
//		
//			if(data == null) {
//				break;
//			}
//			requestBody.append(data);
//		}
//		System.out.println(requestBody.toString());
//		
//		Gson gson = new Gson();
//		Map<String, String> userMap = gson.fromJson(requestBody.toString(), Map.class);
//		System.out.println(userMap);
		
		Map<String, Object> userMap = JsonParseUtil.toMap(request.getInputStream());
		System.out.println(userMap);
		
//		System.out.println(userMap.get("username"));
//		System.out.println(userMap.get("password"));
//		System.out.println(userMap.get("name"));
//		System.out.println(userMap.get("email"));
		
//		ResponseUtil.ofJson(response, JsonParseUtil.toJson(userMap));
//		userMap을 Json으로 응답받고 다시 userMap을 Json으로 바꿔 응답함
		
		System.out.println("회원가입");
		
		ResponseUtil.response(response).of(200).body("회원가입 성공");
	}

}
