package com.kodgemisi.staj2018.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created on August, 2018
 *
 * @author suleymancan
 */
// TODO: 26.08.2018 : ViewControllerRegistry ve WebMvcCOnfigurer, detaylı incele. 
@Configuration
public class TemporalViewConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
}
