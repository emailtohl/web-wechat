package com.github.emailtohl.web.wechat.domain.msg;

import java.io.Serializable;

public class Image implements Serializable {
	private static final long serialVersionUID = 79176552532428359L;
	/**
	 * 发送的图片的媒体ID
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
		return "Image [MediaId=" + MediaId + "]";
	}
}