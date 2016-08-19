package com.github.emailtohl.web.wechat.domain.msg;

public class VideoMsg extends BaseMsg {
	private static final long serialVersionUID = 1578630439544721091L;
	private Video Video;
	/**
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private Long MediaId;
	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private Long ThumbMediaId;
	/**
	 * 消息id，64位整型
	 */
	private Long MsgId;
	
	public VideoMsg() {
		super("video");
	}
	
	public Video getVideo() {
		return Video;
	}
	public void setVideo(Video video) {
		this.Video = video;
	}
	public Long getMediaId() {
		return MediaId;
	}
	public void setMediaId(Long mediaId) {
		this.MediaId = mediaId;
	}
	public Long getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(Long thumbMediaId) {
		this.ThumbMediaId = thumbMediaId;
	}
	public Long getMsgId() {
		return MsgId;
	}
	public void setMsgId(Long msgId) {
		this.MsgId = msgId;
	}

	@Override
	public String toString() {
		return "VideoMsg [Video=" + Video + ", MediaId=" + MediaId + ", ThumbMediaId=" + ThumbMediaId + ", MsgId="
				+ MsgId + ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
				+ ", MsgType=" + MsgType + "]";
	}
}
