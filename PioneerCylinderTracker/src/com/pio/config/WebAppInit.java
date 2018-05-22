package com.pio.config;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInit implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		/*AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.scan(AppConfiguration.class.getPackage().getName());
		servletContext.addListener(new ContextLoaderListener(context));*/
		
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/spring-config.xml");
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		Set<String> mappings = dispatcher.addMapping("/");
		if(!mappings.isEmpty()) {
			throw new IllegalStateException("conflicting mappings found!"+mappings);
		}
	}

}
