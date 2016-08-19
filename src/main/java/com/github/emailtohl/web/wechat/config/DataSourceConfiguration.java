package com.github.emailtohl.web.wechat.config;

import java.beans.PropertyVetoException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 数据源配置
 * @author HeLei
 */
@Configuration
@PropertySource({ "classpath:database.properties", "classpath:wechat.properties" })
public class DataSourceConfiguration {
	@Inject
	Environment env;

	@Value("${local.driverClassName}")
	String driverClassName;
	@Value("${local.url}")
	String url;
	@Value("${local.username}")
	String username;
	@Value("${local.password}")
	String password;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource c3p0DataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(driverClassName);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		return dataSource;
	}
}
