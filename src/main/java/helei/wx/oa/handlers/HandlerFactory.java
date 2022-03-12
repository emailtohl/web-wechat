package helei.wx.oa.handlers;

import helei.wx.oa.message.OAHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static helei.wx.oa.OAConst.*;

/**
 * 根据msgType选择自定义处理器
 * <p>
 * Created by helei on 2021/11/9.
 */
@Component
public class HandlerFactory {
  @Autowired
  Environment env;

  public OAHandler create(String msgType) {
    return switch (msgType) {
      case IMAGE -> new ImageHandler();
      case LOCATION -> new LocationHandler();
      case LINK -> new LinkHandler();
      case VOICE -> new VoiceHandler();
      case VIDEO -> new VideoHandler();
      case NEWS -> new NewsHandler();
      case EVENT -> new EventHandler();
      default -> new TextHandler(env);
    };
  }
}
