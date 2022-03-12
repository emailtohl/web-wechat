package helei.wx.oa.message;

public class Video {
  /**
   * 通过素材管理接口上传多媒体文件，得到的id
   */
  private Long MediaId;
  /**
   * 视频消息的标题
   */
  private String Title;
  /**
   * 视频消息的描述
   */
  private String Description;

  public Long getMediaId() {
    return MediaId;
  }

  public void setMediaId(Long mediaId) {
    this.MediaId = mediaId;
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

  @Override
  public String toString() {
    return "Video [MediaId=" + MediaId + ", Title=" + Title + ", Description=" + Description + "]";
  }
}