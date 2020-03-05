package ru.gvozdilin.bibl.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.gvozdilin.bibl.dao.UserRepository;


public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class, WebConfig.class};
    }







    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }
}

