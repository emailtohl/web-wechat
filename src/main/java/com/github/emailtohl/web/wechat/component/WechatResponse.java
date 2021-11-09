package com.github.emailtohl.web.wechat.component;

/**
 * Created by helei on 2021/11/9.
 */
public class WechatResponse {
  protected String ToUserName;
  protected String FromUserName;
  protected Long CreateTime;
  protected String MsgType;

  public WechatResponse(WechatRequest request, String MsgType) {
    this(request.getToUserName(), request.getFromUserName(), request.getCreateTime(), MsgType);
  }

  public WechatResponse(String toUserName, String fromUserName, Long createTime, String msgType) {
    ToUserName = toUserName;
    FromUserName = fromUserName;
    CreateTime = createTime;
    MsgType = msgType;
  }

  public String getToUserName() {
    return ToUserName;
  }

  public void setToUserName(String toUserName) {
    ToUserName = toUserName;
  }

  public String getFromUserName() {
    return FromUserName;
  }

  public void setFromUserName(String fromUserName) {
    FromUserName = fromUserName;
  }

  public Long getCreateTime() {
    return CreateTime;
  }

  public void setCreateTime(Long createTime) {
    CreateTime = createTime;
  }

  public String getMsgType() {
    return MsgType;
  }

  public void setMsgType(String msgType) {
    MsgType = msgType;
  }
}
