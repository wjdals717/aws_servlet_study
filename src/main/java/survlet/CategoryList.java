package survlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/category")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] categoryArray = {
		"한식" , "체험관광" , "카페" , "자연명소" , "양식" , "문화예술"
	};
	
	private class Feed {
		private String feedName;
		private String categoryName;
		
		public Feed(String feedName, String categoryName) {
			this.feedName = feedName;
			this.categoryName = categoryName;
		}
		
		public String getCategoryName() {
			return categoryName;
		}
		
		public String getFeedInfo() {
			return "feedName: "  + feedName +  ", categoryName: " + categoryName + "\n";
		}
	}
	
	private Feed[] feedArray =  {
		new Feed("1번 피드", "한식"),
		new Feed("2번 피드", "체험관광"),
		new Feed("3번 피드", "카페"),
		new Feed("4번 피드", "자연명소"),
		new Feed("5번 피드", "양식"),
		new Feed("6번 피드", "한식"),
		new Feed("7번 피드", "체험관광"),
		new Feed("8번 피드", "카페"),
		new Feed("9번 피드", "자연명소"),
		new Feed("10번 피드", "양식"),
		new Feed("11번 피드", "한식"),
		new Feed("12번 피드", "체험관광"),
		new Feed("13번 피드", "카페")
	};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		System.out.println(request.getMethod());
		String categoryName = request.getParameter("categoryName");
		
		if(!checkCategory(categoryName)) {
			response.setCharacterEncoding("UTF-8");
			response.setStatus(400);		//categoryName이 존재하지 않으므로 400 Bad Request라는 오류 상태로 바꿔줌
			response.getWriter().println("해당 카테고리는 존재하지 않는 카테고리입니다.");
			return;
		}
		
//		람다식에서 밖에 있는 변수의 주소를 바꾸는 행위는 불가능
//		Atomic은 주소를 밖에 있는 주소를 바꿔줌
//		AtomicReference<String> responseData = new AtomicReference<String>("");
//		
//		findFeedByCategoryName(categoryName).forEach(feed -> {
//			responseData.set(responseData.get() + feed.getFeedInfo());
//		});
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().println(responseData.getPlain());
		
		Gson gson = new Gson();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		//응답하려는 컨텐츠 타입이 json임을 알려줌, 응답을 객체로 자동으로 parsing이 일어남
		response.getWriter().println(gson.toJson(findFeedByCategoryName(categoryName)).toString());
		
	}

	private boolean checkCategory(String categoryName) {
		for(int i = 0; i < categoryArray.length; i++) {
			if(categoryArray[i].equals(categoryName)) {
				return true;
			}
		}
		return false;
	}
	
	private List<Feed> findFeedByCategoryName(String categoryName) {
		List<Feed> feeds = new ArrayList<>();
		
		for(int i= 0;  i < feedArray.length; i++) {
			if(feedArray[i].getCategoryName().equals(categoryName)) {
				feeds.add(feedArray[i]);
			}
		}
		
		return feeds;
	}
}
