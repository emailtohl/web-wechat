package helei.wx.oa.message;

/**
 * 处理来自用户端的消息
 * <p>
 * Created by helei on 2021/11/9.
 */
public interface OAHandler {

  OAResponse handle(OARequest oaRequest);
}
