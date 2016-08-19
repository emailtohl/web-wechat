package com.github.emailtohl.web.wechat.test;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.github.emailtohl.web.wechat.boot.SpringUtil;
import com.github.emailtohl.web.wechat.config.Constant;
import com.github.emailtohl.web.wechat.domain.auth.AccessToken;

public class GetTokenTest {
	AnnotationConfigApplicationContext ctx;
	
	@Before
	public void setUp() {
		ctx = SpringUtil.ctx;
	}
	
	@Test
	public void testGetToken() throws FileNotFoundException {
		Environment env = ctx.getBean(Environment.class);
		RestTemplate httpsRestTemplate = ctx.getBean(RestTemplate.class);
		String requestUrl = Constant.ACCESS_TOKEN.replace("APPID", env.getProperty("appID")).replace("APPSECRET", env.getProperty("appsecret"));
		AccessToken token = httpsRestTemplate.getForObject(requestUrl, AccessToken.class);
		System.out.println(token.getAccess_token());
		Assert.assertNotNull(token);
		Assert.assertNull(token.getErrcode());
	}
}
