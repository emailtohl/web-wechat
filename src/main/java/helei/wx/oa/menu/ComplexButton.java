package helei.wx.oa.menu;

import java.util.Arrays;

/**
 * 复合按钮
 *
 * @author HeLei
 */
public class ComplexButton extends Button {
  private Button[] sub_button;

  public Button[] getSub_button() {
    return sub_button;
  }

  public void setSub_button(Button[] sub_button) {
    this.sub_button = sub_button;
  }

  @Override
  public String toString() {
    return "ComplexButton [sub_button=" + Arrays.toString(sub_button) + ", name=" + name + "]";
  }
}
