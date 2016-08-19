package com.github.emailtohl.web.wechat.domain.msg;

import java.io.Serializable;

public class BaseMsg implements Serializable {
	private static final long serialVersionUID = 2698434928949356003L;
	protected String ToUserName;
	protected String FromUserName;
	protected Long CreateTime;
	protected final String MsgType;
	
	public BaseMsg(String msgType) {
		this.MsgType = msgType;
	}
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		this.ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.FromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		this.CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}

	@Override
	public String toString() {
		return "BaseMsg [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
				+ ", MsgType=" + MsgType + "]";
	}
}
