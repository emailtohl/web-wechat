package helei.wx.oa.handlers;

import helei.wx.oa.message.OAHandler;
import helei.wx.oa.message.OARequest;
import helei.wx.oa.message.OAResponse;
import helei.wx.oa.message.TextResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by helei on 2021/11/9.
 */
class ImageHandler implements OAHandler {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public OAResponse handle(OARequest req) {
    logger.info("处理 图片信息");
    return new TextResponse(req, "hello " + req.getFromUserName());
  }
}
