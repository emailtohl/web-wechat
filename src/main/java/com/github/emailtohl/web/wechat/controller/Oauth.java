package com.github.emailtohl.web.wechat.controller;

import com.github.emailtohl.web.wechat.domain.auth.SNSUserInfo;
import com.github.emailtohl.web.wechat.domain.auth.WeixinOauth2Token;
import com.github.emailtohl.web.wechat.util.OauthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 微信菜单跳转到本应用时，获取用户基本信息
 *
 * @author HeLei
 */
@Controller
@RequestMapping(value = "oauth")
public class Oauth {
  @Autowired
  private OauthUtil oauthUtil;

  @RequestMapping(method = GET)
  public void forwardYyy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 用户同意授权后，能获取到code
    String code = request.getParameter("code");
    String state = request.getParameter("state");
    // 用户同意授权
    if (!"authdeny".equals(code)) {
      // 获取网页授权access_token
      WeixinOauth2Token weixinOauth2Token = oauthUtil.getOauth2AccessToken(code);
      // 网页授权接口访问凭证
      String accessToken = weixinOauth2Token.getAccess_token();
      // 用户标识
      String openId = weixinOauth2Token.getOpenid();
      // 获取用户信息
      SNSUserInfo snsUserInfo = oauthUtil.getSNSUserInfo(accessToken, openId);

      // 设置要传递的参数
      request.setAttribute("snsUserInfo", snsUserInfo);
    }
    // 跳转到yyyUserPage.jsp
    switch (state) {
      case "yyy":
        request.getRequestDispatcher("WEB-INF/jsp/yyy/yyyUserPage.jsp").forward(request, response);
        break;
      default:
    }
  }
}
