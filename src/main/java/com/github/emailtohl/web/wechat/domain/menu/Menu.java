package com.github.emailtohl.web.wechat.domain.menu;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 按钮基类
 *
 * @author HeLei
 */
public class Menu implements Serializable {
  private Button[] button;

  public Button[] getButton() {
    return button;
  }

  public void setButton(Button[] button) {
    this.button = button;
  }

  @Override
  public String toString() {
    return "Menu [button=" + Arrays.toString(button) + "]";
  }
}
