package helei.wx.oa.message;

public class Image {
  /**
   * 发送的图片的媒体ID
   */
  private Long MediaId;

  public Long getMediaId() {
    return MediaId;
  }

  public void setMediaId(Long mediaId) {
    this.MediaId = mediaId;
  }

  @Override
  public String toString() {
    return "Image [MediaId=" + MediaId + "]";
  }
}