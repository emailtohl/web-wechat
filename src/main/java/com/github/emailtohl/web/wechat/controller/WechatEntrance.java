package com.github.emailtohl.web.wechat.controller;

import com.github.emailtohl.web.wechat.domain.msg.BaseMsg;
import com.github.emailtohl.web.wechat.service.WechatService;
import com.github.emailtohl.web.wechat.util.MessageUtil;
import com.github.emailtohl.web.wechat.util.SignUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static com.github.emailtohl.web.wechat.config.Constant.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 微信服务器访问入口
 *
 * @author HeLei
 */
@Controller
@RequestMapping(value = "entrance")
public class WechatEntrance {
  private final Logger logger = LogManager.getLogger();
  private WechatService textService, imageService, locationService, linkService, voiceService, eventService;
  @Autowired
  private SignUtil signUtil;
  @Autowired
  private MessageUtil messageUtil;

  @RequestMapping(method = GET)
  @ResponseBody
  public String approve(String signature, String timestamp, String nonce, String echostr) {
    if (signUtil.checkSignature(signature, timestamp, nonce)) {
      logger.info("验证成功: " + echostr);
      return echostr;
    } else {
      return "";
    }
  }

  @RequestMapping(method = POST, produces = "text/plain; charset=UTF-8")
  @ResponseBody
  public String wechatService(HttpServletRequest req) throws IOException {
    ServletInputStream in = req.getInputStream();
    Map<String, String> requestMap;
    try {
      requestMap = messageUtil.parseXml(in);
    } catch (DocumentException e) {
      throw new RuntimeException("微信服务器发来的xml解析有错", e);
    }
    // 发送方帐号（open_id）
    String fromUserName = requestMap.get(FROM_USER_NAME);
    // 公众帐号
    String toUserName = requestMap.get(TO_USER_NAME);
    // 消息类型
    String msgType = requestMap.get(MSG_TYPE);
    // 声明返回的消息
    BaseMsg respMsg = switch (msgType) {
      case TEXT -> textService.echo(requestMap);
      case IMAGE -> imageService.echo(requestMap);
      case LOCATION -> locationService.echo(requestMap);
      case LINK -> linkService.echo(requestMap);
      case VOICE -> voiceService.echo(requestMap);
      case NEWS -> textService.echo(requestMap);
      case EVENT ->
          // 事件类型
          eventService.echo(requestMap);
      default -> new BaseMsg("");
    };
    respMsg.setToUserName(fromUserName);
    respMsg.setFromUserName(toUserName);
    respMsg.setCreateTime(new Date().getTime());
    return messageUtil.messageToXml(respMsg);
  }

  public WechatService getTextService() {
    return textService;
  }

  @Autowired
  public void setTextService(WechatService textService) {
    this.textService = textService;
  }

  public WechatService getImageService() {
    return imageService;
  }

  @Autowired
  public void setImageService(WechatService imageService) {
    this.imageService = imageService;
  }

  public WechatService getLocationService() {
    return locationService;
  }

  @Autowired
  public void setLocationService(WechatService locationService) {
    this.locationService = locationService;
  }

  public WechatService getLinkService() {
    return linkService;
  }

  @Autowired
  public void setLinkService(WechatService linkService) {
    this.linkService = linkService;
  }

  public WechatService getVoiceService() {
    return voiceService;
  }

  @Autowired
  public void setVoiceService(WechatService voiceService) {
    this.voiceService = voiceService;
  }

  public WechatService getEventService() {
    return eventService;
  }

  @Autowired
  public void setEventService(WechatService eventService) {
    this.eventService = eventService;
  }

}
