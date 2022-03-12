package helei.wx.oa.message;

import helei.wx.oa.OAConst;

public class MusicResponse extends OAResponse {
  private Music Music;

  public MusicResponse(OARequest request) {
    super(OAConst.MUSIC, request);
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
