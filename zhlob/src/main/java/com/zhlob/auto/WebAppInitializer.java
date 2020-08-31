package com.zhlob.auto;

import com.zhlob.auto.security.SecurityFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext root =
                new AnnotationConfigWebApplicationContext();
        root.scan("com.zhlob.auto");
        servletContext.addListener(new ContextLoaderListener(root));
        root.setServletContext(servletContext);
        ServletRegistration.Dynamic appServlet =
                servletContext.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
        DelegatingFilterProxy delegatingFilterProxy = new SecurityFilter();
        delegatingFilterProxy.setTargetFilterLifecycle(true);
        servletContext.addFilter("springSecurityFilterChain", delegatingFilterProxy.getClass())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC),
                        false, "/*");
        servletContext.addFilter("characterEncodingFilter", getCharacterEncodingFilter().getClass())
                .addMappingForUrlPatterns(null, false,
                        "/*");
    }

    private CharacterEncodingFilter getCharacterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
