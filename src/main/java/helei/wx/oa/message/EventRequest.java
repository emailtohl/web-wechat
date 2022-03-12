package helei.wx.oa.message;

import java.util.Map;

/**
 * Created by helei on 2021/11/9.
 */
public class EventRequest extends OARequest {
  private String Event;
  /**
   * 事件KEY值
   */
  private String EventKey;
  /**
   * 地理位置纬度
   */
  private String Latitude;
  /**
   * 地理位置经度
   */
  private String Longitude;
  /**
   * 地理位置精度
   */
  private String Precision;

  public EventRequest(Map<String, String> requestMap) {
    super(requestMap);
  }

  public String getEvent() {
    return Event;
  }

  public void setEvent(String event) {
    Event = event;
  }

  public String getEventKey() {
    return EventKey;
  }

  public void setEventKey(String eventKey) {
    EventKey = eventKey;
  }

  public String getLatitude() {
    return Latitude;
  }

  public void setLatitude(String latitude) {
    Latitude = latitude;
  }

  public String getLongitude() {
    return Longitude;
  }

  public void setLongitude(String longitude) {
    Longitude = longitude;
  }

  public String getPrecision() {
    return Precision;
  }

  public void setPrecision(String precision) {
    Precision = precision;
  }
}
