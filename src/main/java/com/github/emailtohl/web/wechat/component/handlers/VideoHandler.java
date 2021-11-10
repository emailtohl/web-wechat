package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;
import com.github.emailtohl.web.wechat.component.models.VideoRequest;
import com.github.emailtohl.web.wechat.component.models.VideoResponse;
import com.github.emailtohl.web.wechat.config.Constant;

/**
 * Created by helei on 2021/11/9.
 */
class VideoHandler extends WechatHandler {
  VideoHandler(WechatHandler next) {
    super(next);
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    if (!(wechatRequest instanceof VideoRequest req)) {
      return next.handle(wechatRequest);
    }
    VideoResponse resp = new VideoResponse(req, Constant.VIDEO);
    // TODO
    return resp;
  }
}
