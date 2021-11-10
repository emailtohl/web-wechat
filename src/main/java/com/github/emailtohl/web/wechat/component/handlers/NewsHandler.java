package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;
import com.github.emailtohl.web.wechat.component.models.NewsRequest;
import com.github.emailtohl.web.wechat.component.models.NewsResponse;
import com.github.emailtohl.web.wechat.config.Constant;

/**
 * Created by helei on 2021/11/9.
 */
class NewsHandler extends WechatHandler {
  NewsHandler(WechatHandler next) {
    super(next);
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    if (!(wechatRequest instanceof NewsRequest req)) {
      return next.handle(wechatRequest);
    }
    NewsResponse resp = new NewsResponse(req, Constant.NEWS);
    // TODO
    return resp;
  }
}
