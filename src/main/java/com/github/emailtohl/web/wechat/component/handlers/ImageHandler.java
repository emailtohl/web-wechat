package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;
import com.github.emailtohl.web.wechat.component.models.ImageRequest;
import com.github.emailtohl.web.wechat.component.models.ImageResponse;
import com.github.emailtohl.web.wechat.config.Constant;

/**
 * Created by helei on 2021/11/9.
 */
class ImageHandler extends WechatHandler {
  ImageHandler(WechatHandler next) {
    super(next);
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    if (!(wechatRequest instanceof ImageRequest req)) {
      return next.handle(wechatRequest);
    }
    logger.info("处理 图片信息");
    ImageResponse tm = new ImageResponse(req, Constant.IMAGE);
    return tm;
  }
}
