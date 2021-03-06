package com.github.emailtohl.web.wechat.domain.msg;

public class VoiceMsg extends BaseMsg {
	private static final long serialVersionUID = 8189082942429452558L;
	private Voice Voice;
	/**
	 * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private Long MediaId;
	/**
	 * 语音格式，如amr，speex等
	 */
	private String Format;
	/**
	 * 消息id，64位整型
	 */
	private Long MsgId;
	/**
	 * 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段
	 * 注：由于客户端缓存，开发者开启或者关闭语音识别功能，对新关注者立刻生效，对已关注用户需要24小时生效。开发者可以重新关注此帐号进行测试
	 */
	private String Recognition;
	
	public VoiceMsg() {
		super("voice");
	}
	
	public Voice getVoice() {
		return Voice;
	}
	public void setVoice(Voice voice) {
		this.Voice = voice;
	}
	public Long getMediaId() {
		return MediaId;
	}
	public void setMediaId(Long mediaId) {
		this.MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		this.Format = format;
	}
	public Long getMsgId() {
		return MsgId;
	}
	public void setMsgId(Long msgId) {
		this.MsgId = msgId;
	}
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		this.Recognition = recognition;
	}

	@Override
	public String toString() {
		return "VoiceMsg [Voice=" + Voice + ", MediaId=" + MediaId + ", Format=" + Format + ", MsgId=" + MsgId
				+ ", Recognition=" + Recognition + ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName
				+ ", CreateTime=" + CreateTime + ", MsgType=" + MsgType + "]";
	}
}
