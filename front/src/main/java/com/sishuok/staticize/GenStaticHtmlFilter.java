package com.sishuok.staticize;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenStaticHtmlFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		//1：取到访问的url
		String urlPath = request.getRequestURI();
		String genParam = request.getParameter("gen");
		//2：判断是否需要静态化
		if(urlPath.endsWith("/toIndex") && ! "true".equals(genParam)){
			//3：对于需要静态化的，判断是否已经有了静态化的内容
			Object obj = GenHtml.mapHtml.get("toIndex");
			if(obj!=null){
				//3.1如果有，就直接取出来，返回客户端
				System.out.println("now have static indexxxxxxxxx");
			}else{
				//3.2：如果没有，就调用后端生成Html的方法，然后获取内容返回
				new GenHtml().genStaticHtml();
				obj = GenHtml.mapHtml.get("toIndex");
			}
			
			byte[] bs = (byte[])obj;
			
			//直接返回
			response.getOutputStream().write(bs);
			
		}else{
			chain.doFilter(req, resp);
		}
		
		
	}

	public void destroy() {
		
	}

}
