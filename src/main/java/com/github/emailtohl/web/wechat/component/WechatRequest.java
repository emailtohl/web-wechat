package com.github.emailtohl.web.wechat.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by helei on 2021/11/9.
 */
public abstract class WechatRequest {
  /**
   * The constant logger.
   */
  protected static final Logger logger = LoggerFactory.getLogger(WechatRequest.class);
  /**
   * The To user name.
   */
  protected String ToUserName;
  /**
   * The From user name.
   */
  protected String FromUserName;
  /**
   * The Create time.
   */
  protected Long CreateTime;
  /**
   * The Type.
   */
  protected WechatRequestType type;

  /**
   * Instantiates a new Wechat request.
   *
   * @param toUserName        the to user name
   * @param fromUserName      the from user name
   * @param createTime        the create time
   * @param wechatRequestType the wechat request type
   */
  public WechatRequest(String toUserName, String fromUserName, Long createTime, String wechatRequestType) {
    ToUserName = toUserName;
    FromUserName = fromUserName;
    CreateTime = createTime;
    try {
      this.type = WechatRequestType.valueOf(wechatRequestType);
    } catch (IllegalArgumentException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * Gets to user name.
   *
   * @return the to user name
   */
  public String getToUserName() {
    return ToUserName;
  }

  /**
   * Sets to user name.
   *
   * @param toUserName the to user name
   */
  public void setToUserName(String toUserName) {
    ToUserName = toUserName;
  }

  /**
   * Gets from user name.
   *
   * @return the from user name
   */
  public String getFromUserName() {
    return FromUserName;
  }

  /**
   * Sets from user name.
   *
   * @param fromUserName the from user name
   */
  public void setFromUserName(String fromUserName) {
    FromUserName = fromUserName;
  }

  /**
   * Gets create time.
   *
   * @return the create time
   */
  public Long getCreateTime() {
    return CreateTime;
  }

  /**
   * Sets create time.
   *
   * @param createTime the create time
   */
  public void setCreateTime(Long createTime) {
    CreateTime = createTime;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public WechatRequestType getType() {
    return type;
  }

  /**
   * Sets type.
   *
   * @param type the type
   */
  public void setType(WechatRequestType type) {
    this.type = type;
  }
}
