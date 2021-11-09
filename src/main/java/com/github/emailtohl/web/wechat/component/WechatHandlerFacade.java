package com.github.emailtohl.web.wechat.component;

import com.github.emailtohl.web.wechat.component.handlers.WechatHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by helei on 2021/11/9.
 */
@Component
public class WechatHandlerFacade extends WechatHandler {

  @Autowired
  public WechatHandlerFacade(Environment env) {
    super(WechatHandlerFactory.create(env));
  }

  @Override
  public WechatResponse handle(WechatRequest wechatRequest) {
    return next.handle(wechatRequest);
  }
}
