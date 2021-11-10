package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;
import com.github.emailtohl.web.wechat.component.models.TextResponse;
import com.github.emailtohl.web.wechat.config.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by helei on 2021/11/9.
 */
class LastHandler extends WechatHandler {
  private static final Logger logger = LoggerFactory.getLogger(LastHandler.class);

  public LastHandler() {
    super(null);
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    logger.info("已达到责任链最后一层，未匹配类型");
    return new TextResponse(wechatRequest, Constant.TEXT);
  }
}
