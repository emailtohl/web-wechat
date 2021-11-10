package com.github.emailtohl.web.wechat.component.models;

import com.github.emailtohl.web.wechat.component.WechatRequest;

/**
 * Created by helei on 2021/11/10.
 */
public class ImageRequest extends WechatRequest {
  /**
   * Instantiates a new Wechat request.
   *
   * @param toUserName        the to user name
   * @param fromUserName      the from user name
   * @param createTime        the create time
   * @param wechatRequestType the wechat request type
   */
  public ImageRequest(String toUserName, String fromUserName, Long createTime, String wechatRequestType) {
    super(toUserName, fromUserName, createTime, wechatRequestType);
  }
}
