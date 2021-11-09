package com.github.emailtohl.web.wechat.service.impl;

import com.github.emailtohl.web.wechat.domain.msg.Article;
import com.github.emailtohl.web.wechat.domain.msg.ArticleMsg;
import com.github.emailtohl.web.wechat.domain.msg.BaseMsg;
import com.github.emailtohl.web.wechat.service.WechatService;
import com.github.emailtohl.web.wechat.util.MessageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.emailtohl.web.wechat.config.Constant.*;

/**
 * 地理位置处理器
 *
 * @author HeLei
 */
@Service
public class LocationService implements WechatService {
  private static final Logger logger = LogManager.getLogger();

  @Autowired
  private MessageUtil wechatUtil;

  public BaseMsg echo(Map<String, String> requestMap) {
    logger.info("处理地理位置信息");
    StringBuilder sb = new StringBuilder();
    sb.append("您发送的是地理位置消息！");
    String CreateTime = wechatUtil.formatTime(requestMap.get(CREATE_TIME));
    String Label = requestMap.get(MSG_LABEL);
    String Location_X = requestMap.get(MSG_LOCATION_X);
    String Location_Y = requestMap.get(MSG_LOCATION_Y);
    sb.append(CreateTime).append(" 时，您在：").append(Label).append(" 经度： ").append(Location_X).append(" 纬度： ")
        .append(Location_Y);
    Article ac = new Article();
    ac.setDescription(sb.toString());
    ac.setTitle("返回用户当前的地理信息");
    List<Article> list = new ArrayList<Article>();
    list.add(ac);
    ArticleMsg am = new ArticleMsg();
    am.setArticles(list);
    return am;
  }
}
