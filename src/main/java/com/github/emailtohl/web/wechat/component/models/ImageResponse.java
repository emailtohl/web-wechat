package com.github.emailtohl.web.wechat.component.models;

import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;

/**
 * Created by helei on 2021/11/10.
 */
public class ImageResponse extends WechatResponse {
  public ImageResponse(WechatRequest request, String MsgType) {
    super(request, MsgType);
  }
}