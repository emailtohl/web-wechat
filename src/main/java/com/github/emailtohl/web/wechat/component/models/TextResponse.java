package com.github.emailtohl.web.wechat.component.models;

import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;
import com.github.emailtohl.web.wechat.config.Constant;

/**
 * Created by helei on 2021/11/9.
 */
public class TextResponse extends WechatResponse {

  private String Content;

  public TextResponse(WechatRequest request, String content) {
    super(request, Constant.TEXT);
    this.Content = content;
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }
}
