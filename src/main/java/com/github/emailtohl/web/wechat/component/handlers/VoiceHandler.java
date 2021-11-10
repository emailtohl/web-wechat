package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;
import com.github.emailtohl.web.wechat.component.models.VoiceRequest;
import com.github.emailtohl.web.wechat.component.models.VoiceResponse;
import com.github.emailtohl.web.wechat.config.Constant;

/**
 * Created by helei on 2021/11/9.
 */
class VoiceHandler extends WechatHandler {
  public VoiceHandler(WechatHandler next) {
    super(next);
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    if (!(wechatRequest instanceof VoiceRequest req)) {
      return next.handle(wechatRequest);
    }
    VoiceResponse resp = new VoiceResponse(req, Constant.VOICE);
    // TODO
    return resp;
  }
}
