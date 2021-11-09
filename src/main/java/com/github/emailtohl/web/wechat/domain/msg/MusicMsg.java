package com.github.emailtohl.web.wechat.domain.msg;

public class MusicMsg extends BaseMsg {
  private Music Music;

  public MusicMsg() {
    super("music");
  }

  public Music getMusic() {
    return Music;
  }

  public void setMusic(Music music) {
    this.Music = music;
  }

  @Override
  public String toString() {
    return "MusicMsg [Music=" + Music + ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName
        + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType + "]";
  }
}
