package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import com.github.emailtohl.web.wechat.component.WechatRequest;
import com.github.emailtohl.web.wechat.component.WechatResponse;
import com.github.emailtohl.web.wechat.component.models.EventRequest;
import com.github.emailtohl.web.wechat.component.models.TextResponse;

import static com.github.emailtohl.web.wechat.config.Constant.*;

/**
 * Created by helei on 2021/11/9.
 */
class EventHandler extends WechatHandler {
  EventHandler(WechatHandler next) {
    super(next);
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    if (!(wechatRequest instanceof EventRequest req)) {
      return next.handle(wechatRequest);
    }
    logger.debug("处理事件信息");
    WechatResponse resp = null;
    String eventType = req.getEvent();
    switch (eventType) {
      case EVENT_SUBSCRIBE -> {
        logger.debug("被订阅");
        logger.debug(req.getEventKey());
        resp = new TextResponse(req, "谢谢您的关注！");
      }
      case EVENT_UNSUBSCRIBE -> // 取消订阅
          // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
          logger.debug("取消订阅");
      case EVENT_CLICK -> { // 自定义菜单点击事件
        logger.debug("自定义菜单点击事件");
        logger.debug(req.getEventKey());
      }
      case EVENT_VIEW -> { // TODO 点击菜单跳转链接时的事件推送
        logger.debug("点击菜单跳转链接时的事件推送");
        logger.debug(req.getEventKey());
      }
      case EVENT_SCAN -> { // TODO 扫描事件
        logger.debug("扫描事件");
        logger.debug(req.getEventKey());
      }
      case EVENT_LOCATION -> { // TODO 上传的地理位置事件
        logger.debug("上传地理事件");
        logger.debug(req.getLatitude());
        logger.debug(req.getLongitude());
        logger.debug(req.getPrecision());
      }
      default -> {
        logger.debug("未知事件");
        resp = new TextResponse(req, "未知事件");
      }
    }
    return resp;
  }
}
