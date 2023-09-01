package utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
//	private int statusCode;
//	private String body;

	public static ResponseUtilBuilder response(HttpServletResponse response) {
		return new ResponseUtilBuilder(response);	//객체가 생성되어 주소가 할당됨
	}
	
	public static class ResponseUtilBuilder {
		private HttpServletResponse response;
		
		public ResponseUtilBuilder(HttpServletResponse response) {
			this.response = response;
		}
		
		public ResponseUtilBuilder of(int statusCode) { //상태 코드 저장
			response.setStatus(statusCode);
			return this;
		}
		
		public void body(Object body) throws IOException {  //회원 가입 완료 문장이나 실패했을 때 문장
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(body);
		}
	}
}
