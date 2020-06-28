//package com.sber.device.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//
//
//@Configuration
//@ComponentScan("com.sber.device")
//@PropertySource("classpath:application.properties")
//@EnableJpaRepositories
//@EnableTransactionManagement
//public class ApplicationConfig {
//
//
//    @Bean
//    public ObjectMapper jsonObjectMapper() {
//        return Jackson2ObjectMapperBuilder.json()
//                .modules(new JavaTimeModule())
//                .build();
//    }
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//}