package br.com.symplus.challenger.bevolunteer.api.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class AppConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		factory.setContextPath("/api/v1");
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("messages_pt-br");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

}
