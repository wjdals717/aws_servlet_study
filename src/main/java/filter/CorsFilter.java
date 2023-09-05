package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;


@WebFilter("*")	//특정필터를 작성하면 해당 요청에 대해서만 동작이 실행됨, *은 모든 요청에 대해서 이 필터를 거침
public class CorsFilter extends HttpFilter implements Filter {

//	톰캣 -> 전처리 -> 전처리 필터 -> 서블릿 -> 후처리 -> 응답
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpServletResponse= (HttpServletResponse) response; //ServletRequest은  setHeader가 없기 때문에 다운캐스팅을 통해 HttpServletResponse로 변경 뒤 setHeader를 씀
		
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");			//http://localhost:3000 이 주소에서 오는 요청만 받아들임. *이면 모든 주소에서 들어오는 요청을 받아들임
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");	//받아들일 요청의 종류
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");	//24시간을 의미
		
		chain.doFilter(request, response);	//서블릿이 호출되는 지점 //후처리
	}	

}
