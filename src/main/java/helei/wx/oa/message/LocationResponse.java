package helei.wx.oa.message;

import helei.wx.oa.OAConst;

public class LocationResponse extends OAResponse {
  /**
   * 地理位置维度
   */
  private Double Location_X;
  /**
   * 地理位置经度
   */
  private Double Location_Y;
  /**
   * 地图缩放大小
   */
  private Integer Scale;
  /**
   * 地理位置信息
   */
  private String Label;

  public LocationResponse(OARequest request) {
    super(OAConst.LOCATION, request);
  }

  public Double getLocation_X() {
    return Location_X;
  }

  public void setLocation_X(Double location_X) {
    this.Location_X = location_X;
  }

  public Double getLocation_Y() {
    return Location_Y;
  }

  public void setLocation_Y(Double location_Y) {
    this.Location_Y = location_Y;
  }

  public Integer getScale() {
    return Scale;
  }

  public void setScale(Integer scale) {
    this.Scale = scale;
  }

  public String getLabel() {
    return Label;
  }

  public void setLabel(String label) {
    this.Label = label;
  }

  @Override
  public String toString() {
    return "LocationMsg [Location_X=" + Location_X + ", Location_Y=" + Location_Y + ", Scale=" + Scale + ", Label="
        + Label + ", MsgId=" + MsgId + ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName
        + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType + "]";
  }
}
