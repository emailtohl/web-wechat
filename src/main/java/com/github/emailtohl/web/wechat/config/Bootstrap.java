package com.github.emailtohl.web.wechat.config;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
/**
 * 启动web类
 * @author HeLei
 */
public class Bootstrap implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		container.getServletRegistration("default").addMapping("/resources/*", "*.css", "*.js", "*.png", "*.gif",
				"*.jpg");

		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootContextConfiguration.class);
		container.addListener(new ContextLoaderListener(rootContext));

		AnnotationConfigWebApplicationContext MvcContext = new AnnotationConfigWebApplicationContext();
		MvcContext.register(MvcConfiguration.class);
//		MvcContext.setParent(rootContext);
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcherServlet",
				new DispatcherServlet(MvcContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.setMultipartConfig(new MultipartConfigElement(null, 20_971_520L, 41_943_040L, 512_000));
		dispatcher.addMapping("/");

		container.setAttribute("springContext", rootContext);
		
		FilterRegistration.Dynamic registration = container.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
		registration.addMappingForUrlPatterns(null, false, "/*");
	}
}
