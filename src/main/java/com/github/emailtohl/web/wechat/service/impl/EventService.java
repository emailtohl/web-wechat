package com.github.emailtohl.web.wechat.service.impl;

import static com.github.emailtohl.web.wechat.config.Constant.EVENT_CLICK;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_KEY;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_LATITUDE;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_LOCATION;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_LONGITUDE;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_PRECISION;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_SCAN;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_SUBSCRIBE;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_TICKET;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_TYPE;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_UNSUBSCRIBE;
import static com.github.emailtohl.web.wechat.config.Constant.EVENT_VIEW;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.emailtohl.web.wechat.domain.msg.BaseMsg;
import com.github.emailtohl.web.wechat.domain.msg.TextMsg;
import com.github.emailtohl.web.wechat.service.WechatService;
/**
 * 事件处理器
 * @author HeLei
 */
@Service
public class EventService implements WechatService {
	private static final Logger logger = LogManager.getLogger();

	public BaseMsg echo(Map<String, String> requestMap) {
		logger.debug("处理事件信息");
		BaseMsg resp = null;
		String eventType = requestMap.get(EVENT_TYPE);
		switch (eventType) {
		case EVENT_SUBSCRIBE:
			logger.debug("被订阅");
			logger.debug(requestMap.get(EVENT_TICKET));
			TextMsg textResp = new TextMsg();
			textResp.setContent("谢谢您的关注！");
			resp = textResp;
			break;
		case EVENT_UNSUBSCRIBE: // 取消订阅
			// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
			logger.debug("取消订阅");
			break;
		case EVENT_CLICK: // 自定义菜单点击事件
			logger.debug("自定义菜单点击事件");
			logger.debug(requestMap.get(EVENT_KEY));
			break;
		case EVENT_VIEW: // TODO 点击菜单跳转链接时的事件推送
			logger.debug("点击菜单跳转链接时的事件推送");
			logger.debug(requestMap.get(EVENT_KEY));
			break;
		case EVENT_SCAN: // TODO 扫描事件
			logger.debug("扫描事件");
			logger.debug(requestMap.get(EVENT_KEY));
			break;
		case EVENT_LOCATION: // TODO 上传的地理位置事件
			logger.debug("上传地理事件");
			logger.debug(requestMap.get(EVENT_LATITUDE));
			logger.debug(requestMap.get(EVENT_LONGITUDE));
			logger.debug(requestMap.get(EVENT_PRECISION));
			break;
		default:
			logger.debug("未知事件");
		}

		if (resp == null) {
			TextMsg textResp = new TextMsg();
			textResp.setContent("已响应事件");
			resp = textResp;
		}
		return resp;
	}
}
