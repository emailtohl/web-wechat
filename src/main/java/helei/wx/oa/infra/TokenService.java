package helei.wx.oa.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import static helei.wx.oa.OAConst.ACCESS_TOKEN;

/**
 * 定时刷新token的定时器
 *
 * @author HeLei
 */
public class TokenService {
  private final Logger logger = LoggerFactory.getLogger(TokenService.class);
  private final RestTemplate restTemplate;
  private final String appID;
  private final String appsecret;
  private volatile String accessToken;

  public TokenService(RestTemplate restTemplate, String appID, String appsecret) {
    this.restTemplate = restTemplate;
    this.appID = appID;
    this.appsecret = appsecret;
  }

  @Scheduled(fixedDelay = 7_200_000L - 2_000L, initialDelay = 0L)
  void update() {
    String requestUrl = ACCESS_TOKEN.replace("APPID", appID)
        .replace("APPSECRET", appsecret);
    /*
      在刷新过程中，中控服务器对外输出的依然是老access_token，此时公众平台后台会保证在刷新短时间内，新老access_token都可用，这保证了第三方业务的平滑过渡
     */
    AccessToken accessTokenBean = restTemplate.getForObject(requestUrl, AccessToken.class);
    if (accessTokenBean == null || accessTokenBean.getErrcode() != null) {
      logger.warn("token 获取失败： " + accessTokenBean);
    } else {
      accessToken = accessTokenBean.getAccess_token();
      logger.debug("access_token: " + accessToken);
    }
  }

  public String getAccessToken() {
    return accessToken;
  }

}
