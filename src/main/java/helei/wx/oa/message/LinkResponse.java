package helei.wx.oa.message;

import helei.wx.oa.OAConst;

public class LinkResponse extends OAResponse {
  /**
   * 消息标题
   */
  private String Title;
  /**
   * 消息描述
   */
  private String Description;
  /**
   * 消息链接
   */
  private String Url;
  /**
   * 消息id，64位整型
   */
  private Long MsgId;

  public LinkResponse(OARequest request) {
    super(OAConst.LINK, request);
  }


  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    this.Title = title;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    this.Description = description;
  }

  public String getUrl() {
    return Url;
  }

  public void setUrl(String url) {
    this.Url = url;
  }

  @Override
  public String toString() {
    return "LinkMsg [Title=" + Title + ", Description=" + Description + ", Url=" + Url + ", MsgId=" + MsgId
        + ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
        + ", MsgType=" + MsgType + "]";
  }
}
