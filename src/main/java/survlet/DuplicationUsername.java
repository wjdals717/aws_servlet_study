package survlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ResponseUtil;


@WebServlet("/auth/signup/duplicate/username")
public class DuplicationUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] usernames = { "aaa", "bbb", "ccc" };   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		for(int i = 0; i < usernames.length; i++) {
			if(Objects.equals(usernames[i], username)) { //Objects.equals은 null일 수 없음, 따로 nullpointException을 할 필요가 없음
				ResponseUtil.response(response).of(400).body(true); //중복 -> true
				return;
			}
		}
		ResponseUtil.response(response).of(200).body(false);		//중복X -> false
	}

}
