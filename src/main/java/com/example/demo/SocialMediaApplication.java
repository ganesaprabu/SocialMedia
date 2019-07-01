package com.example.demo;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SocialMediaApplication {

	public static void main(String[] args) {
		System.out.println("GIT");
		SpringApplication.run(SocialMediaApplication.class, args);
	}
	
	
	/*
	 * Below 2 Beans are for the Internationalization (i18n).    
	 * Here the messages are being picked by based on the Locale 
	 *   -----------------------------------------------------------  Start --------------------
	 * */
	
					/*
					 * In the below style "SessionLocaleResolver", in each controller and in each method, wherever the locale specific
					 * Information is needed, then we have to specify the below parameter in the method to pull the locale.
					 * 
					 *  @RequestHeader(name= "Accept-Language", required = false) Locale locale
					 *  
					 * */
					/*
					@Bean
					public LocaleResolver localeResolver() {
						SessionLocaleResolver localeResolver = new SessionLocaleResolver();
						localeResolver.setDefaultLocale(Locale.US);
						return localeResolver;
					}
					*/
					/*
					 * When we use "AcceptHeaderLocaleResolver", then we don't want to get the locale from the @RequestHeader. 
					 * Instead, we can get the value from the "LocaleContextHolder" like the below in all the controller.
					 * 																		       ----------------------
					 * 		messageSource.getMessage("welcome.message", null, LocaleContextHolder.getLocale());
					 * 														  ------------------------------							
					 * */
					@Bean
					public LocaleResolver localeResolver() {
						AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
						localeResolver.setDefaultLocale(Locale.US);
						return localeResolver;
					}
					/*
					@Bean
					public ResourceBundleMessageSource messageSource() {
						ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
						messageSource.setBasename("messages");
						return messageSource;
					} 
					*/
					
	/*
	 * Above 2 Beans are for the Internationalization (i18n).    
	 * Here the messages are being picked by based on the Locale 
	 *   -----------------------------------------------------------  End --------------------
	 * */				
}
	