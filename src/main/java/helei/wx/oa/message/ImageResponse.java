package helei.wx.oa.message;

import helei.wx.oa.OAConst;

public class ImageResponse extends OAResponse {
  private Image Image;
  /**
   * 图片链接
   */
  private String PicUrl;
  /**
   * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
   */
  private Long MediaId;
  /**
   * 消息id，64位整型
   */
  private Long MsgId;

  public ImageResponse(OARequest request) {
    super(OAConst.IMAGE, request);
  }

  public Image getImage() {
    return Image;
  }

  public void setImage(Image image) {
    this.Image = image;
  }

  public String getPicUrl() {
    return PicUrl;
  }

  public void setPicUrl(String picUrl) {
    this.PicUrl = picUrl;
  }

  public Long getMediaId() {
    return MediaId;
  }

  public void setMediaId(Long mediaId) {
    this.MediaId = mediaId;
  }

  @Override
  public String toString() {
    return "ImageMsg [Image=" + Image + ", PicUrl=" + PicUrl + ", MediaId=" + MediaId + ", MsgId=" + MsgId
        + ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
        + ", MsgType=" + MsgType + "]";
  }
}
