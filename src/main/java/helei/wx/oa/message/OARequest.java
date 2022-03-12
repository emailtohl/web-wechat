package helei.wx.oa.message;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static helei.wx.oa.OAConst.*;

/**
 * Created by helei on 2021/11/9.
 */
public class OARequest {
  private static final Logger logger = LoggerFactory.getLogger(OARequest.class);
  protected final String MsgType;
  protected final long MsgId;
  protected final String ToUserName;
  protected final String FromUserName;
  protected final long CreateTime;

  public OARequest(Map<String, String> requestMap) {
    // 发送方帐号（open_id）
    FromUserName = requestMap.get(FROM_USER_NAME);
    // 公众帐号
    ToUserName = requestMap.get(TO_USER_NAME);
    // 消息类型
    MsgType = requestMap.get(MSG_TYPE);
    MsgId = Long.parseLong(requestMap.get(MSG_ID));
    CreateTime = parseLong(requestMap.get(CREATE_TIME));
  }

  /**
   * 将微信服务器发来的xml数据流解析成Map数据结构
   *
   * @param inputStream 输入流
   * @return Map数据结构
   */
  public static Map<String, String> extract(InputStream inputStream) {
    // 将解析结果存储在HashMap中
    Map<String, String> map = new HashMap<String, String>();
    try {
      // 读取输入流
      SAXReader reader = new SAXReader();
      Document document = reader.read(inputStream);
      // 得到xml根元素
      Element root = document.getRootElement();
      // 得到根元素的所有子节点
      List<Element> elementList = root.elements();
      // 遍历所有子节点
      for (Element e : elementList)
        map.put(e.getName(), e.getText());
    } catch (DocumentException e) {
      logger.error(e.getMessage(), e);
      throw new IllegalStateException(e);
    }
    return map;
  }

  /**
   * 将输入流中的Map数据结构解析成具体的公众号请求
   *
   * @param requestMap 输入流解析的Map数据
   * @return 具体的公众号请求
   */
  static OARequest create(Map<String, String> requestMap) {
    String msgType = requestMap.get(MSG_TYPE);
    return switch (msgType) {
      case TEXT -> {
        TextRequest req = new TextRequest(requestMap);
        req.setContent(requestMap.get(CONTENT));
        yield req;
      }
      case IMAGE -> new ImageRequest(requestMap);
      case LOCATION -> new LocationRequest(requestMap);
      case LINK -> new LinkRequest(requestMap);
      case VOICE -> new VoiceRequest(requestMap);
      case VIDEO -> new VideoRequest(requestMap);
      case NEWS -> new NewsRequest(requestMap);
      case EVENT -> {
        EventRequest req = new EventRequest(requestMap);
        req.setEvent(requestMap.get(EVENT_TYPE));
        req.setEventKey(requestMap.get(EVENT_KEY));
        req.setLatitude(requestMap.get(EVENT_LATITUDE));
        req.setLongitude(requestMap.get(EVENT_LONGITUDE));
        req.setPrecision(requestMap.get(EVENT_PRECISION));
        yield req;
      }
      default -> new OARequest(requestMap);
    };
  }

  /**
   * 将微信服务器的xml输入流解析成具体的公众号请求
   *
   * @param inputStream 微信服务器的xml输入流
   * @return 具体的公众号请求
   */
  public static OARequest from(InputStream inputStream) {
    Map<String, String> requestMap = extract(inputStream);
    return create(requestMap);
  }

  /**
   * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
   *
   * @param createTime
   * @return
   */
  public static String formatTime(String createTime) {
    // 将微信传入的CreateTime转换成long类型，再乘以1000
    long msgCreateTime = Long.parseLong(createTime) * 1000L;
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(msgCreateTime));
  }

  private long parseLong(String num) {
    if (num == null) {
      return 0;
    }
    try {
      return Long.parseLong(num);
    } catch (NumberFormatException e) {
      logger.error(e.getMessage(), e);
      return 0;
    }
  }

  public OAResponse handle(OAHandler handler) {
    return handler.handle(this);
  }

  public String getMsgType() {
    return MsgType;
  }

  public long getMsgId() {
    return MsgId;
  }

  public String getToUserName() {
    return ToUserName;
  }

  public String getFromUserName() {
    return FromUserName;
  }

  public long getCreateTime() {
    return CreateTime;
  }
}
