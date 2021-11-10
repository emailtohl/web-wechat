package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;
import com.github.emailtohl.web.wechat.component.models.LinkRequest;
import com.github.emailtohl.web.wechat.component.models.LinkResponse;
import com.github.emailtohl.web.wechat.config.Constant;

/**
 * Created by helei on 2021/11/9.
 */
class LinkHandler extends WechatHandler {
  LinkHandler(WechatHandler next) {
    super(next);
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    if (!(wechatRequest instanceof LinkRequest req)) {
      return next.handle(wechatRequest);
    }
    LinkResponse resp = new LinkResponse(req, Constant.LINK);
    // TODO
    return resp;
  }
}
