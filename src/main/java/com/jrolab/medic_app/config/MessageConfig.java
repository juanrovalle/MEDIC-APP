// package com.jrolab.medic_app.config;

// import java.beans.BeanProperty;
// import java.util.Locale;

// import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
// import org.springframework.context.MessageSource;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.support.ReloadableResourceBundleMessageSource;
// import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
// import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// @Configuration
// public class MessageConfig {
//     @Bean
//     public MessageSource messageSource() {
//         ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//         messageSource.setBasename("classpath:messages");
//         return messageSource;
//     }

//     @Bean
//     public LocalValidatorFactoryBean getValidator() {
//         LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//         bean.setValidationMessageSource(messageSource());
//         return bean;
//     }

//     @Bean
//     public LocaleResolver localeResolver() {
//         SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//         localeResolver.setDefaultLocale(Locale.ENGLISH);
//         return localeResolver();
//     }
// }
