//package com.example.AttendenceApp.configuration;
//
//import java.util.EnumSet;
//
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.SessionTrackingMode;
//
//@Configuration
//public class SessionConfig implements WebMvcConfigurer{
//
//    @Bean
//    public ServletContextInitializer servletContextInitializer() {
//        return new ServletContextInitializer() {
//            @Override
//            public void onStartup(ServletContext servletContext) throws ServletException {
//                // Set session tracking to use only cookies, and disable URL rewriting for session IDs.
//                servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
//            }
//        };
//    }
//    
//    
////    @Bean
////    public WebMvcConfigurer corsConfigurer() {
////        return new WebMvcConfigurer() {
////        	 @Override
////        	    public void addCorsMappings(CorsRegistry registry) {
////        	        registry.addMapping("/**")  // All endpoints
////        	                .allowedOrigins("*")  // Allow all origins
////        	                .allowedMethods("*")  // Allow all HTTP methods (GET, POST, etc.)
////        	                .allowedHeaders("*");  // Allow all headers
////        	    }
////        };
////    }
//    
////    @Bean
////    public InternalResourceViewResolver jspViewResolver() {
////        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
////        resolver.setPrefix("/WEB-INF/jsp/");
////        resolver.setSuffix(".jsp");
////        return resolver;
////    }
//    
////    @Override
////    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/first").setViewName("index");
////    }
//}
