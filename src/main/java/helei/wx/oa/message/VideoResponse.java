package helei.wx.oa.message;

import helei.wx.oa.OAConst;

public class VideoResponse extends OAResponse {
  private Video Video;
  /**
   * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
   */
  private Long MediaId;
  /**
   * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
   */
  private Long ThumbMediaId;

  public VideoResponse(OARequest request) {
    super(OAConst.VIDEO, request);
  }

  public Video getVideo() {
    return Video;
  }

  public void setVideo(Video video) {
    this.Video = video;
  }

  public Long getMediaId() {
    return MediaId;
  }

  public void setMediaId(Long mediaId) {
    this.MediaId = mediaId;
  }

  public Long getThumbMediaId() {
    return ThumbMediaId;
  }

  public void setThumbMediaId(Long thumbMediaId) {
    this.ThumbMediaId = thumbMediaId;
  }

  @Override
  public String toString() {
    return "VideoMsg [Video=" + Video + ", MediaId=" + MediaId + ", ThumbMediaId=" + ThumbMediaId + ", MsgId="
        + MsgId + ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
        + ", MsgType=" + MsgType + "]";
  }
}
