package com.github.emailtohl.web.wechat.component.models;

import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;

/**
 * Created by helei on 2021/11/9.
 */
public class VoiceResponse extends WechatResponse {

  public VoiceResponse(WechatRequest request, String MsgType) {
    super(request, MsgType);
  }
}
