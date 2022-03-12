package helei.wx.oa.message;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将消息序列化为xml的服务
 *
 * @author HeLei
 */
@Service
public class MessageService {
  private final Logger logger = LoggerFactory.getLogger(MessageService.class);
  /**
   * 扩展xstream，使其支持CDATA块
   */
  private final XStream xstream;
  /**
   * 判断是否是QQ表情
   *
   * @param content
   * @return
   */
  // 判断QQ表情的正则表达式
  private final Pattern p = Pattern.compile("/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>");

  public MessageService() {
    xstream = createXStream();
  }

  /**
   * 解析微信发来的请求（XML）
   *
   * @return
   * @throws Exception
   */
  public Map<String, String> parseXml(InputStream inputStream) {
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
   * 消息对象转换成xml
   *
   * @return
   */
  public String messageToXml(OAResponse msg) {
    return xstream.toXML(msg);
  }

  /**
   * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
   *
   * @param createTime
   * @return
   */
  public String formatTime(String createTime) {
    // 将微信传入的CreateTime转换成long类型，再乘以1000
    long msgCreateTime = Long.parseLong(createTime) * 1000L;
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(msgCreateTime));
  }

  public boolean isQqFace(String content) {
    boolean result = false;
    Matcher m = p.matcher(content);
    if (m.matches()) {
      result = true;
    }
    return result;
  }

  /**
   * emoji表情转换(hex -> utf-16)
   *
   * @param hexEmoji
   * @return
   */
  public String emoji(int hexEmoji) {
    return String.valueOf(Character.toChars(hexEmoji));
  }

  private XStream createXStream() {
    final XStream xstream;
    xstream = new XStream(new XppDriver() {
      public HierarchicalStreamWriter createWriter(Writer out) {
        return new PrettyPrintWriter(out) {
          // 对所有xml节点的转换都增加CDATA标记
          final boolean cdata = true;

          public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
            super.startNode(name, clazz);
          }

          protected void writeText(QuickWriter writer, String text) {
            if (cdata) {
              writer.write("<![CDATA[");
              writer.write(text);
              writer.write("]]>");
            } else {
              writer.write(text);
            }
          }
        };
      }
    });
    xstream.alias("xml", OAResponse.class);
    xstream.alias("xml", ImageResponse.class);
    xstream.alias("xml", MusicResponse.class);
    xstream.alias("xml", ArticleResponse.class);
    xstream.alias("xml", TextResponse.class);
    xstream.alias("xml", VideoResponse.class);
    xstream.alias("xml", VoiceResponse.class);
    xstream.alias("item", Article.class);
    return xstream;
  }
}
