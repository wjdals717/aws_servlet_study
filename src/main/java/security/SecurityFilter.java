package security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ResponseUtil;

@WebFilter("*")
public class SecurityFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String rootPath = "/servlet_study_jeongmin";
		String[] antMatchers =  {"/auth"};				//인증이 필요없는 path들
		
		String uri = req.getRequestURI();
		
		//인증이 필요없는 경우
		for(String antMatcher: antMatchers) {
			if(uri.startsWith(rootPath + antMatcher)) { //"servlet_study_jeongmin/auth" 이렇게 시작하면 다음으로 넘어감
				chain.doFilter(request, response);
				return;
			}
		}
		
		String token = req.getHeader("Authorization");
		
		//인증이 필요한 경우 -> mypage
		if(!req.getMethod().equalsIgnoreCase("options") && !SecurityContextHolder.isAuthenticated(token)) {	//이 토큰을 가진 사람은 인증되지 않았음 -> 유효한 토큰이 아니거나 토큰이 없는 경우
			ResponseUtil.response(resp).of(401).body("인증 실패");
			return;
		}
		
		chain.doFilter(request, response); //profileServlet으로 이동하게 됨
	}
}
