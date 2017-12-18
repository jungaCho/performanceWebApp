package util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;

@WebFilter(urlPatterns= {"/*"}, initParams= {@WebInitParam(name="encoding", value="utf-8")})
public class EncodingFilter implements Filter{
	private String encoding;

	public void init(FilterConfig filterConfig) throws ServletException{
		String temp = filterConfig.getInitParameter("encoding");
		if(temp != null){
			encoding = temp;
		}
	}

	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)
       throws java.io.IOException, ServletException{
		HttpServletRequest req = (HttpServletRequest)request;
		String method = req.getMethod();
		//System.out.println("method: "+method);

		if(method.equalsIgnoreCase("POST")){
			req.setCharacterEncoding(encoding);
		}
		chain.doFilter(request,response); //다음 filter 연결
	}

	public void destroy(){
		//System.out.println("call destroy of Filter");
	}
}
