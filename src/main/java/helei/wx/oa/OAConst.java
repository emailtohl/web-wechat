package helei.wx.oa;

/**
 * 存储微信公众号定义的常量
 *
 * @author helei
 */
public interface OAConst {
  /**
   * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
   */
  String SIGNATURE = "signature";
  /**
   * 时间戳
   */
  String TIMESTAMP = "timestamp";
  /**
   * 随机数
   */
  String NONCE = "nonce";
  /**
   * 随机字符串
   */
  String ECHOSTR = "echostr";

  /**
   * 开发者微信号
   */
  String TO_USER_NAME = "ToUserName";
  /**
   * 发送方帐号（一个OpenID）
   */
  String FROM_USER_NAME = "FromUserName";
  /**
   * 消息创建时间 （整型）
   */
  String CREATE_TIME = "CreateTime";
  /**
   * text
   */
  String MSG_TYPE = "MsgType";
  /**
   * 文本消息内容
   */
  String CONTENT = "Content";
  /**
   * 消息id，64位整型
   */
  String MSG_ID = "MsgId";

  /**
   * 消息类型： 文本
   */
  String TEXT = "text";
  /**
   * 消息类型：图片
   */
  String IMAGE = "image";
  /**
   * 消息类型：语音
   */
  String VOICE = "voice";
  /**
   * 消息类型：视频
   */
  String VIDEO = "video";
  /**
   * 消息类型：音乐
   */
  String MUSIC = "music";
  /**
   * 消息类型：小视频
   */
  String SHORTVIDEO = "shortvideo";
  /**
   * 消息类型：地理位置
   */
  String LOCATION = "location";
  /**
   * 消息类型：链接
   */
  String LINK = "link";

  /**
   * 消息类型：地理位置维度
   */
  String MSG_LOCATION_X = "Location_X";
  /**
   * 消息类型：地理位置经度
   */
  String MSG_LOCATION_Y = "Location_Y";
  /**
   * 消息类型：地图缩放大小
   */
  String MSG_SCALE = "Scale";
  /**
   * 消息类型：地理位置信息
   */
  String MSG_LABEL = "Label";

  /**
   * 图文消息
   */
  String NEWS = "news";

  /**
   * 事件
   */
  String EVENT = "event";
  /**
   * 事件类型
   */
  String EVENT_TYPE = "Event";
  /**
   * 事件KEY值
   */
  String EVENT_KEY = "EventKey";
  /**
   * 事件类型：订阅
   */
  String EVENT_SUBSCRIBE = "subscribe";
  /**
   * 事件类型：取消订阅
   */
  String EVENT_UNSUBSCRIBE = "unsubscribe";
  /**
   * 事件类型：扫描
   */
  String EVENT_SCAN = "SCAN";
  /**
   * 事件类型：上报地理位置事件
   */
  String EVENT_LOCATION = "LOCATION";
  /**
   * 事件类型：自定义菜单点击
   */
  String EVENT_CLICK = "CLICK";
  /**
   * 事件类型：点击菜单跳转链接时的事件推送
   */
  String EVENT_VIEW = "VIEW";

  /**
   * 二维码的ticket，可用来换取二维码图片
   */
  String EVENT_TICKET = "Ticket";
  /**
   * 地理位置纬度
   */
  String EVENT_LATITUDE = "Latitude";
  /**
   * 地理位置经度
   */
  String EVENT_LONGITUDE = "Longitude";
  /**
   * 地理位置精度
   */
  String EVENT_PRECISION = "Precision";

  /**
   * 获取access token
   */
  String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

  /**
   * 这是引导用户点击的链接地址，用户若点击此地址，则微信后台会将code为参数的请求发送到REDIRECT_URI地址中
   * 用户同意授权，获取code
   */
  String REDIRECT_URI_GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

  /**
   * 通过code换取网页授权access_token
   */
  String GET_WEBSITE_AUTHORIZED_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

  /**
   * 刷新网页凭证
   */
  String REFRESH_WEBSITE_AUTHORIZED_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

  /**
   * 通过网页授权获取用户信息
   */
  String SNS_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";

  /**
   * 通过网页授权获取用户信息
   */
  String USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";

  /**
   * 自定义菜单创建接口
   */
  String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
  /**
   * 自定义菜单查询接口
   */
  String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
  /**
   * 自定义菜单删除接口
   */
  String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";


}
