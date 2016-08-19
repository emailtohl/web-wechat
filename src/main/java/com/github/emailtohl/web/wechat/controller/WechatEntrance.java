package com.github.emailtohl.web.wechat.controller;

import static com.github.emailtohl.web.wechat.config.Constant.EVENT;
import static com.github.emailtohl.web.wechat.config.Constant.FROM_USER_NAME;
import static com.github.emailtohl.web.wechat.config.Constant.IMAGE;
import static com.github.emailtohl.web.wechat.config.Constant.LINK;
import static com.github.emailtohl.web.wechat.config.Constant.LOCATION;
import static com.github.emailtohl.web.wechat.config.Constant.MSG_TYPE;
import static com.github.emailtohl.web.wechat.config.Constant.NEWS;
import static com.github.emailtohl.web.wechat.config.Constant.TEXT;
import static com.github.emailtohl.web.wechat.config.Constant.TO_USER_NAME;
import static com.github.emailtohl.web.wechat.config.Constant.VOICE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.emailtohl.web.wechat.domain.msg.BaseMsg;
import com.github.emailtohl.web.wechat.service.WechatService;
import com.github.emailtohl.web.wechat.util.MessageUtil;
import com.github.emailtohl.web.wechat.util.SignUtil;
/**
 * 微信服务器访问入口
 * 
 * @author HeLei
 */
@Controller
@RequestMapping(value = "entrance")
public class WechatEntrance {
	private Logger logger = LogManager.getLogger();
	private WechatService textService, imageService, locationService, linkService, voiceService, eventService;
	@Inject
	private SignUtil signUtil;
	@Inject
	private MessageUtil messageUtil;
	
	@RequestMapping(method = GET)
	@ResponseBody
	public String approve(String signature, String timestamp, String nonce, String echostr) {
		if (signUtil.checkSignature(signature, timestamp, nonce)) {
			logger.info("验证成功: " + echostr);
			return echostr;
		} else {
			return "";
		}
	}

	@RequestMapping(method = POST, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String wechatService(HttpServletRequest req) throws IOException {
		ServletInputStream in = req.getInputStream();
		Map<String, String> requestMap;
		try {
			requestMap = messageUtil.parseXml(in);
		} catch (DocumentException e) {
			throw new RuntimeException("微信服务器发来的xml解析有错", e);
		}
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get(FROM_USER_NAME);
		// 公众帐号
		String toUserName = requestMap.get(TO_USER_NAME);
		// 消息类型
		String msgType = requestMap.get(MSG_TYPE);
		// 声明返回的消息
		BaseMsg respMsg;
		switch (msgType) {
		case TEXT:
			respMsg = textService.echo(requestMap);
			break;
		case IMAGE:
			respMsg = imageService.echo(requestMap);
			break;
		case LOCATION:
			respMsg = locationService.echo(requestMap);
			break;
		case LINK:
			respMsg = linkService.echo(requestMap);
			break;
		case VOICE:
			respMsg = voiceService.echo(requestMap);
			break;
		case NEWS:
			respMsg = textService.echo(requestMap);
			break;
		case EVENT:
			// 事件类型
			respMsg = eventService.echo(requestMap);
			break;
		default:
			respMsg = new BaseMsg("");
		}
		respMsg.setToUserName(fromUserName);
		respMsg.setFromUserName(toUserName);
		respMsg.setCreateTime(new Date().getTime());
		return messageUtil.messageToXml(respMsg);
	}

	public WechatService getTextService() {
		return textService;
	}

	@Inject
	public void setTextService(WechatService textService) {
		this.textService = textService;
	}

	public WechatService getImageService() {
		return imageService;
	}

	@Inject
	public void setImageService(WechatService imageService) {
		this.imageService = imageService;
	}

	public WechatService getLocationService() {
		return locationService;
	}

	@Inject
	public void setLocationService(WechatService locationService) {
		this.locationService = locationService;
	}

	public WechatService getLinkService() {
		return linkService;
	}

	@Inject
	public void setLinkService(WechatService linkService) {
		this.linkService = linkService;
	}

	public WechatService getVoiceService() {
		return voiceService;
	}

	@Inject
	public void setVoiceService(WechatService voiceService) {
		this.voiceService = voiceService;
	}

	public WechatService getEventService() {
		return eventService;
	}
	
	@Inject
	public void setEventService(WechatService eventService) {
		this.eventService = eventService;
	}

}
