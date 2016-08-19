package com.github.emailtohl.web.wechat.service.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.emailtohl.web.wechat.domain.msg.BaseMsg;
import com.github.emailtohl.web.wechat.domain.msg.TextMsg;
import com.github.emailtohl.web.wechat.service.WechatService;
/**
 * 链接处理器
 * @author HeLei
 */
@Service
public class LinkService implements WechatService {
	private static final Logger logger = LogManager.getLogger();
	public BaseMsg echo(Map<String, String> requestMap) {
		logger.info("处理链接信息");
		TextMsg tm = new TextMsg();
		tm.setContent("您发送的是链接消息！");
		return tm;
	}
}
