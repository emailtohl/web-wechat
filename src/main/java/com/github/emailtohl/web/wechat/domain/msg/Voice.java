package com.github.emailtohl.web.wechat.domain.msg;

import java.io.Serializable;

public class Voice implements Serializable {
	private static final long serialVersionUID = 1394286679679396578L;
	/**
	 * 发送的语音的媒体ID
	 */
	private Long MediaId;
	
	public Long getMediaId() {
		return MediaId;
	}
	public void setMediaId(Long mediaId) {
		this.MediaId = mediaId;
	}
	@Override
	public String toString() {
		return "Voice [MediaId=" + MediaId + "]";
	}
}