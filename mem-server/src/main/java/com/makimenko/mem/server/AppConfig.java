package com.makimenko.mem.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.makimenko.mem.server.dao.GameDao;
import com.makimenko.mem.server.dao.impl.GameDaoSimpleImpl;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	@Value("${upload.dir}")
	private String uploadDir;

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setIncludeHeaders(true);
		return loggingFilter;
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false);
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("addResourceHandlers: Map download folder to {}", this.uploadDir);
		registry.addResourceHandler("/download/**").addResourceLocations("file:" + uploadDir);
	}

	@Bean
	public GameDao gameDao() {
		return new GameDaoSimpleImpl();
	}

}
