package helei.wx.oa.message;

public class Voice {
  /**
   * 发送的语音的媒体ID
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
    return "Voice [MediaId=" + MediaId + "]";
  }
}