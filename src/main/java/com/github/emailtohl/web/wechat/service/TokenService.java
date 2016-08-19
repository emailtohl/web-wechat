package com.github.emailtohl.web.wechat.service;

import com.github.emailtohl.web.wechat.domain.auth.WeixinOauth2Token;

/**
 * 定期刷新access_token，并提供access_token
 * 
 * @author helei
 *
 */
public interface TokenService {
	/**
	 * @return 提供access_token
	 */
	String getAccessToken();
	
	WeixinOauth2Token getOauth2AccessToken();
}
