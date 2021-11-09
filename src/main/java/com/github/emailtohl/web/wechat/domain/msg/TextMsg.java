package com.github.emailtohl.web.wechat.domain.msg;

public class TextMsg extends BaseMsg {
  /**
   * 文本消息内容
   */
  private String Content;
  /**
   * 消息id，64位整型
   */
  private Long MsgId;

  public TextMsg() {
    super("text");
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    this.Content = content;
  }

  public Long getMsgId() {
    return MsgId;
  }

  public void setMsgId(Long msgId) {
    this.MsgId = msgId;
  }

  @Override
  public String toString() {
    return "TextMsg [Content=" + Content + ", MsgId=" + MsgId + ", ToUserName=" + ToUserName + ", FromUserName="
        + FromUserName + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType + "]";
  }
}
