package helei.wx.oa.message;

import java.util.Map;

/**
 * Created by helei on 2021/11/9.
 */
public class TextRequest extends OARequest {
  private String Content;

  public TextRequest(Map<String, String> requestMap) {
    super(requestMap);
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }
}
