package com.example.AttendenceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication
public class AttendenceAppApplication extends SpringBootServletInitializer {
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(AttendenceAppApplication.class);
	    }

	public static void main(String[] args) {
		
		
        System.setProperty("java.library.path", "C:/Users/vasanth/Downloads/opencv/build/java/x64");

		
		SpringApplication.run(AttendenceAppApplication.class, args);
		
	}

}
