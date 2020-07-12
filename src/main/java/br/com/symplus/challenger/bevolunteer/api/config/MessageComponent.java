package br.com.symplus.challenger.bevolunteer.api.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageComponent {
	
	private static ResourceBundleMessageSource messageSource;
	
	@Autowired
	public MessageComponent(ResourceBundleMessageSource messageSource) {
		MessageComponent.messageSource = messageSource;
	}
	
	public static String getMessage(String code, String ...args) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, args, locale);
	}

}
