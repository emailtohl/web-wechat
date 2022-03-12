package helei.wx.oa.message;

import helei.wx.oa.OAConst;

public class TextResponse extends OAResponse {
  /**
   * 文本消息内容
   */
  private String Content;

  public TextResponse(OARequest request) {
    super(OAConst.TEXT, request);
  }

  public TextResponse(OARequest request, String content) {
    this(request);
    this.Content = content;
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }

  @Override
  public String toString() {
    return "TextMsg [Content=" + Content + ", MsgId=" + MsgId + ", ToUserName=" + ToUserName + ", FromUserName="
        + FromUserName + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType + "]";
  }
}
