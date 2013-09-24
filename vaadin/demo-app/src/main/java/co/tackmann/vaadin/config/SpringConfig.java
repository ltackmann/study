package co.tackmann.vaadin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("co.tackmann.vaadin.service")
public class SpringConfig {
	@Bean
	public String getDefaultLanguage() {
		return "English";
	}
}
