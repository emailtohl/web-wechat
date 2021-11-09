package com.github.emailtohl.web.wechat.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.Optional;

/**
 * Created by helei on 2021/11/9.
 */
public abstract class WechatHandler {
  protected final Logger logger = LoggerFactory.getLogger(getClass());
  protected final WechatHandler next;
  protected Environment env;

  public WechatHandler(WechatHandler next) {
    this.next = next;
  }

  /**
   * 处理消息类型，并返回结果
   *
   * @param wechatRequest 参数
   * @return 消息对象
   */
  public abstract WechatResponse handle(WechatRequest wechatRequest);

  public void setEnv(Environment env) {
    this.env = env;
  }
}
