package com.github.emailtohl.web.wechat.boot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.emailtohl.web.wechat.config.RootContextConfiguration;
/**
 * 获取spring容器，并保存在静态域中，可随时访问
 * @author HeLei
 */
public final class SpringUtil {
	private SpringUtil() {
	}

	public static final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
			RootContextConfiguration.class);
}
