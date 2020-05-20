package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*") //url pattern mapping
public class CharacterEncodingFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //chain이 넘겨줄까말까 결정
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request,response); //흐름을 넘겨서 다음 서블릿이 실행되게 함. -> 다음 실행을 관할
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
