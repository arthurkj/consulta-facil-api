package br.com.akj.api.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import br.com.caelum.stella.validation.CPFValidator;

@Configuration
public class AppConfig {

    private static final Locale PT_BR = new Locale("pt", "BR");

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:i18n/messages");
        source.setDefaultEncoding("UTF-8");
        source.setDefaultLocale(PT_BR);
        return source;
    }

    @Bean
    public CPFValidator stellaCpfvalidator() {
        return new CPFValidator();
    }
}
