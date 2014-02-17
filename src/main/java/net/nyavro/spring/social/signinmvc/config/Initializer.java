package net.nyavro.spring.social.signinmvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class Initializer implements WebApplicationInitializer {
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING = "/";

    @Override
    public void onStartup(final ServletContext context) throws ServletException {
        final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(ApplicationContext.class);
        final ServletRegistration.Dynamic dispatcher = context.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(root));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);
        final EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        context.addFilter("characterEncoding", characterEncodingFilter()).addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        context.addFilter("springSecurityFilterChain", new DelegatingFilterProxy()).addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        context.addListener(new ContextLoaderListener(root));
    }

    private CharacterEncodingFilter characterEncodingFilter() {
        final CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}


