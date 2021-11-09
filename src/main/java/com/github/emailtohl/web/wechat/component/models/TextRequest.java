package com.github.emailtohl.web.wechat.component.models;

import com.github.emailtohl.web.wechat.component.WechatRequest;

/**
 * Created by helei on 2021/11/9.
 */
public class TextRequest extends WechatRequest {
  private String Content;

  public TextRequest(String toUserName, String fromUserName, Long createTime, String wechatRequestType, String content) {
    super(toUserName, fromUserName, createTime, wechatRequestType);
    Content = content;
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }
}
