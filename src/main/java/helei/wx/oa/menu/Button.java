package helei.wx.oa.menu;

import java.io.Serializable;

/**
 * 微信菜单按钮
 *
 * @author HeLei
 */
public class Button implements Serializable {
  protected String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
