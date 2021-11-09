package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;
import com.github.emailtohl.web.wechat.component.models.TextRequest;
import com.github.emailtohl.web.wechat.component.models.TextResponse;
import com.github.emailtohl.web.wechat.config.Constant;
import org.springframework.util.StringUtils;

/**
 * Created by helei on 2021/11/9.
 */
class TextHandler extends WechatHandler {
  TextHandler(WechatHandler next) {
    super(next);
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    if (!(wechatRequest instanceof TextRequest req)) {
      return next.handle(wechatRequest);
    }
    logger.info("处理文本信息");
    TextResponse resp = new TextResponse(req, "没有请求消息");
    String content = req.getContent();
    if (StringUtils.hasText(content)) {
      switch (content) {
        case "1" -> resp.setContent("\r\n 你输入的是1， hello world");
        case "2" -> resp.setContent("\r\n 你输入的是2， 我的名字叫 foo");
        case "3" -> resp.setContent(Constant.REDIRECT_URI_GET_CODE + env.getProperty("appID")
            + "&redirect_uri=" + env.getProperty("redirect_uri")
            + "&response_type=code&scope=" + env.getProperty("scope")
            + "&state=STATE#wechat_redirect");
        default -> resp.setContent("\r\n 不识别标识码");
      }
    }
    return resp;
  }
}
