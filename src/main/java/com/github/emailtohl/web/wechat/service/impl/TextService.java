package com.github.emailtohl.web.wechat.service.impl;

import com.github.emailtohl.web.wechat.config.Constant;
import com.github.emailtohl.web.wechat.domain.msg.BaseMsg;
import com.github.emailtohl.web.wechat.domain.msg.TextMsg;
import com.github.emailtohl.web.wechat.service.WechatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 文本处理器
 *
 * @author HeLei
 */
@Service
public class TextService implements WechatService {
  private static final Logger logger = LogManager.getLogger();
  @Autowired
  Environment env;

  public BaseMsg echo(Map<String, String> requestMap) {
    logger.info("处理文本信息");
    TextMsg result = new TextMsg();
    String content = requestMap.get("Content");
    if (content == null || content.isEmpty()) {
      result.setContent("没有请求消息");
    } else {
      switch (content) {
        case "1":
          result.setContent("\r\n 你输入的是1， hello world");
          break;
        case "2":
          result.setContent("\r\n 你输入的是2， 我的名字叫 foo");
          break;
        case "3":
          result.setContent(Constant.REDIRECT_URI_GET_CODE + env.getProperty("appID")
              + "&redirect_uri=" + env.getProperty("redirect_uri")
              + "&response_type=code&scope=" + env.getProperty("scope")
              + "&state=STATE#wechat_redirect");
          break;
        default:
          result.setContent("\r\n 不识别标识码");
      }
    }
    return result;
  }

}
