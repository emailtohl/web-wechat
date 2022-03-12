package helei.wx.oa.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static helei.wx.oa.OAConst.*;

/**
 * 获取用户信息的工具
 *
 * @author HeLei
 */
public class OauthService {
  private final ObjectMapper om = new ObjectMapper();
  private final Logger logger = LoggerFactory.getLogger(OauthService.class);
  private final HttpsClient httpsClient;
  private final TokenService accessTokenService;
  private final String appID;
  private final String appsecret;

  public OauthService(HttpsClient httpsClient, TokenService accessTokenService, String appID, String appsecret) {
    this.httpsClient = httpsClient;
    this.accessTokenService = accessTokenService;
    this.appID = appID;
    this.appsecret = appsecret;
  }

  /**
   * 获取网页授权凭证
   *
   * @param code
   * @return WeixinAouth2Token
   */
  public Oauth2Token getOauth2AccessToken(String code) {
    // 拼接请求地址
    String requestUrl = GET_WEBSITE_AUTHORIZED_ACCESS_TOKEN_URL.replace("APPID", appID)
        .replace("SECRET", appsecret)
        .replace("CODE", code);
    // 获取网页授权凭证
    String json = httpsClient.get(requestUrl);
    return fromJson(json, Oauth2Token.class);
  }

  /**
   * 刷新网页授权凭证
   *
   * @param refreshToken
   * @return WeixinAouth2Token
   */
  public Oauth2Token refreshOauth2AccessToken(String refreshToken) {
    // 拼接请求地址
    String requestUrl = REFRESH_WEBSITE_AUTHORIZED_ACCESS_TOKEN_URL.replace("APPID", appID)
        .replace("REFRESH_TOKEN", refreshToken);
    // 刷新网页授权凭证
    String json = httpsClient.get(requestUrl);
    return fromJson(json, Oauth2Token.class);
  }

  /**
   * 通过网页授权获取用户信息
   *
   * @param accessToken 网页授权接口调用凭证
   * @param openId      用户标识
   * @return SNSUserInfo
   */
  public SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
    // 拼接请求地址
    String requestUrl = SNS_USERINFO_URL.replace("ACCESS_TOKEN", accessToken)
        .replace("OPENID", openId);
    // 通过网页授权获取用户信息
    String json = httpsClient.get(requestUrl);
    return fromJson(json, SNSUserInfo.class);
  }

  /**
   * 获取用户信息
   *
   * @param openId 用户标识
   * @return OAUserInfo
   */
  public OAUserInfo getUserInfo(String openId) {
    // 拼接请求地址
    String requestUrl = USERINFO_URL.replace("ACCESS_TOKEN", accessTokenService.getAccessToken())
        .replace("OPENID", openId);
    // 获取用户信息
    String json = httpsClient.get(requestUrl);
    return fromJson(json, OAUserInfo.class);
  }

  private <T> T fromJson(String json, Class<T> clz) {
    try {
      return om.readValue(json, clz);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage(), e);
      throw new IllegalStateException(e);
    }
  }
}
