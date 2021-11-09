package com.github.emailtohl.web.wechat.service.impl;

import com.github.emailtohl.web.wechat.config.Constant;
import com.github.emailtohl.web.wechat.domain.auth.AccessToken;
import com.github.emailtohl.web.wechat.domain.auth.WeixinOauth2Token;
import com.github.emailtohl.web.wechat.service.TokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 定时刷新token的定时器
 *
 * @author HeLei
 */
@Service
public class TokenServiceImpl implements TokenService {
  private final static Logger log = LogManager.getLogger();
  /**
   * 在刷新过程中，中控服务器对外输出的依然是老access_token，此时公众平台后台会保证在刷新短时间内，新老access_token都可用，这保证了第三方业务的平滑过渡
   */
  private volatile AccessToken accessTokenBean;
  private volatile String accessToken;

  @Autowired
  @Qualifier("httpsForWechatRestTemplate")
  private RestTemplate restTemplate;
  @Value("${appID}")
  private String appID;
  @Value("${appsecret}")
  private String appsecret;

  @Scheduled(fixedDelay = 7_200_000L - 2_000L, initialDelay = 0L)
  private void update() {
    String requestUrl = Constant.ACCESS_TOKEN.replace("APPID", appID)
        .replace("APPSECRET", appsecret);
    accessTokenBean = restTemplate.getForObject(requestUrl, AccessToken.class);
    if (accessTokenBean.getErrcode() != null) {
      log.warn("token 获取失败： " + accessTokenBean);
    } else {
      accessToken = accessTokenBean.getAccess_token();
      log.debug("access_token: " + accessToken);
    }
  }

  @Override
  public String getAccessToken() {
    return accessToken;
  }

  @Override
  public WeixinOauth2Token getOauth2AccessToken() {
    return null;
  }
}
