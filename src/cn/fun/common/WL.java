package cn.fun.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WL implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
//		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
//		ISysService service = (ISysService) wac.getBean("sysService");
		 
	}

}
