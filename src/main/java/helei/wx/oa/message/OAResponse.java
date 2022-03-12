package helei.wx.oa.message;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

/**
 * Created by helei on 2021/11/9.
 */
public class OAResponse {
  private static final XStream XSTREAM;

  static {
    XSTREAM = new XStream(new XppDriver() {
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
    XSTREAM.alias("xml", OAResponse.class);
    XSTREAM.alias("xml", ImageResponse.class);
    XSTREAM.alias("xml", MusicResponse.class);
    XSTREAM.alias("xml", ArticleResponse.class);
    XSTREAM.alias("xml", TextResponse.class);
    XSTREAM.alias("xml", VideoResponse.class);
    XSTREAM.alias("xml", VoiceResponse.class);
    XSTREAM.alias("item", Article.class);
  }

  protected final String MsgType;
  protected final long MsgId;
  protected final String ToUserName;
  protected final String FromUserName;
  protected final long CreateTime;

  public OAResponse(String msgType, OARequest request) {
    this(msgType, request.getMsgId(), request.getFromUserName(), request.getToUserName());
  }

  public OAResponse(String msgType, long MsgId, String toUserName, String fromUserName) {
    this.MsgType = msgType;
    this.MsgId = MsgId;
    this.ToUserName = toUserName;
    this.FromUserName = fromUserName;
    this.CreateTime = System.currentTimeMillis();
  }

  public String toXML() {
    return XSTREAM.toXML(this);
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
