package com.github.emailtohl.web.wechat.service.impl;

import com.github.emailtohl.web.wechat.domain.msg.Article;
import com.github.emailtohl.web.wechat.domain.msg.ArticleMsg;
import com.github.emailtohl.web.wechat.domain.msg.BaseMsg;
import com.github.emailtohl.web.wechat.service.WechatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 音频文件处理器
 *
 * @author HeLei
 */
@Service
public class VoiceService implements WechatService {
  private static final Logger logger = LogManager.getLogger();

  public BaseMsg echo(Map<String, String> requestMap) {
    logger.info("处理音频信息");
    ArticleMsg am = new ArticleMsg();
    Article ac = new Article();
    ac.setDescription("测试跳转商品管理系统");
    ac.setTitle("商品管理系统");
    ac.setPicUrl("http://localhost:8080/myframe/resource/images/img.png");
    ac.setUrl("http://localhost:8080/myframe/");
    List<Article> list = new ArrayList<Article>();
    list.add(ac);
    am.setArticles(list);
    return am;
  }
}
