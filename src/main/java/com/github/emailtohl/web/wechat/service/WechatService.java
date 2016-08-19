package com.github.emailtohl.web.wechat.service;

import java.util.Map;

import com.github.emailtohl.web.wechat.domain.msg.BaseMsg;
/**
 * 消息处理的统一接口
 * 
 * @author HeLei
 */
public interface WechatService {
	/**
	 * 处理消息类型，并返回结果
	 * 
	 * @param requestMap 参数Map
	 * @return 消息对象
	 */
	BaseMsg echo(Map<String, String> requestMap);
}
