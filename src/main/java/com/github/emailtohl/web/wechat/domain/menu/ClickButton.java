package com.github.emailtohl.web.wechat.domain.menu;
/**
 * 按钮对象
 * @author HeLei
 */
public class ClickButton extends Button {
	private static final long serialVersionUID = -3617445526254281707L;
	private String type;
	private String key;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "ClickButton [type=" + type + ", key=" + key + ", name=" + name + "]";
	}
}