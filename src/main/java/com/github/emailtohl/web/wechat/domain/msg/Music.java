package com.github.emailtohl.web.wechat.domain.msg;

import java.io.Serializable;

public class Music implements Serializable {
  /**
   * 音乐标题
   */
  private String Title;
  /**
   * 音乐描述
   */
  private String Description;
  /**
   * 音乐链接
   */
  private String MusicUrl;
  /**
   * 高品质音乐链接，wifi环境优先使用该链接播放音乐
   */
  private String HQMusicUrl;
  /**
   * 缩略图的媒体ID
   */
  private Long ThumbMediaId;

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

  public String getMusicUrl() {
    return MusicUrl;
  }

  public void setMusicUrl(String musicUrl) {
    this.MusicUrl = musicUrl;
  }

  public String getHQMusicUrl() {
    return HQMusicUrl;
  }

  public void setHQMusicUrl(String hQMusicUrl) {
    HQMusicUrl = hQMusicUrl;
  }

  public Long getThumbMediaId() {
    return ThumbMediaId;
  }

  public void setThumbMediaId(Long thumbMediaId) {
    this.ThumbMediaId = thumbMediaId;
  }

  @Override
  public String toString() {
    return "Music [Title=" + Title + ", Description=" + Description + ", MusicUrl=" + MusicUrl + ", HQMusicUrl="
        + HQMusicUrl + ", ThumbMediaId=" + ThumbMediaId + "]";
  }
}