package com.sishuok.staticize;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GenStaticHtmlListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
//		ApplicationContext ctx = (ApplicationContext)sce.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		GenHtml gh = new GenHtml();
		gh.startGenStaticHtml();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
