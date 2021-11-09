package com.github.emailtohl.web.wechat.component.handlers;

import com.github.emailtohl.web.wechat.component.WechatHandler;
import org.springframework.core.env.Environment;

/**
 * Created by helei on 2021/11/9.
 */
public class WechatHandlerFactory {

  public static WechatHandler create(Environment env) {
    WechatHandler wechatHandler = new TextHandler(new EventHandler(new VoiceHandler(new NewsHandler(new ImageHandler(new LinkHandler(new LocationHandler(new VideoHandler(new LastHandler()))))))));
    wechatHandler.setEnv(env);
    return wechatHandler;
  }
}
