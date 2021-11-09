package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatRequestType;
import com.github.emailtohl.web.wechat.component.WechatResponse;

/**
 * Created by helei on 2021/11/9.
 */
class NewsHandler extends WechatHandler {
  NewsHandler(WechatHandler next) {
    super(next);
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    if (WechatRequestType.news != wechatRequest.getType()) {
      return next.handle(wechatRequest);
    }
    return null;
  }
}
