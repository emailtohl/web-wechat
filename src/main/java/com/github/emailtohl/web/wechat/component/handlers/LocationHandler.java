package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;
import com.github.emailtohl.web.wechat.component.models.LocationRequest;
import com.github.emailtohl.web.wechat.component.models.LocationResponse;

import static com.github.emailtohl.web.wechat.config.Constant.LOCATION;

/**
 * Created by helei on 2021/11/9.
 */
class LocationHandler extends WechatHandler {
  LocationHandler(WechatHandler next) {
    super(next);
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    if (!(wechatRequest instanceof LocationRequest req)) {
      return next.handle(wechatRequest);
    }
    logger.info("处理地理位置信息");
    LocationResponse resp = new LocationResponse(req, LOCATION);
    // TODO
    return resp;
  }
}
