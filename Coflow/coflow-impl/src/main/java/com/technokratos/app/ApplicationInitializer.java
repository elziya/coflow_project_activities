package com.technokratos.app;

import com.technokratos.config.DataBaseConfig;
import com.technokratos.config.WebMvcConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springWebContext = new AnnotationConfigWebApplicationContext();

        springWebContext.register(WebMvcConfig.class);
        springWebContext.register(DataBaseConfig.class);

        ContextLoaderListener listener = new ContextLoaderListener(springWebContext);
        servletContext.addListener(listener);

        DispatcherServlet dp =  new DispatcherServlet(springWebContext);
        dp.setThrowExceptionIfNoHandlerFound(true);

        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcher", dp);
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }
}
