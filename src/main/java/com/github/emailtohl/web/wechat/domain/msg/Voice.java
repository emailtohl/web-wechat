package com.github.emailtohl.web.wechat.domain.msg;

import java.io.Serializable;

public class Voice implements Serializable {
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