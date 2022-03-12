package helei.wx.oa.handlers;

import helei.wx.oa.OAConst;
import helei.wx.oa.message.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * Created by helei on 2021/11/9.
 */
class TextHandler implements OAHandler {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  Environment env;

  public TextHandler(Environment env) {
    this.env = env;
  }

  @Override
  public OAResponse handle(OARequest oaRequest) {
    TextRequest req = (TextRequest) oaRequest;
    logger.info("处理文本信息");
    TextResponse resp = new TextResponse(req, "没有请求消息");
    String content = req.getContent();
    if (StringUtils.hasText(content)) {
      switch (content) {
        case "1" -> resp.setContent("\r\n 你输入的是1， hello world");
        case "2" -> resp.setContent("\r\n 你输入的是2， 我的名字叫 foo");
        case "3" -> resp.setContent(OAConst.REDIRECT_URI_GET_CODE + env.getProperty("appID")
            + "&redirect_uri=" + env.getProperty("redirect_uri")
            + "&response_type=code&scope=" + env.getProperty("scope")
            + "&state=STATE#wechat_redirect");
        default -> resp.setContent("\r\n 不识别标识码");
      }
    }
    return resp;
  }
}
