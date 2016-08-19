package com.github.emailtohl.web.wechat.domain.msg;

public class ImageMsg extends BaseMsg {
	private static final long serialVersionUID = -1161571630759227547L;
	private Image Image;
	/**
	 * 图片链接
	 */
	private String PicUrl;
	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private Long MediaId;
	/**
	 * 消息id，64位整型
	 */
	private Long MsgId;
	
	public ImageMsg() {
		super("image");
	}

	public Image getImage() {
		return Image;
	}
	public void setImage(Image image) {
		this.Image = image;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		this.PicUrl = picUrl;
	}
	public Long getMediaId() {
		return MediaId;
	}
	public void setMediaId(Long mediaId) {
		this.MediaId = mediaId;
	}
	public Long getMsgId() {
		return MsgId;
	}
	public void setMsgId(Long msgId) {
		this.MsgId = msgId;
	}

	@Override
	public String toString() {
		return "ImageMsg [Image=" + Image + ", PicUrl=" + PicUrl + ", MediaId=" + MediaId + ", MsgId=" + MsgId
				+ ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
				+ ", MsgType=" + MsgType + "]";
	}
}
